package be.ros.FindAProject.models.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorDto {

    public List<String> globalErrors = new ArrayList<>();
    public List<FieldErrorDto> fieldErrors = new ArrayList<>();

    public ValidationErrorDto() {}
}
