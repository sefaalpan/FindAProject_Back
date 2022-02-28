package be.ros.FindAFreelance.contraints;

import be.ros.FindAFreelance.models.forms.UserLoginForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    @Override
    public boolean isValid(String pwd, ConstraintValidatorContext context) {


        return pwd.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
                && pwd.length() >= 8 && pwd.length() <= 30;
    }
}
