package com.ifood.ifood_java.service.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ifood.ifood_java.entity.categoria.CategoriaProdutos;
import com.ifood.ifood_java.entity.produtos.Produtos;
import com.ifood.ifood_java.entity.produtos.ProdutosRequest;
import com.ifood.ifood_java.entity.restaurante.Restaurante;
import com.ifood.ifood_java.entity.usuario.Usuario;
import com.ifood.ifood_java.repository.CategoriaProdutosRepository;
import com.ifood.ifood_java.repository.ProdutosRepository;
import com.ifood.ifood_java.repository.RestauranteRepository;
import com.ifood.ifood_java.repository.UsuarioRepository;
import com.ifood.ifood_java.service.upload.UploadService;

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

    @Autowired
    private UploadService uploadService;

    // ---------------------------------------------------------------------
    // 🔥 CRIAR PRODUTO DO RESTAURANTE DO USUÁRIO LOGADO
    // ---------------------------------------------------------------------
    public Produtos criarProdutos(ProdutosRequest request, MultipartFile imagem) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if ("anonymousUser".equals(username)) {
            throw new RuntimeException("Usuário precisa estar logado para criar produtos");
        }

        Long userId = Long.parseLong(username);

        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Restaurante restaurante = restauranteRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        CategoriaProdutos categoriaProdutos = categoriaProdutosRepository
                .findByNome(request.getCategoria())
                .orElseGet(() -> {
                    CategoriaProdutos nova = new CategoriaProdutos();
                    nova.setNome(request.getCategoria());
                    return categoriaProdutosRepository.save(nova);
                });

        Produtos produtos = new Produtos();
        produtos.setNome(request.getNome());
        produtos.setDescricao(request.getDescricao());
        produtos.setPreco(request.getPreco());
        produtos.setAtivo(request.getAtivo());
        produtos.setCategoria(categoriaProdutos);
        produtos.setRestaurante(restaurante);

        if (imagem != null && !imagem.isEmpty()) {
            String url = uploadService.salvarImagem(imagem);
            produtos.setUrlImagem(url);
        }

        return produtosRepository.save(produtos);
    }

    // ---------------------------------------------------------------------
    // 🔥 LISTAR PRODUTOS DO RESTAURANTE DO USUÁRIO LOGADO
    // ---------------------------------------------------------------------
    public List<Produtos> mostrarProdutos() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if ("anonymousUser".equals(username)) {
            // Usuário anônimo -> retornar lista vazia
            return List.of();
        }

        Long userId = Long.parseLong(username);

        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Restaurante restaurante = restauranteRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        return produtosRepository.findAllByRestauranteIdRestaurante(restaurante.getIdRestaurante());
    }

    // ---------------------------------------------------------------------
    // 🔥 LISTAR PRODUTOS DE QUALQUER RESTAURANTE (CLIENTE CLICOU EM UM RESTAURANTE)
    // ---------------------------------------------------------------------
    public List<Produtos> listarProdutosPorRestaurante(Long restauranteId) {
        return produtosRepository.findAllByRestauranteIdRestaurante(restauranteId);
    }

    // ---------------------------------------------------------------------
    // 🔥 DETALHE DO PRODUTO
    // ---------------------------------------------------------------------
    public Produtos buscarProdutoPorId(Long produtoId) {
        return produtosRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

}
