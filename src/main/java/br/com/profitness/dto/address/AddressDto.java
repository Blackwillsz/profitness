package br.com.profitness.dto.address;

import br.com.profitness.models.Address;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressDto {

    private String street;
    private String neigborhood;
    private String city;
    private String country;
    private boolean active;

    public AddressDto(){}

    public static AddressDto responseDto(Address address){
        return new AddressDto(
                address.getStreet(),
                address.getNeigborhood(),
                address.getCity(),
                address.getCountry(),
                address.isActive());
    }

    public static AddressDto responseDto(Optional<Address> address) {
        return new AddressDto(
                address.get().getStreet(),
                address.get().getNeigborhood(),
                address.get().getCity(),
                address.get().getCountry(),
                address.get().isActive());
    }

//    public static AddressDto responseDto(Address address){
//        return new AddressDto(address.getStreet(), address.getNeigborhood(), address.getCity(), address.getCountry(), address.isActive());
//    }
}
