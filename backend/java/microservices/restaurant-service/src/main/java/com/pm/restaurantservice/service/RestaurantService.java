package com.pm.restaurantservice.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.pm.restaurantservice.dto.RestaurantRequestDTO;
import com.pm.restaurantservice.dto.RestaurantResponseDTO;
import com.pm.restaurantservice.exception.RestaurantNotFoundException;
import com.pm.restaurantservice.mapper.RestaurantMapper;
import com.pm.restaurantservice.model.Restaurant;
import com.pm.restaurantservice.repository.RestaurantRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RestaurantService {
  private final RestaurantRepository restaurantRepository;
  private final Cloudinary cloudinary;
  public RestaurantService(RestaurantRepository restaurantRepository,
      Cloudinary cloudinary) {
    this.restaurantRepository = restaurantRepository;
    this.cloudinary=cloudinary;
  }

  public List<RestaurantResponseDTO> getRestaurants() {
    List<Restaurant> restaurants = restaurantRepository.findAll();
    return restaurants.stream().map(RestaurantMapper::toDTO).toList();
  }

  public static String getAuth0Id(Authentication authentication) {
     if (authentication instanceof OAuth2AuthenticationToken token && token.getPrincipal() instanceof DefaultOidcUser user) {
      OidcIdToken idToken = user.getIdToken();
      return idToken.getClaimAsString("sub");
    }
    else if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
      Jwt jwt = jwtAuthenticationToken.getToken();
      return jwt.getClaimAsString("sub");
    }
    else{
      return null;
    }
  }
  public RestaurantResponseDTO createRestaurant(@ModelAttribute RestaurantRequestDTO restaurantRequestDTO) {
    if(restaurantRepository.existsByUserId(restaurantRequestDTO.getUserId())){
      return RestaurantMapper.toDTO(null);
    }
    else{
      try {
        MultipartFile imageFile = (MultipartFile) restaurantRequestDTO.getImageFile();
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + imageFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(imageFile.getBytes());
        fos.close();

        var pic = cloudinary.uploader().upload(convFile, ObjectUtils.asMap("folder", "/mern-food-ordering-app/"));

        restaurantRequestDTO.setImageUrl(pic.get("url").toString());
        LocalDate lt = LocalDate.now();
        restaurantRequestDTO.setLastUpdated(lt);
        Restaurant newRestaurant=restaurantRepository.save(
                RestaurantMapper.toModel(restaurantRequestDTO));
        return RestaurantMapper.toDTO(newRestaurant);

      } catch (IOException e) {
        throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Failed to upload the file.");
      }
    }
  }

  public RestaurantResponseDTO updateRestaurant(String _id,
      RestaurantRequestDTO restaurantRequestDTO) {

    Restaurant restaurant = restaurantRepository.findById(_id).orElseThrow(
        () -> new RestaurantNotFoundException("Restaurant not found"));

    restaurant.setRestaurantName(restaurantRequestDTO.getRestaurantName());
    restaurant.setCity(restaurantRequestDTO.getCity());
    restaurant.setCountry(restaurantRequestDTO.getCountry());
    restaurant.setDeliveryPrice(restaurantRequestDTO.getDeliveryPrice());
    restaurant.setEstimatedDeliveryTime(restaurantRequestDTO.getEstimatedDeliveryTime());
    restaurant.setImageUrl(restaurantRequestDTO.getImageUrl());
    restaurant.setLastUpdated(restaurantRequestDTO.getLastUpdated());
    restaurant.setCuisines(restaurantRequestDTO.getCuisines());
    restaurant.setMenuItems(restaurantRequestDTO.getMenuItems());

    Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
    return RestaurantMapper.toDTO(updatedRestaurant);
  }

}
