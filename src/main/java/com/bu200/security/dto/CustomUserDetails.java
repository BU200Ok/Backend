package com.bu200.security.dto;

import com.bu200.login.entity.Account;
import com.bu200.security.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getAccountRole();
            }
        });
        return collection;
    }

    //비번
    @Override
    public String getPassword() {
        return user.getAccountPassword();
    }
    //이메일
    public String getEmail(){
        return user.getAccountEmail();
    }
    //이름
    public String getName(){
        return user.getAccountName();
    }
    public Integer getCode(){
        return user.getAccountCode();
    }
    //아이디
    @Override
    public String getUsername() {
        return user.getAccountId();
    }

//    public Integer getAccountCode() {
//        return user.getAccountCode();
//    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
