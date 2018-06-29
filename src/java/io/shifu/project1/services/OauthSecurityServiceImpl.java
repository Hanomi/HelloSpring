package io.shifu.project1.services;

import io.shifu.project1.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *  Implementation of {@link OauthSecurityService}
 */

@Service
public class OauthSecurityServiceImpl implements OauthSecurityService {

    private static final Logger logger = LoggerFactory.getLogger(OauthSecurityService.class);

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }

    @Override
    public void vkLogin(User user) {
        org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User(user.getUsername(), "12345", AuthorityUtils.createAuthorityList("ROLE_USER"));

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user1, null, user1.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        logger.debug(String.format("Success vk logged - %s", user.getUsername()));
    }
}
