package br.edu.ifsul.cstsi.boa_acao.autenticacao;

import br.edu.ifsul.cstsi.boa_acao.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //adiciona ao Contexto do app como um REST Controller
@RequestMapping("api/v1/autenticacao") //Endpoint padrão da classe
public class AutenticacaoController {

    private final AuthenticationManager manager;

    //injeção de dependências
    public AutenticacaoController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostMapping("/login")
    public ResponseEntity<String> efetuaLogin(@RequestBody UsuarioAutenticacaoDto data) {
        var authenticationDTO = new UsernamePasswordAuthenticationToken(data.email(), data.senha()); //converte o DTO em DTO do Spring Security

        var authentication = manager.authenticate(authenticationDTO); //autentica o usuário (esse objeto contém o usuário e a senha)
        //var tokenJWT = tokenService.geraToken((Usuario) authentication.getPrincipal()); //gera o token JWT para enviar na response
        return ResponseEntity.ok("autenticou" + authentication.getPrincipal()); //envia a response com o token JWT
    }
}
