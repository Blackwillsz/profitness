package br.com.profitness.dto.client;

import br.com.profitness.models.Client;
import br.com.profitness.util.MapperUtil;


public class ClientToApiModel {

    public ClientDto toDto(Client cliente){
        return MapperUtil.converte(this, ClientDto.class);
    }
}
