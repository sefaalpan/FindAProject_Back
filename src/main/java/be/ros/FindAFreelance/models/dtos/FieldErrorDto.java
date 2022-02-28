package be.ros.FindAFreelance.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldErrorDto {

    private String message;
    private String fieldName;

}
