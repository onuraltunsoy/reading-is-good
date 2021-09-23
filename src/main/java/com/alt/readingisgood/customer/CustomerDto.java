package com.alt.readingisgood.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document
@Data
@Builder
public class CustomerDto {

    @NotBlank(message = "Username cannot be empty!")
    private String username;

    @JsonIgnore
    @NotBlank(message = "password cannot be empty!")
    private String password;

}
