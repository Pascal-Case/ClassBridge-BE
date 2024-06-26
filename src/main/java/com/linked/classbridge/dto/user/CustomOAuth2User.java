package com.linked.classbridge.dto.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {

    private final UserDto userDto;

    public CustomOAuth2User(UserDto userDTO) {

        this.userDto = userDTO;
    }

    // 사용자의 속성을 반환
    @Override
    public Map<String, Object> getAttributes() {

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("provider", userDto.getProvider());
        attributes.put("providerId", userDto.getProviderId());
        attributes.put("email", userDto.getEmail());
        attributes.put("username", userDto.getUsername());
        attributes.put("authType", userDto.getAuthType());
        attributes.put("roles", userDto.getRoles());
        return attributes;
    }

    // 사용자의 권한을 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = userDto.getRoles();

        if (roles != null) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }

        return authorities;
    }

    @Override
    public String getName() {

        return userDto.getUsername();
    }

    public String getEmail() {

        return userDto.getEmail();
    }

}
