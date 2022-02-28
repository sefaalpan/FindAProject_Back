package be.ros.FindAFreelance.contraints;

import be.ros.FindAFreelance.services.impl.UserServiceImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameExistValidator implements ConstraintValidator<UsernameExistConstraint, String> {


    private final UserServiceImpl service;

    public UsernameExistValidator(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return service.findByUsername(value) == null;
    }
}
