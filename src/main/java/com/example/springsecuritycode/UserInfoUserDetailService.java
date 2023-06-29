package com.example.springsecuritycode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserInfoUserDetailService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);

        //        //Uncool method
//        UserInfo userInfo1 = userInfo.get();
//        if(!userInfo.isPresent()){
//            throw new UsernameNotFoundException("user not present"+username);
//        }
//        return new UserInfoUserDetails(userInfo1);

        //Cooler way
        return userInfo.map(UserInfoUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("user not found"+username));

    }
}
