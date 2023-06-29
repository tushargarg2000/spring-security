package com.example.springsecuritycode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUserDetails implements UserDetails {


    private String name;

    private String password;

    List<GrantedAuthority> authorities;

    //This is a constructor to initalize the objects
    public UserInfoUserDetails(UserInfo userInfo){

        name = userInfo.getName();
        password = userInfo.getPassword();


        //Not cool method
//        String roles = userInfo.getRoles();
//        String[] rolesArray = roles.split(",");
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for(String role : rolesArray){
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
//            authorities.add(grantedAuthority);
//
//        }
//          If the below is not clear use above one

        authorities = Arrays.stream(userInfo.getRoles().split(","))
                        .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

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
