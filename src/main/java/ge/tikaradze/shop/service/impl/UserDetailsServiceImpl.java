package ge.tikaradze.shop.service.impl;

import ge.tikaradze.shop.entity.AppUser;
import ge.tikaradze.shop.entity.Role;
import ge.tikaradze.shop.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.findByUsername(username);

        if (appUser != null) {
            String commaSeparatedStringToAuthorityString = "ROLE_";
            for (Role currentUserRole : appUser.getRoles()) {
                commaSeparatedStringToAuthorityString += currentUserRole.getName() + ",ROLE_";
            }
            String authorityStr = commaSeparatedStringToAuthorityString.substring(0, commaSeparatedStringToAuthorityString.lastIndexOf(","));
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorityStr);

            return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);

        }
        // თუ იუზერი არ მოიძებნა ვისვრით ექსეფშენს
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }

}