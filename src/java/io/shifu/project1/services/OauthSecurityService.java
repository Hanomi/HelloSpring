package io.shifu.project1.services;

import io.shifu.project1.model.User;

/**
 * Security service
 */

public interface OauthSecurityService {

    String findLoggedInUsername();

    void vkLogin(User user);
}
