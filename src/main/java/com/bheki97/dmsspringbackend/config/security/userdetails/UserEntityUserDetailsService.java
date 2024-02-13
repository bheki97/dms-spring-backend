package com.bheki97.dmsspringbackend.config.security.userdetails;

import com.bheki97.dmsspringbackend.entity.UserEntity;
import com.bheki97.dmsspringbackend.exception.DMSException;
import com.bheki97.dmsspringbackend.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserEntityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity optionalUser = userEntityRepository.findByEmail(username)
                .orElseThrow(()->new DMSException("Account Does not Exist"));

        return new UserEntityUserdetails(optionalUser);
    }
}
