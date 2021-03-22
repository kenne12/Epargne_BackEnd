package com.projet.epargne.services.impl;

import com.projet.epargne.dao.PrivilegeRepository;
import com.projet.epargne.dto.UtilisateurDto;
import com.projet.epargne.entities.Privilege;
import com.projet.epargne.services.interfaces.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurService accountService;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilisateurDto u = accountService.findByUserName(username);
        if (u == null) {
            throw new UsernameNotFoundException(username);
        } else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            Collection<Privilege> privileges = privilegeRepository.findPrivilegeByIdUtilisateur(u.getIdUtilisateur());

            /*privileges.forEach(r -> {
                authorities.add(new SimpleGrantedAuthority(r.getMenu().getRole()));
            });*/

            /*u.setPrivileges(privilegeRepository.findPrivilegeByIdUtilisateur(u.getIdUtilisateur()));
            u.getPrivileges().forEach(r -> {
                authorities.add(new SimpleGrantedAuthority(r.getMenu().getRole()));
            });*/

            return new User(u.getLogin(), u.getPassword(), authorities);
        }
    }
}