package com.ifood.ifood_java.service.auth;
import com.ifood.ifood_java.entity.usuario.UsuarioRequest;
import com.ifood.ifood_java.infra.security.TokenService;
import com.ifood.ifood_java.entity.usuario.LoginRequest;
import com.ifood.ifood_java.entity.usuario.Usuario;
import com.ifood.ifood_java.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {

   
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registrarUsuario(UsuarioRequest request) {
    
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setCpf(request.getCpf());
        usuario.setFone_celular(request.getFone_celular());
        usuario.setDt_dascimento((request.getDt_nascimento()));

        usuarioRepository.save(usuario);

        return "Usuário registrado com sucesso";
    }

    public String login(LoginRequest request){
      Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return tokenService.gerarToken(usuario.getEmail());
        }

    }

