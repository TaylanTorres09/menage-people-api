package com.manager.people.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDTO {
    
    private Long id;

    private String name;

    private LocalDate birthDate;

}
