package com.manager.people.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDTO {
    
    private Long id;

    @NotBlank(message = "Required Name")
    @Size(min = 5, max = 20, message = "Length between 5 and 20 characters")
    private String name;

    @NotBlank(message = "Required Birth Date")
    @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$", message = "example dd/mm/yyyy")
    private String birthDate;

}
