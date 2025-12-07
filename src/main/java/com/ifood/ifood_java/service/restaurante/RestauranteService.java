package com.ifood.ifood_java.service.restaurante;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ifood.ifood_java.entity.categoria.CategoriaRestaurante;
import com.ifood.ifood_java.entity.endereco.Endereco;
import com.ifood.ifood_java.entity.restaurante.Restaurante;
import com.ifood.ifood_java.entity.restaurante.RestauranteRequest;
import com.ifood.ifood_java.entity.usuario.Usuario;
import com.ifood.ifood_java.repository.CategoriaRestauranteRepository;
import com.ifood.ifood_java.repository.RestauranteRepository;
import com.ifood.ifood_java.repository.UsuarioRepository;
import com.ifood.ifood_java.service.upload.UploadService;

@Service
public class RestauranteService {
    
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private  UploadService uploadService;

    @Autowired
    private CategoriaRestauranteRepository categoriaRestauranteRepository;;

   public Restaurante criarRestaurante(RestauranteRequest request, MultipartFile imagem) {
    
    Long userId = Long.parseLong(
        SecurityContextHolder.getContext().getAuthentication().getName()
    );

    Usuario usuario = usuarioRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    
    CategoriaRestaurante categoria = categoriaRestauranteRepository.findById(request.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

    Endereco endereco = new Endereco();
    endereco.setRua(request.getEndereco().getRua());
    endereco.setNumero(request.getEndereco().getNumero());
    endereco.setBairro(request.getEndereco().getBairro());
    endereco.setCidade(request.getEndereco().getCidade());
    endereco.setEstado(request.getEndereco().getEstado());
    endereco.setCep(request.getEndereco().getCep());

    Restaurante restaurante = new Restaurante();
    restaurante.setNome(request.getNome());
    restaurante.setTelefone(request.getTelefone());
    restaurante.setCnpj(request.getCnpj());
    restaurante.setRaio_entrega(request.getRaio_entrega());
    restaurante.setCategoria(categoria);
    restaurante.setEndereco(endereco);
    restaurante.setUsuario(usuario);

     if (imagem != null && !imagem.isEmpty()) {
            String caminho = uploadService.salvarImagem(imagem);
            restaurante.setUrlImagem(caminho); 
        }
  
    restauranteRepository.save(restaurante);

    return restaurante;
}

public List<Restaurante> mostrarRestaurante() {

    Long userId = Long.parseLong(
        SecurityContextHolder.getContext().getAuthentication().getName()
    );

    return restauranteRepository.findAllByUsuarioIdUsuario(userId);
}

public void deletarRestaurante(Long id) {
        if (!restauranteRepository.existsById(id)) {
            throw new RuntimeException("Restaurante não encontrado");
        }
        restauranteRepository.deleteById(id);
    }



//mobile

 public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll();
    }
}
