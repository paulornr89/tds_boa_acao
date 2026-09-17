package br.edu.ifsul.cstsi.boa_acao.controller;

import br.edu.ifsul.cstsi.boa_acao.model.Item;
import br.edu.ifsul.cstsi.boa_acao.model.ItemDto;
import br.edu.ifsul.cstsi.boa_acao.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/v1/itens")
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public ResponseEntity <List<ItemDto>> findAll() {
        return ResponseEntity.ok(
                itemRepository.findAll().stream()
                        .map(ItemDto::new)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> findById(@PathVariable(value = "id") Long id) {
        var item = itemRepository.findById(id);
        if(item.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item.map(ItemDto::new).get());
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<ItemDto>> findByDescricao(@PathVariable(value = "descricao") String descricao) {
        List<Item> itens = itemRepository.findByDescricaoStartingWithIgnoreCaseOrderByDescricaoAsc(descricao);
        if(itens.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<ItemDto> dtos = itens.stream()
                .map(ItemDto::new)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ItemDto> insert(@RequestBody ItemDto itemDto, UriComponentsBuilder uriBuilder) {
        var item = itemRepository.save(new Item(
                null,
                itemDto.descricao(),
                itemDto.unidade(),
                itemDto.tipo()
        ));
        var location = uriBuilder.path("api/v1/itens/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(location).body(new ItemDto(item));
    }

    @PutMapping("{id}")
    public ResponseEntity<ItemDto> update(@PathVariable(value = "id") Long id, @RequestBody ItemDto itemDto) {
        var item = itemRepository.save(new Item(
                id,
                itemDto.descricao(),
                itemDto.unidade(),
                itemDto.tipo()
        ));
        return item != null ?
                ResponseEntity.ok(new ItemDto(item)) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
