package br.edu.ifsul.cstsi.boa_acao.item;

import br.edu.ifsul.cstsi.boa_acao.BaseAPIIntegracaoTest;
import br.edu.ifsul.cstsi.boa_acao.BoaAcaoApplication;
import br.edu.ifsul.cstsi.boa_acao.model.Item;
import br.edu.ifsul.cstsi.boa_acao.model.ItemDto;
import br.edu.ifsul.cstsi.boa_acao.model.ItemDtoPost;
import br.edu.ifsul.cstsi.boa_acao.model.ItemDtoPut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
    Realiza o teste de integração da unidade ItemController.
    O teste de integração testa todas as camadas HTTP request -> Security -> Controller -> Repository -> MariaDB.
 */

@SpringBootTest(classes = BoaAcaoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@ActiveProfiles("test")
public class ItemControllerIntegracaoTest extends BaseAPIIntegracaoTest {

    private ResponseEntity<ItemDto> getItem(String url) {
        return get(url, ItemDto.class);
    }

    private ResponseEntity<List<ItemDto>> getItensList(String url) {
        var headers = getHeaders();

        return rest.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {
                });
    }

    @Test
    @DisplayName("Espera uma página com 5 objetos e uma página de 5 objetos")
    public void findAllEsperaUmaPaginaCom5ObjetosEUmaPaginaDe5Objetos() {
        // ACT
        var data = getItensList("/api/v1/itens").getBody();

        // ASSERT
        assertNotNull(data);
        assertEquals(5, data.size());

        // ACT
        var dataPage = getItensList("/api/v1/itens?page=0&size=5").getBody();

        // ASSERT
        assertNotNull(dataPage);
        assertEquals(5, dataPage.size());
    }

    @Test
    public void findByIdEsperaUmObjetoPorIdPesquisadoENotFoudParaIdInexistente() {
        // ACT + ASSERT
        assertNotNull(getItem("/api/v1/itens/1"));
        assertNotNull(getItem("/api/v1/itens/2"));
        assertNotNull(getItem("/api/v1/itens/3"));
        assertNotNull(getItem("/api/v1/itens/4"));
        assertNotNull(getItem("/api/v1/itens/5"));
        assertEquals(HttpStatus.NOT_FOUND, getItem("/api/v1/itens/100000").getStatusCode());
    }

    @Test
    public void findByDescricaoEsperaUmObjetoPorDescricaoPesquisada() {
        // ACT + ASSERT
        assertEquals(1, getItensList("/api/v1/itens/descricao/arroz").getBody().size());
        assertEquals(1, getItensList("/api/v1/itens/descricao/feij").getBody().size());
        assertEquals(1, getItensList("/api/v1/itens/descricao/leite").getBody().size());
        assertEquals(1, getItensList("/api/v1/itens/descricao/macar").getBody().size());
        assertEquals(1, getItensList("/api/v1/itens/descricao/sabon").getBody().size());

        // ACT + ASSERT (o teu controller retorna 404 quando não encontra, o do professor retorna 204)
        assertEquals(HttpStatus.NOT_FOUND, getItensList("/api/v1/itens/descricao/xxx").getStatusCode());
    }

    @Test
    public void testInsertEspera201CreatedE404ENotFound() {
        // ARRANGE
        var itemDtoPost = new ItemDtoPost(
                "Teste",
                "un",
                "teste"
        );

        // ACT
        var response = post("/api/v1/itens", itemDtoPost, null);

        // ASSERT
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        var location = response.getHeaders().get("location").get(0);
        var i = getItem(location).getBody();
        assertNotNull(i);
        assertEquals("Teste", i.descricao());
        assertEquals("un", i.unidade());
        assertEquals("teste", i.tipo());

        // ACT
        delete(location, null);

        // ASSERT
        assertEquals(HttpStatus.NOT_FOUND, getItem(location).getStatusCode());
    }

    @Test
    public void testUpdateEspera200OkE404ENotFound() {
        // ARRANGE
        var itemDtoPost = new ItemDtoPost(
                "Teste",
                "un",
                "teste"
        );

        var responsePost = post("/api/v1/itens", itemDtoPost, null);
        assertEquals(HttpStatus.CREATED, responsePost.getStatusCode());
        var location = responsePost.getHeaders().get("location").get(0);
        var iDto = getItem(location).getBody();
        assertNotNull(iDto);
        assertEquals("Teste", iDto.descricao());
        assertEquals("un", iDto.unidade());
        assertEquals("teste", iDto.tipo());

        //prepara um DTO para o PUT
        var itemDtoPut = new ItemDtoPut(
                "Teste Modificado",
                "kg",
                "Higiene"
        );

        // ACT
        var responsePUT = put(location, itemDtoPut, ItemDto.class);

        // ASSERT
        assertEquals(HttpStatus.OK, responsePUT.getStatusCode());
        assertEquals("Teste Modificado", responsePUT.getBody().descricao());
        assertEquals("kg", responsePUT.getBody().unidade());
        assertEquals("Higiene", responsePUT.getBody().tipo());

        // ACT
        delete(location, null);

        // ASSERT
        assertEquals(HttpStatus.NOT_FOUND, getItem(location).getStatusCode());
    }

    @Test
    public void testDeleteEspera200OkE404NotFound() {
        // ARRANGE
        var item = new Item();
        item.setDescricao("Teste");
        item.setUnidade("un");
        item.setTipo("teste");
        var responsePost = post("/api/v1/itens", item, null);
        assertEquals(HttpStatus.CREATED, responsePost.getStatusCode());
        var location = responsePost.getHeaders().get("location").get(0);
        var i = getItem(location).getBody();
        assertNotNull(i);
        assertEquals("Teste", i.descricao());
        assertEquals("un", i.unidade());

        // ACT
        var responseDelete = delete(location, null);

        // ASSERT
        assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, getItem(location).getStatusCode());
    }

    @Test
    public void testGetNotFoundEspera404NotFound() {
        // ARRANGE + ACT
        var response = getItem("/api/v1/itens/1100");

        // ASSERT
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}