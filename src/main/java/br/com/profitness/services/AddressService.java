package br.com.profitness.services;

import br.com.profitness.dto.address.AddressDto;
import br.com.profitness.dto.address.AddressInput;
import br.com.profitness.dto.address.AddressUpdateInput;
import br.com.profitness.exceptions.AddressExceptions;
import br.com.profitness.models.Address;
import br.com.profitness.repositories.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(final AddressRepository addressRepository) {
        this.addressRepository = Objects.requireNonNull(addressRepository);
    }


    @Transactional
    public Address save(AddressInput addressInput) {
        var address = new Address();
        if (addressInput.getActive().equalsIgnoreCase("true")) {
            address = addressInput.toObject();
            address.setCreatedAt(Instant.now());
            address.setDeleted(false);
        }
        return addressRepository.save(address);
    }

    public List<Address> findAllAddress() {
        return addressRepository.findAll();
    }

    public AddressDto findById(UUID id) {
        Optional<Address> addressOptional = addressRepository.findById(id);

        if (!addressOptional.isPresent()) {
            throw new NotFoundException((format("Endereço com o id: [%s] não encontrado", id)));
        }
        AddressDto dtoResponse = AddressDto.responseDto(addressOptional.get());
        return dtoResponse;
    }

    @Transactional
    public Address updateAddress(AddressUpdateInput updateInput, UUID id) {
        Optional<Address> addressOptional = addressRepository.findById(id);

        if (!addressOptional.isPresent()) {
            addressOptional.orElseThrow(() -> new NotFoundException("Endereço não encontrado."));
        }

        addressOptional.get().setStreet(Optional
                .ofNullable(updateInput.getStreet())
                .orElse(addressOptional.get().getStreet())
        );
        addressOptional.get().setNumber(Optional.ofNullable(updateInput.getNumber()).orElse(addressOptional.get().getNumber()));
        addressOptional.get().setComplement(Optional.ofNullable(updateInput.getComplement()).orElse(addressOptional.get().getComplement()));
        addressOptional.get().setNeigborhood(Optional.ofNullable(updateInput.getNeigborhood()).orElse(addressOptional.get().getNeigborhood()));
        addressOptional.get().setCity(Optional.ofNullable(updateInput.getCity()).orElse(addressOptional.get().getCity()));
        addressOptional.get().setCountry(Optional.ofNullable(updateInput.getCountry()).orElse(addressOptional.get().getCountry()));
        addressOptional.get().setReference(Optional.ofNullable(updateInput.getReference()).orElse(addressOptional.get().getReference()));
        addressOptional.get().setState(Optional.ofNullable(updateInput.getState()).orElse(addressOptional.get().getState()));
        addressOptional.get().setZipCode(Optional.ofNullable(updateInput.getZipCode()).orElse(addressOptional.get().getZipCode()));
        addressOptional.get().setUpdatedAt(Instant.now());

        return addressRepository.save(addressOptional.get());
    }

    @Transactional
    public AddressDto delete(UUID id) {
        Optional<Address> address = addressRepository.findById(id);
        if (!address.isPresent()) {
            throw new NotFoundException((format("Endereço com o id: [%s] não encontrado", id)));
        } else {
            if (address.get().isActive() == false) {
                throw new AddressExceptions((format("Endereço: [%s] já foi deletado", address.get().getStreet())));
            }
            address.get().setDeletedAt(Instant.now());
            address.get().setDeleted(true);
            address.get().setActive(false);
        }
        return AddressDto.responseDto(address);
    }
}