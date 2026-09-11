package br.edu.ifsul.cstsi.boa_acao.controller;

import br.edu.ifsul.cstsi.boa_acao.model.Item;
import br.edu.ifsul.cstsi.boa_acao.model.ItemDto;
import br.edu.ifsul.cstsi.boa_acao.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public String insert(@RequestBody Item item) {
        return "insert " + item;
    }

    @PutMapping("{id}")
    public String update(@PathVariable(value = "id") Long id, @RequestBody Item item) {
        return "update " + item;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        return "delete " + id;
    }

}
