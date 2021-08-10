package com.projet.epargne.utils;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static final Map<String, String> putAuthTokens(String accessToken , String refreshToken){
        Map<String , String> tokens = new HashMap<>();
        tokens.put("access_token" , accessToken);
        tokens.put("refresh_token" , refreshToken);
        return tokens;
    }
}
