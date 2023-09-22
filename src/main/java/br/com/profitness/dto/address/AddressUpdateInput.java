package br.com.profitness.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class AddressUpdateInput {

    @NotBlank
    private String street;

    @NotNull
    private Integer number;

    private String complement;

    @NotBlank
    private String neigborhood;

    @NotBlank
    @Size(max = 9)
    @Pattern(regexp = "\\d{5}-\\d{3}")
    private String zipCode;

    private String reference;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

}
