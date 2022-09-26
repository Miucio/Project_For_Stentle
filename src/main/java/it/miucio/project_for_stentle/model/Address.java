package it.miucio.project_for_stentle.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class Address {
    @NotBlank
    @NotEmpty
    private String street;
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^[0-9]+$")
    private String number;
    @NotBlank
    @NotEmpty
    private String country;

    public Address(String street, String number, String country) {
        this.street = street;
        this.number = number;
        this.country = country;
    }
}
