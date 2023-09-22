package br.com.profitness.dto.client;

import br.com.profitness.models.Client;
import br.com.profitness.util.MapperUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ClientUpdateDto {

    @NotBlank
    private String fullName;

    @NotBlank
    @Size(max = 2)
    private String ddd;

    @NotBlank
    @Size(max = 10)
    @Pattern(regexp = "\\d{5}-\\d{4}")
    private String phoneNumber;

    @NotBlank
    private String email;

    @NotBlank
    private String active;

    public Client toObject(){
        return MapperUtil.converte(this, Client.class);
    }

    public ClientDto toDto() {
        return MapperUtil.converte(this, ClientDto.class);
    }

    public ClientUpdateDto toUpdate(){
        return MapperUtil.getMapper().map(this, ClientUpdateDto.class);
    }

}
