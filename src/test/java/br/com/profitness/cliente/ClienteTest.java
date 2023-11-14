package br.com.profitness.cliente;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class ClienteTest {

    @Test
    public void testDadoUmPostQuandoCadastroClienteEntaoObtenhaStatusCode201(){
        //Configurar o caminho comum para a API Rest
        RestAssured.baseURI = "http://localhost";
        port = 8085;
        basePath = "/customers";


        //Cadastrar Cliente
        given()
                .body("{\n" +
                        "    \"fullName\": \"Darth Vader\",\n" +
                        "    \"birthDate\": \"21/05/1789\",\n" +
                        "    \"cpf\": \"738.268.713-91\",\n" +
                        "    \"rg\": \"25.469.030-0\",\n" +
                        "    \"ddd\": \"92\",\n" +
                        "    \"phoneNumber\": \"15800-2000\",\n" +
                        "    \"email\": \"soldier@galaxy.com\",\n" +
                        "    \"sex\": \"masculino\",\n" +
                        "    \"address\": {\n" +
                        "        \"id\": \"1c4ca2b5-9fe9-4d81-a97d-24efce45670c\"\n" +
                        "    },\n" +
                        "    \"active\": true\n" +
                        "}")
                .contentType(ContentType.JSON)
        .when()
                .post("/register")
        .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void testDadoUmGetQuandoBuscarTodosClienteEntaoObtenhaStatus200(){
        //Configurar o caminho comum para a API Rest
        baseURI = "http://localhost";
        port = 8085;
        basePath = "/customers";

        //Configurar o buscar todos
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/buscarTodos")
        .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testDadoUmGetQuandoBuscarClientePeloIdEntaoObtenhaStatus200(){
        baseURI = "http://localhost";
        port = 8085;
        basePath = "/customers";

        //Configurar buscar pelo id
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/buscarCliente/05ef89fc-4efd-41f3-bed3-e3a898996e96")
        .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testDadoUmPutQuandoAtualizarClientePeloIdEntaoObtenhaStatus204(){
        baseURI = "http://localhost";
        port = 8085;
        basePath = "/customers";

        given()
                .body(" {\n" +
                        "  \"fullName\": \"Heloise Alana Larissa Aparício\",\n" +
                        "  \"ddd\": \"75\",\n" +
                        "  \"phoneNumber\": \"99263-0960\",\n" +
                        "  \"email\": \"heloise_alana@icloud.com\",\n" +
                        "  \"active\": \"true\"\n" +
                        "} ")
                .contentType(ContentType.JSON)
        .when()
                .put("/atualizarCliente/f6d593b8-721e-405f-80a5-78dc41dc7787")
        .then()
                .assertThat()
                .statusCode(204);
    }

    @Test
    public void testDadoUmDeleteQuandoDeletarUmClientePeloIdEntaoObtenhaStatus200(){
        baseURI = "http://localhost";
        port = 8085;
        basePath = "/customers";

        //Configurar buscar pelo id
        given()
                .contentType(ContentType.JSON)
        .when()
                .delete("/deletarCliente/ea6974ae-5160-41ab-8cd0-6cc62398485e")
        .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testDadoUmDeleteQuandoDeletarUmClienteQueJaFoiDeletadoObtenhaStatus409(){
        baseURI = "http://localhost";
        port = 8085;
        basePath = "/customers";

        //Configurar buscar pelo id
        given()
                .contentType(ContentType.JSON)
        .when()
                .delete("/deletarCliente/ea6974ae-5160-41ab-8cd0-6cc62398485e")
        .then()
                .assertThat()
                .statusCode(409);
    }
}
