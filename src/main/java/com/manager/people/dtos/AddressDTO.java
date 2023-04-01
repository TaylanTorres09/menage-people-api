package com.manager.people.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {

    @NotBlank(message = "Required Street")
    @Size(min = 1, max = 30, message = "Length between 5 and 20 characters")
    private String street;
    
    @NotBlank(message = "Required Brazilian CEP")
    @Pattern(regexp = "[0-9]{5}-[\\d]{3}", message = "example XXXXX-XXX")
    private String cep;
    
    @NotNull(message = "Required number's address")
    private Integer numberAddress;
    
    @NotBlank(message = "Required City")
    @Size(min = 1, max = 30, message = "Length between 5 and 20 characters")
    private String city;
    
    @NotNull(message = "Required if principal address")
    private Boolean principalAddress;

    @NotNull(message = "Identify person")
    private Long personId;
}
