package br.com.profitness.dto.client;

import br.com.profitness.models.Client;
import br.com.profitness.util.MapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientInputToDomainModel extends Client {

    public Client toModel(ClientUpdateDto clienteUpdateDto){
        return MapperUtil.converte(this, Client.class);
    }
}
