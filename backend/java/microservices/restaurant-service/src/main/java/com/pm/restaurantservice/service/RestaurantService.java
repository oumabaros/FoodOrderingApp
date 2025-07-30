package com.pm.restaurantservice.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pm.restaurantservice.dto.RestaurantRequestDTO;
import com.pm.restaurantservice.dto.RestaurantResponseDTO;
import com.pm.restaurantservice.grpc.AuthServiceGrpcClient;
import com.pm.restaurantservice.grpc.BillingServiceGrpcClient;
import com.pm.restaurantservice.mapper.RestaurantMapper;
import com.pm.restaurantservice.model.Restaurant;
import com.pm.restaurantservice.repository.RestaurantRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AuthServiceGrpcClient authServiceGrpcClient;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final Cloudinary cloudinary;


    public RestaurantService(RestaurantRepository restaurantRepository,
                             AuthServiceGrpcClient authServiceGrpcClient,
                             BillingServiceGrpcClient billingServiceGrpcClient,
                             Cloudinary cloudinary) {
        this.restaurantRepository = restaurantRepository;
        this.cloudinary = cloudinary;
        this.authServiceGrpcClient=authServiceGrpcClient;
        this.billingServiceGrpcClient=billingServiceGrpcClient;

    }

    public Optional<RestaurantResponseDTO> getRestaurantByUser(Authentication authentication) {
        Optional<Restaurant> restaurant = restaurantRepository.findByAuth0Id(getAuth0Id(authentication));

        return RestaurantMapper.toOptionalDTO(restaurant);
   }

    public static String getAuth0Id(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken token && token.getPrincipal() instanceof DefaultOidcUser user) {
            OidcIdToken idToken = user.getIdToken();
            return idToken.getClaimAsString("sub");
        } else if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            Jwt jwt = jwtAuthenticationToken.getToken();
            return jwt.getClaimAsString("sub");
        } else {
            return null;
        }
    }

    public Optional<RestaurantResponseDTO> createRestaurant(RestaurantRequestDTO restaurantRequestDTO,
                                                            Authentication authentication) {
        String userId=authServiceGrpcClient.getUserId(getAuth0Id(authentication)).getUserId();
        Optional<Restaurant> restaurant = restaurantRepository.findByUser(userId);
        if (restaurant.isPresent()) {
            return RestaurantMapper.toOptionalDTO(restaurant);
        } else {
            try {
                if (restaurantRequestDTO.getImageFile()!=null) {
                    Optional<MultipartFile> imageFile = restaurantRequestDTO.getImageFile();
                    File convFile = new File(System.getProperty("java.io.tmpdir") + "/" +
                            imageFile.get().getOriginalFilename());
                    FileOutputStream fos = new FileOutputStream(convFile);
                    fos.write(imageFile.get().getBytes());
                    fos.close();
                    var pic = cloudinary.uploader().upload(convFile, ObjectUtils.asMap("folder", "/mern-food-ordering-app/"));
                    restaurantRequestDTO.setImageUrl(pic.get("url").toString());
                }

                restaurantRequestDTO.setUser(userId);
                restaurantRequestDTO.setAuth0Id(getAuth0Id(authentication));
                LocalDate lt = LocalDate.now();
                restaurantRequestDTO.setLastUpdated(lt);

                Restaurant newRestaurant=restaurantRepository.save(RestaurantMapper.toModel(restaurantRequestDTO));

                billingServiceGrpcClient.createBillingAccount(newRestaurant.getId(),
                        newRestaurant.getRestaurantName(), newRestaurant.getCountry());

                return Optional.of(RestaurantMapper.toDTO(newRestaurant));
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Could not create Restaurant.");
            }
        }
    }

    public Optional<RestaurantResponseDTO> updateRestaurant(RestaurantRequestDTO restaurantRequestDTO,
                                                            Authentication authentication) {
        String userId=authServiceGrpcClient.getUserId(getAuth0Id(authentication)).getUserId();
        Optional<Restaurant> restaurant = restaurantRepository.findByUser(userId);
        if (restaurant.isPresent()) {
            try {
                if (restaurantRequestDTO.getImageFile()!=null) {
                    Optional<MultipartFile> imageFile = restaurantRequestDTO.getImageFile();
                    File convFile = new File(System.getProperty("java.io.tmpdir") + "/" +
                            imageFile.get().getOriginalFilename());
                    FileOutputStream fos = new FileOutputStream(convFile);
                    fos.write(imageFile.get().getBytes());
                    fos.close();
                    var pic = cloudinary.uploader().upload(convFile, ObjectUtils.asMap("folder", "/mern-food-ordering-app/"));
                    restaurantRequestDTO.setImageUrl(pic.get("url").toString());
                }

                LocalDate lt = LocalDate.now();
                restaurantRequestDTO.setLastUpdated(lt);

                return restaurant.map(res -> {
                    res.setRestaurantName(restaurantRequestDTO.getRestaurantName());
                    res.setCity(restaurantRequestDTO.getCity());
                    res.setCountry(restaurantRequestDTO.getCountry());
                    res.setDeliveryPrice(restaurantRequestDTO.getDeliveryPrice());
                    res.setEstimatedDeliveryTime(restaurantRequestDTO.getEstimatedDeliveryTime());
                    res.setImageUrl(restaurantRequestDTO.getImageUrl());
                    res.setLastUpdated(restaurantRequestDTO.getLastUpdated());
                    res.setCuisines(restaurantRequestDTO.getCuisines());
                    res.setMenuItems(restaurantRequestDTO.getMenuItems());
                    res.setUser(userId);
                    res.setAuth0Id(getAuth0Id(authentication));
                    Restaurant updatedRestaurant = restaurantRepository.save(res);
                    return RestaurantMapper.toDTO(updatedRestaurant);
                });
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Failed to update Restaurant.");
            }
        } else {
            return RestaurantMapper.toOptionalDTO(restaurant);
        }
    }
}