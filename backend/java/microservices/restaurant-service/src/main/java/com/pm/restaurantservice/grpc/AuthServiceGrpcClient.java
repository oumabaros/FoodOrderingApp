package com.pm.restaurantservice.grpc;

import auth.AuthRequest;
import auth.AuthResponse;
import auth.AuthServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(
            AuthServiceGrpcClient.class);
    private final AuthServiceGrpc.AuthServiceBlockingStub blockingStub;

    public AuthServiceGrpcClient( @Value("${auth.service.address}") String serverAddress,
                                  @Value("${auth.service.port}") int serverPort
            ) {

        log.info("Connecting to Auth Service GRPC service at {}:{}",
                serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();

        blockingStub = AuthServiceGrpc.newBlockingStub(channel);
    }
    public AuthResponse getAuth0Id() {

        AuthRequest request = AuthRequest.newBuilder().build();

        AuthResponse response = blockingStub.getUserDetails(request);
        log.info("Received Auth0Id Response from auth service via GRPC: {}", response.getAuthId());
        return response;
    }
    public AuthResponse getUserId() {

        AuthRequest request = AuthRequest.newBuilder().build();

        AuthResponse response = blockingStub.getUserDetails(request);
        log.info("Received UserId Response from auth service via GRPC: {}",response.getUserId());
        return response;
    }
}
