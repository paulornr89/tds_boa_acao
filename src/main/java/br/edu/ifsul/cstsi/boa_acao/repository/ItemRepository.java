package br.edu.ifsul.cstsi.boa_acao.repository;

import br.edu.ifsul.cstsi.boa_acao.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

//@RepositoryRestResource(path = "itens", collectionResourceRel = "itens")
@RepositoryRestResource(exported = false)
public interface ItemRepository extends JpaRepository<Item, Long> {
//    @Query(value = "SELECT * FROM itens i WHERE i.descricao LIKE CONCAT(:descricao, '%') ORDER BY i.descricao", nativeQuery = true)
//    List<Item> findByDescricaoQuerySQL(@Param("descricao") String descricao);
    List<Item> findByDescricaoStartingWithIgnoreCaseOrderByDescricaoAsc(String descricao);
}
