package com.ifood.ifood_java.service.auth;
import com.ifood.ifood_java.entity.usuario.UsuarioRequest;
import com.ifood.ifood_java.infra.security.TokenService;
import com.ifood.ifood_java.controller.auth.LoginRequest;
import com.ifood.ifood_java.controller.auth.ResponseLoginRequest;
import com.ifood.ifood_java.entity.endereco.Endereco;
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

        Endereco endereco = new Endereco();
        endereco.setRua(request.getEndereco().getRua());
        endereco.setNumero(request.getEndereco().getNumero());
        endereco.setBairro(request.getEndereco().getBairro());
        endereco.setCidade(request.getEndereco().getCidade());
        endereco.setEstado(request.getEndereco().getEstado());
        endereco.setCep(request.getEndereco().getCep());

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setCpf(request.getCpf());
        usuario.setFoneCelular(request.getFone_celular());
        usuario.setDtNascimento((request.getDt_nascimento()));
        usuario.setEndereco(endereco);

        usuarioRepository.save(usuario);

        return "Usuário registrado com sucesso";
    }

 public ResponseLoginRequest login(LoginRequest request) {

    Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
        throw new RuntimeException("Senha inválida");
    }

    
    String token = tokenService.gerarToken(usuario);

    return new ResponseLoginRequest(
            token,
            "Bearer",
            usuario.getIdUsuario(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getCpf(),
            usuario.getDtNascimento(),
            usuario.getFoneCelular()
    );
}




}

