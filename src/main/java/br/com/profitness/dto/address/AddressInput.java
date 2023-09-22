package br.com.profitness.dto.address;


import br.com.profitness.models.Address;
import br.com.profitness.util.MapperUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AddressInput {

    @NotBlank
    private String street;

    @NotBlank
    @Size(max = 4)
    private String number;

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

    @NotNull
    private String active;

    public Address toObject() {
        return MapperUtil.converte(this, Address.class);
    }
}
