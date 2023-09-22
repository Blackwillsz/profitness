package br.com.profitness.dto.client;

import br.com.profitnes.annotation.Cpf;
import br.com.profitness.models.Address;
import br.com.profitness.models.Client;
import br.com.profitness.util.MapperUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientInput {

        @NotBlank
        private String fullName;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate birthDate;

        @NotBlank
        @Size(max = 14)
        @Cpf
        private String cpf;

        @NotBlank
        @Size(max = 13)
        private String rg;

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
        private String sex;

        @NotBlank
        private String active;

        @NotNull
        private Address address;

        public Client toObjeto(){
                return MapperUtil.converte(this, Client.class);
        }
}
