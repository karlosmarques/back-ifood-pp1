package com.ifood.ifood_java.service.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ifood.ifood_java.entity.categoria.CategoriaProdutos;
import com.ifood.ifood_java.entity.produtos.Produtos;
import com.ifood.ifood_java.entity.produtos.ProdutosRequest;
import com.ifood.ifood_java.entity.restaurante.Restaurante;
import com.ifood.ifood_java.entity.usuario.Usuario;
import com.ifood.ifood_java.repository.CategoriaProdutosRepository;
import com.ifood.ifood_java.repository.ProdutosRepository;
import com.ifood.ifood_java.repository.RestauranteRepository;
import com.ifood.ifood_java.repository.UsuarioRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CategoriaProdutosRepository categoriaProdutosRepository;


    public Produtos criarPordutos(ProdutosRequest request){

        Long userId = Long.parseLong(
        SecurityContextHolder.getContext().getAuthentication().getName()
    );

    Usuario usuario = usuarioRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    Restaurante restaurante = restauranteRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

    CategoriaProdutos categoriaProdutos = categoriaProdutosRepository.findByNome(request.getNome())
                .orElseThrow(() ->  new RuntimeException("Categoria não encontrada"));

        Produtos produtos = new Produtos();
        produtos.setNome(request.getNome());
        produtos.setDescricao(request.getDescricao());
        produtos.setPreco(request.getPreco());
        produtos.setAtivo(request.getAtivo());
        produtos.setCategoria(categoriaProdutos);
        produtos.setRestaurante(restaurante);
    
        return produtosRepository.save(produtos);
        
}

public List<Produtos> mostrarProdutos(){

     Long userId = Long.parseLong(
        SecurityContextHolder.getContext().getAuthentication().getName()
    );
    Usuario usuario = usuarioRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    
    Restaurante restaurante = restauranteRepository.findByUsuario(usuario)
        .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
    
    return produtosRepository.findAllByRestauranteIdRestaurante(restaurante.getIdRestaurante());
}


    
}
