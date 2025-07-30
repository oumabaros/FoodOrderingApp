package com.pm.authservice.grpc;

import auth.AuthRequest;
import auth.AuthResponse;
import auth.AuthServiceGrpc.AuthServiceImplBase;
import com.pm.authservice.utils.AuthUtils;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class AuthGrpcService extends AuthServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(
            AuthGrpcService.class);

    @Override
    public void sendDetails(AuthRequest authRequest,
                          StreamObserver<AuthResponse> responseObserver){
        log.info("Restaurant request for {} received."+authRequest.toString());
        String userId=AuthUtils.getUserId(authRequest.getAuthId());
        AuthResponse authResponse=AuthResponse.newBuilder()
                .setUserId(userId)
                .build();
        responseObserver.onNext(authResponse);
        responseObserver.onCompleted();

    }
}
