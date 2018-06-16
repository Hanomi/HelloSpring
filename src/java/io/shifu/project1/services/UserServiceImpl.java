package io.shifu.project1.services;

// implementation of userService for User

import io.shifu.project1.dao.RoleDao;
import io.shifu.project1.dao.UserDao;
import io.shifu.project1.model.Role;
import io.shifu.project1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RoleDao roleDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) {
        // кодируем пароль
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // присваеваем роль по умолчанию
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        // сохраняем
        userDao.save(user);
    }

    @Override
    public void activationUser(User user) {
        user.setConfirmationToken(null);
        user.setEnabled(true);
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findByConfirmationToken(String confirmationToken) {
        return userDao.findByConfirmationToken(confirmationToken);
    }
}
