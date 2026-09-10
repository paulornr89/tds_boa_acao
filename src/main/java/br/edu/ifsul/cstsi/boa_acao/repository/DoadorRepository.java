package br.edu.ifsul.cstsi.boa_acao.repository;

import br.edu.ifsul.cstsi.boa_acao.model.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "doadores", collectionResourceRel = "doadores")
public interface DoadorRepository extends JpaRepository<Doador, Long> {
}
