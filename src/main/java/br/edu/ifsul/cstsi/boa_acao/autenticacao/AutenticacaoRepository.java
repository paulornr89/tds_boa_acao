package br.edu.ifsul.cstsi.boa_acao.autenticacao;

import br.edu.ifsul.cstsi.boa_acao.model.Usuario;
import org.springframework.data.repository.Repository;

public interface AutenticacaoRepository extends Repository<Usuario,Long> {
    Usuario findByEmail(String email);
}