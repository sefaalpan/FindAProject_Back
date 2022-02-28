package be.ros.FindAFreelance.services;

import be.ros.FindAFreelance.models.dtos.UserDto;
import be.ros.FindAFreelance.models.forms.UserLoginForm;
import be.ros.FindAFreelance.models.forms.UserRegisterForm;

public interface AuthService {
    UserDto login(UserLoginForm form);
    UserDto register(UserRegisterForm form);
}

