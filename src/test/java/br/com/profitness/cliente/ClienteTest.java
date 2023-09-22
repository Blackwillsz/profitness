package br.com.profitness.cliente;

import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ClienteTest {

    @Test
    public void testDadoUmPostQuandoCadastroClienteEntaoObtenhaStatusCode201(){
        //Configurar o caminho comum para a API Rest
        baseURI = "http://localhost";
        port = 8085;
        basePath = "/customers";

        //Cadastrar Cliente
        given()
                .body(" {\n" +
                        "\t\"nomeCompleto\" : \"Stormtropper\",\n" +
                        "\t\"dataNascimento\" : \"21/05/1790\",\n" +
                        "\t\"cpf\" : \"226.318.238-18\",\n" +
                        "\t\"rg\" : \"18.753.002-25\",\n" +
                        "\t\"ddd\" : \"92\",\n" +
                        "\t\"numeroTelefone\" : \"15800-2000\",\n" +
                        "\t\"email\" : \"soldier@galaxy.com\",\n" +
                        "\t\"sexo\" : \"masculino\",\n" +
                        "\t\"endereco\" : \"Estrela da Morte\",\n" +
                        "\t\"ativo\" : \"true\"\n" +
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
        basePath = "/clientes";

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
        basePath = "/clientes";

        //Configurar buscar pelo id
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("/buscarCliente/96d881e0-4f94-4799-b132-95d78424097f")
        .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testDadoUmPutQuandoAtualizarClientePeloIdEntaoObtenhaStatus204(){
        baseURI = "http://localhost";
        port = 8085;
        basePath = "/clientes";

        given()
                .body(" {\n" +
                        "  \"nomeCompleto\": \"Willian\",\n" +
                        "  \"ddd\": \"17\",\n" +
                        "  \"numeroTelefone\": \"66417-9858\",\n" +
                        "  \"email\": \"cavaleirojedi@gmail.com\",\n" +
                        "  \"ativo\": \"true\"\n" +
                        "} ")
                .contentType(ContentType.JSON)
        .when()
                .put("/atualizarCliente/96d881e0-4f94-4799-b132-95d78424097f")
        .then()
                .assertThat()
                .statusCode(204);
    }

    @Test
    public void testDadoUmDeleteQuandoDeletarUmClientePeloIdEntaoObtenhaStatus200(){
        baseURI = "http://localhost";
        port = 8085;
        basePath = "/clientes";

        //Configurar buscar pelo id
        given()
                .contentType(ContentType.JSON)
        .when()
                .delete("/2c54d808-a7ab-49a0-8db1-92e6f336582b")
        .then()
                .assertThat()
                .statusCode(200);
    }
}
