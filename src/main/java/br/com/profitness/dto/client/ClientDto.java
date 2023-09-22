package br.com.profitness.dto.client;

import br.com.profitness.models.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;

import static br.com.profitness.util.MapperUtil.converte;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientDto {

    @NotBlank
    private String fullName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;

    public ClientDto(){}

    public static ClientDto respostaDto(Client client){
       return new ClientDto(
               client.getFullName(),
               client.getBirthDate(),
               client.getPhoneNumber(),
               client.getEmail());
    }

    public static ClientDto respostaDto(Optional<Client> client) {
        return new ClientDto(
                client.get().getFullName(),
                client.get().getBirthDate(),
                client.get().getPhoneNumber(),
                client.get().getEmail());
    }

    public ClientInput clientDto(){
        return converte(this, ClientInput.class);
    }

    public Client toObject(){
        return converte(this, Client.class);

    }
}

