package com.edu.springboot.config;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.*;

/**
 *  用来储存请求与权限的对应关系
 */
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private Map<String, Collection<ConfigAttribute>> resourceMap = null;
    private PathMatcher pathMatcher = new AntPathMatcher();

    private String urlroles;
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String url = ((FilterInvocation) o).getRequestUrl();

        if(resourceMap == null)
            resourceMap = loadResourceMatchAuthority();

        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            if (pathMatcher.match(resURL,url)) {
                return resourceMap.get(resURL);
            }
        }
        return resourceMap.get(url);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public CustomSecurityMetadataSource(String urlroles) {
        super();
        this.urlroles = urlroles;
        resourceMap=loadResourceMatchAuthority();
    }
    private Map<String, Collection<ConfigAttribute>> loadResourceMatchAuthority() {

        Map<String, Collection<ConfigAttribute>> map = new HashMap<String, Collection<ConfigAttribute>>();

        if(urlroles != null && !urlroles.isEmpty()){
            String[] resouces = urlroles.split(";");
            for(String resource : resouces){
                String[] urls = resource.split("=");
                String[] roles = urls[1].split(",");
                Collection<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
                for(String role : roles){
                    ConfigAttribute config = new SecurityConfig(role.trim());
                    list.add(config);
                }
                //key：url, value：roles
                map.put(urls[0].trim(), list);
            }
        }else{

        }
        return map;
    }
}
