package br.com.profitness.controllers;

import br.com.profitness.dto.client.ClientDto;
import br.com.profitness.dto.client.ClientInput;
import br.com.profitness.dto.client.ClientUpdateDto;
import br.com.profitness.exceptions.ClientServicesExceptions;
import br.com.profitness.models.Client;
import br.com.profitness.responses.Response;
import br.com.profitness.services.ClientServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.webjars.NotFoundException;

import java.util.*;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClientController {


        private final ClientServices clientServices;

        public ClientController(final ClientServices clientServices){
            this.clientServices = Objects.requireNonNull(clientServices);
        }

        @PostMapping(value = "/register")
        @ResponseStatus(HttpStatus.CREATED)
        public ClientDto registerClient(@RequestBody @Valid ClientInput clienteInput) {
            if(clientServices.existsByCpf(clienteInput.getCpf())){
                System.out.println("erros");
            }
               Client cliente = clientServices.toSave(clienteInput);
            return ClientDto.respostaDto(cliente);
        }

        @GetMapping(value = "/searchAll")
        public ResponseEntity<Response<List<Client>>> findAllClients(){
            List<Client> clients = null;
            clients = clientServices.findAllCustomer();
            Response<List<Client>> clientResponse = new Response<>();
            clientResponse.setData(clients);
            return ResponseEntity.status(HttpStatus.OK).body(clientResponse);
        }

        @GetMapping(value = "/searchConsumer/{id}")
        public ResponseEntity<Response<ClientDto>> findById(@PathVariable UUID id) {
            Response<ClientDto> response = new Response<ClientDto>();
            ClientDto clientResponse;
            try {
                clientResponse = clientServices.findById(id);
            }
            catch(NotFoundException e){
                response.setErrors(Collections.singletonList(e.getMessage()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            catch(HttpClientErrorException ce){
                response.setErrors(Collections.singletonList(ce.getMessage()));
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
            }
            response.setData(clientResponse);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }


        @DeleteMapping(value = "/deleteConsumer/{id}")
        public ResponseEntity<Response<ClientDto>> deletarCliente(@PathVariable UUID id) {
            Response<ClientDto> response = new Response<ClientDto>();
            ClientDto clientResponse;
            try {
                clientResponse = clientServices.delete(id);

            } catch (NotFoundException e) {
                response.setErrors(Collections.singletonList(e.getMessage()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } catch (ClientServicesExceptions e) {
                response.setErrors(Collections.singletonList(e.getMessage()));
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
            response.setData(clientResponse);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        @PutMapping(value = "/updateConsumer/{id}")
        public ResponseEntity<Response<ClientDto>> updateCustomerData(@PathVariable UUID id, @RequestBody @Valid ClientUpdateDto clientUpdate) {
            Response<ClientDto> response = new Response<ClientDto>();
            ClientDto clientDto;
            try {
                Client client = clientServices.updateClient(id, clientUpdate);
                clientDto = client.clienteDto();
            } catch (NotFoundException e){
                response.setErrors(Collections.singletonList(e.getMessage()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

            }  catch (NoSuchElementException e){
                response.setErrors(Collections.singletonList(e.getMessage()));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            response.setData(clientDto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

}
