package ru.kpfu.itis.service;

import ru.kpfu.itis.repository.AuthUserRepository;
import ru.kpfu.itis.util.Md5Util;

/**
 * Created by Vladislav on 19.10.2015.
 */
public class AuthUserService {
    private AuthUserRepository authUserRepository = new AuthUserRepository();
    public boolean authUser(String login, String password){
        return authUserRepository.authUser(login, Md5Util.md5(password));
    }
}
