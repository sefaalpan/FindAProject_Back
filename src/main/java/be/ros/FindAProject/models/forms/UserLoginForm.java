package be.ros.FindAProject.models.forms;

import be.ros.FindAProject.contraints.PasswordConstraint;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginForm {

    @Length(min = 3, max = 30)
    @NotNull @NotBlank @NotEmpty
    String username;

    @NotNull @NotBlank @NotEmpty
    @PasswordConstraint
    String password;
}
