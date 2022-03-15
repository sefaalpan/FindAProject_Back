package be.ros.FindAProject.models.forms;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
public class UserSimpleForm {

    @NotNull @NotBlank @NotEmpty
    String username;

}
