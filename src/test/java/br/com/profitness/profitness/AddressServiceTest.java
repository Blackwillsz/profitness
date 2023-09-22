package br.com.profitness.profitness;


import br.com.profitness.services.AddressService;
import org.junit.Test;

public class AddressServiceTest {

    @Test
    public void testGivenPostShouldReturnNewAddress(){

        var expectedStreet = "rua xv de novembro";
        var expectedNumber = 3230;
        var expectedComplement = "Edificio Atlas";
        var expectedNeigborhood = "centro";
        var expectedZipCode = 15015110;
        var expectedCity = "Rio Preto";
        var expectedState = "sp";
        var expectedCountry = "Brasil";
        var expectedActive = true;

    }
}
