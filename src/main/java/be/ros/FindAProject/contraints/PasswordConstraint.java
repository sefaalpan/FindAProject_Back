package be.ros.FindAProject.contraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PasswordValidator.class})
public @interface PasswordConstraint {

//    String message() default "le mot de passe n'est pas valide";
    String message() default "Mot de passe pas valiiiide";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
