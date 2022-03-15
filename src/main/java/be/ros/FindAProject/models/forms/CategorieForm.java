package be.ros.FindAProject.models.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorieForm {

    @NotNull
    @NotBlank
    @NotEmpty
    private String label;
}

