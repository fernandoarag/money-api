package com.fernandoarag.moneyapi.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fernandoarag.moneyapi.api.models.UsersModel;
import com.fernandoarag.moneyapi.api.repository.UsersRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UsersModel> userOptional = usersRepository.findByEmail(email);
        UsersModel user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos!"));
        return new User(email, user.getPassword(), getPermissions(user));
    }

    private Collection<? extends GrantedAuthority> getPermissions(UsersModel usersModel) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        usersModel.getPermissions()
                .forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
        return authorities;
    }

}
