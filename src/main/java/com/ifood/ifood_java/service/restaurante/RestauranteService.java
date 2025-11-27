package com.ifood.ifood_java.service.restaurante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ifood.ifood_java.entity.restaurante.Restaurante;
import com.ifood.ifood_java.entity.restaurante.RestauranteRequest;
import com.ifood.ifood_java.entity.usuario.Usuario;
import com.ifood.ifood_java.repository.RestauranteRepository;
import com.ifood.ifood_java.repository.UsuarioRepository;

@Service
public class RestauranteService {
    
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired UsuarioRepository usuarioRepository;

   public RestauranteRequest criarRestaurante(RestauranteRequest request) {
    
    Long userId = Long.parseLong(
        SecurityContextHolder.getContext().getAuthentication().getName()
    );

    Usuario usuario = usuarioRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

   
    Restaurante restaurante = new Restaurante();
    restaurante.setNome(request.getNome());
    restaurante.setTelefone(request.getTelefone());
    restaurante.setCnpj(request.getCnpj());
    restaurante.setRaio_entrega(request.getRaio_entrega());
    restaurante.setUsuario(usuario);
  
    restauranteRepository.save(restaurante);

    return request;
}

}
