package com.selflearn.ecommerce.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegisterDto {
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private  final String firstName;
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private  final String lastName;
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private  final String username;
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private  final String email;
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private  final String password;
}
