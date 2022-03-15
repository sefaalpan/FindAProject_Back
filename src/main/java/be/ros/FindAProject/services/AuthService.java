package be.ros.FindAProject.services;

import be.ros.FindAProject.models.dtos.UserDto;
import be.ros.FindAProject.models.forms.UserLoginForm;
import be.ros.FindAProject.models.forms.UserRegisterForm;

public interface AuthService {
    UserDto login(UserLoginForm form);
    UserDto register(UserRegisterForm form);
}

