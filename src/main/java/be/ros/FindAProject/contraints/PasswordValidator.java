package be.ros.FindAProject.contraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    @Override
    public boolean isValid(String pwd, ConstraintValidatorContext context) {


        return pwd.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
                && pwd.length() >= 8 && pwd.length() <= 30;
    }
}
