package br.edu.ifsul.cstsi.boa_acao.repository;

import br.edu.ifsul.cstsi.boa_acao.model.Organizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "organizacoes", collectionResourceRel = "organizacoes")
public interface OrganizacaoRepository extends JpaRepository<Organizacao, Long> {
}
