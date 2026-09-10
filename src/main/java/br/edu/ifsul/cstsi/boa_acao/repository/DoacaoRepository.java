package br.edu.ifsul.cstsi.boa_acao.repository;

import br.edu.ifsul.cstsi.boa_acao.model.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "doacoes", collectionResourceRel = "doacoes")
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
}
