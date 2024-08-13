package com.recommendation_service.config;

import java.lang.reflect.Method;

public class CustomKeyGenerator implements org.springframework.cache.interceptor.KeyGenerator {
    // This class is used to generate custom keys for caching
    // The key is generated based on the user id and the profile id
    // The key is used to cache the recommendations for a user
    @Override
    public Object generate(Object target, Method method, Object... params) {
        Integer userId = (Integer) params[0];
        Integer profileId = (Integer) params[1];
        Integer limit = (Integer) params[2];
        Integer offset = (Integer) params[3];
        return userId + ":" + profileId + ":" + limit + ":" + offset;
    }
}
