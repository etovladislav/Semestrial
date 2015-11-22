package ru.kpfu.itis.service;

import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.RegistrationUserRepository;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.util.Avatar;
import ru.kpfu.itis.util.Md5Util;

import java.util.Arrays;
import java.util.Map;

public class RegistrationUserService {

    private RegistrationUserRepository registrationUserRepository = new RegistrationUserRepository();

    private UserRepository userRepository = new UserRepository();
    public boolean registrationUser(Map<String, String[]> parameterMap) {
        User user = new User(parameterMap.get("login")[0],parameterMap.get("email")[0], parameterMap.get("firstname")[0],
                parameterMap.get("lastname")[0],
                Md5Util.md5(parameterMap.get("password")[0]));
        userRepository.saveAvatar(parameterMap.get("login")[0], Avatar.DEFAULT);
        registrationUserRepository.registrationUser(user);
        return false;
    }
}
