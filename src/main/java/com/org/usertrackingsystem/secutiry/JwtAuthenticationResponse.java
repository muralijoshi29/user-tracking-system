package com.org.usertrackingsystem.secutiry;

import com.org.usertrackingsystem.vo.ApiResponse;

public class JwtAuthenticationResponse extends ApiResponse{
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(Boolean success, String message) {
    	super(success,message);
    }
    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
