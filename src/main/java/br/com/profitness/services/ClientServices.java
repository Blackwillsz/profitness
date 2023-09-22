package br.com.profitness.services;

import br.com.profitness.dto.client.ClientDto;
import br.com.profitness.dto.client.ClientUpdateDto;
import br.com.profitness.dto.client.ClientInput;
import br.com.profitness.exceptions.ClientServicesExceptions;
import br.com.profitness.models.Address;
import br.com.profitness.models.Client;
import br.com.profitness.repositories.AddressRepository;
import br.com.profitness.repositories.ClientRepository;
import br.com.profitness.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;



@Service
public class ClientServices {

    private final ClientRepository clientRepository;

    private final AddressRepository addressRepository;

    private final MapperUtil mapperUtil;

    public ClientServices(final ClientRepository clientRepository, final AddressRepository addressRepository, MapperUtil mapperUtil) {
        this.clientRepository = Objects.requireNonNull(clientRepository);
        this.addressRepository = Objects.requireNonNull(addressRepository);
        this.mapperUtil = Objects.requireNonNull(mapperUtil);
    }

    public boolean existsByCpf(String cpf) {
        return clientRepository.existsByCpf(cpf);
    }

    public boolean existsByRg(String rg) {
        return clientRepository.existsByRg(rg);
    }

    public Optional<Address> addressById(UUID addressId) {
        return addressRepository.findById(addressId);
    }

    @Transactional
    public Client toSave(@RequestBody ClientInput clientInput) {
        var client = new Client();
        Optional<Address> addresId = Optional.ofNullable(Optional.ofNullable(addressById(clientInput.getAddress().getId())
                .orElse(clientInput.getAddress()))
                .orElseThrow(() -> new ClientServicesExceptions("Address not found")));
        client = clientInput.toObjeto();
        client.setCreatedAt(Instant.now());
        return clientRepository.save(client);
    }

    public List<Client> findAllCustomer() {
        return clientRepository.findAll();
    }

    public ClientDto findById(UUID id) {
       Optional<Client> client = clientRepository.findById(id);

        if(client == null){
            throw new NotFoundException((format("Cliente com o id: [%s] não encontrado", id)));
        }
        ClientDto dtoResponse = ClientDto.respostaDto(client.get());
        return dtoResponse;
    }

    @Transactional
    public Client updateClient(UUID id, ClientUpdateDto clientUpdateDto){
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (!clientOptional.isPresent()) {
            clientOptional.orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        }

        clientOptional.get().toObject();
        clientOptional.get().setFullName(clientUpdateDto.getFullName());
        clientOptional.get().setDdd(clientUpdateDto.getDdd());
        clientOptional.get().setPhoneNumber(clientUpdateDto.getPhoneNumber());
        clientOptional.get().setEmail(clientUpdateDto.getEmail());
        clientOptional.get().setUpdatedAt(Instant.now());

        return clientRepository.save(clientOptional.get());
    }

    @Transactional
    public ClientDto delete(UUID id) {
        Optional<Client> client = clientRepository.findById(id);
        if (!client.isPresent()) {
            throw new NotFoundException((format("Cliente com o id: [%s] não encontrado", id)));
        } else {
            if (client.get().isActive() == true) {
                throw new ClientServicesExceptions((format("Cliente: [%s] já foi deletado", client.get().getFullName())));
            }
            client.get().setDeletedAt(Instant.now());
            client.get().setDeleted(true);
            client.get().setActive(false);
        }
        return ClientDto.respostaDto(client);
    }

//        Client client;

//        if (!clientOptional.isPresent()) {
//            client = clientOptional.orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
//        }
//        client = clientUpdateDto.toObject();
//        client.setUpdatedAt(Instant.now());
//        return client;
//    }

//       client public void delete (UUID id){
//            clientRepository.deleteById(id);
//        }
    }

