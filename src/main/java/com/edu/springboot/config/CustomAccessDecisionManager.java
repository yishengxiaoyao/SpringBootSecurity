package com.edu.springboot.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * 自定义决断器
 */
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if (collection==null){
            return;
        }
        //config urlroles
        Iterator<ConfigAttribute> iterable=collection.iterator();

        while (iterable.hasNext()){
            ConfigAttribute configAttribute=iterable.next();
            //need role
            String role=configAttribute.getAttribute();
            //user role
            for (GrantedAuthority ga:authentication.getAuthorities()){
                if (role.contains(ga.getAuthority())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("Access Denied!!!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
