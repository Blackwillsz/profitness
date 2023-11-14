package br.com.profitness.controllers;

import br.com.profitness.dto.address.AddressDto;
import br.com.profitness.dto.address.AddressInput;
import br.com.profitness.dto.address.AddressUpdateInput;
import br.com.profitness.exceptions.AddressExceptions;
import br.com.profitness.models.Address;
import br.com.profitness.responses.Response;
import br.com.profitness.services.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "/address", produces = "application/json")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AddressController {

    private final AddressService addressService;

    public AddressController(final AddressService addressService) {
        this.addressService = Objects.requireNonNull(addressService);
    }

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDto saveAddress(@RequestBody @Valid AddressInput addressInput){
        Address address = addressService.save(addressInput);
        return AddressDto.responseDto(address);
    }

    @GetMapping(value = "/searchAll")
    public ResponseEntity<Response<List<Address>>> findAllAddress(){
        List<Address> address = addressService.findAllAddress();
        Response<List<Address>> addressResponse = new Response<>();
        addressResponse.setData(address);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
    }

    @GetMapping(value = "/searchAddress/{id}")
    public ResponseEntity<Response<AddressDto>> findById(@PathVariable UUID id) {
        Response<AddressDto> response = new Response<AddressDto>();
        AddressDto addressDto;
        try {
            addressDto = addressService.findById(id);
        }
        catch(NotFoundException e){
            response.setErrors(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch(AddressExceptions ce){
            response.setErrors(Collections.singletonList(ce.getMessage()));
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }
        response.setData(addressDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/updateAddress/{id}")
    public AddressDto updateAddress(@PathVariable UUID id, @RequestBody @Valid AddressUpdateInput updateInput) {
        Address address = addressService.updateAddress(updateInput, id);
        return AddressDto.responseDto(address);
    }

    @DeleteMapping(value = "/deleteAddress/{id}")
    public ResponseEntity<Response<AddressDto>> deletarCliente(@PathVariable UUID id) {
        Response<AddressDto> response = new Response<AddressDto>();
        AddressDto addressResponse;
        try {
            addressResponse = addressService.delete(id);

        } catch (NotFoundException e) {
            response.setErrors(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (AddressExceptions e) {
            response.setErrors(Collections.singletonList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        response.setData(addressResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
