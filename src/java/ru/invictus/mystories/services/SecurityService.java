package ru.invictus.mystories.services;

/**
 * Security service
 */

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
