package com.edu.springboot.config;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 进行url匹配，防止csrf攻击
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {

    private Pattern allowMethods=Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

    private List<String> exclusionUrls;
    @Override
    public boolean matches(HttpServletRequest httpServletRequest) {
        if (!CollectionUtils.isEmpty(exclusionUrls)){
            String servletPath=httpServletRequest.getServletPath();
            for (String url:exclusionUrls){
                if (servletPath.contains(url)){
                    return false;
                }
            }
        }
        return !allowMethods.matcher(httpServletRequest.getMethod()).matches();
    }

    public List<String> getExclusionUrls() {
        return exclusionUrls;
    }

    public void setExclusionUrls(List<String> exclusionUrls) {
        this.exclusionUrls = exclusionUrls;
    }
}
