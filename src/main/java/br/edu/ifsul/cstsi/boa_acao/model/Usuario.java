package br.edu.ifsul.cstsi.boa_acao.model;

import br.edu.ifsul.cstsi.boa_acao.perfil.Perfil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Usuario implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis;
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "id_usuario")
    private Long id;
    private String nome;
    private String sobrenome;
    @Column(unique = true)
    private String email;
    private String tipo;
    private String senha;
    private boolean isConfirmado = false;
    private boolean situacao = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_perfis",
            joinColumns = @JoinColumn(name = "usuarios_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "perfis_id", referencedColumnName = "id"))
    private List<Perfil> perfis;

    //Método utilitário para gerar o Hash da senha
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123"));
    }
}
