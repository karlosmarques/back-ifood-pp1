package com.ifood.ifood_java.service.produto;
import org.springframework.stereotype.Service;

import com.ifood.ifood_java.entity.categoria.CategoriaProduto;
import com.ifood.ifood_java.entity.produto.Produto;
import com.ifood.ifood_java.entity.produto.ProdutoRequest;
import com.ifood.ifood_java.repository.CategoriaProdutoRepository;
import com.ifood.ifood_java.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepo;
    private final CategoriaProdutoRepository categoriaRepo;

    public ProdutoService(
        ProdutoRepository produtoRepo,
        CategoriaProdutoRepository categoriaRepo
    ) {
        this.produtoRepo = produtoRepo;
        this.categoriaRepo = categoriaRepo;
    }

    public Produto criar(ProdutoRequest req) {

        CategoriaProduto categoria = categoriaRepo.findById(req.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Produto p = new Produto();
        p.setNome(req.getNome());
        p.setDescricao(req.getDescricao());
        p.setPreco(req.getPreco());
        p.setCategoria(categoria);

        return produtoRepo.save(p);
    }

    public Object listar() {
        return produtoRepo.findAll();
    }
    
}
