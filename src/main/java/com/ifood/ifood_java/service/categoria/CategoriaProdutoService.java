package com.ifood.ifood_java.service.categoria;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ifood.ifood_java.entity.categoria.CategoriaProduto;
import com.ifood.ifood_java.repository.CategoriaProdutoRepository;

@Service
public class CategoriaProdutoService {

    private final CategoriaProdutoRepository repository;

    public CategoriaProdutoService(CategoriaProdutoRepository repository) {
        this.repository = repository;
    }

    public List<CategoriaProduto> listarTodos() {
        return repository.findAll();
    }

    public CategoriaProduto salvar(CategoriaProduto categoria) {
        return repository.save(categoria);
    }

    public CategoriaProduto atualizar(Long id, CategoriaProduto categoria) {
        var existenteOpt = repository.findById(id);

        if (!existenteOpt.isPresent()) {
            throw new RuntimeException("Categoria não encontrada");
        }

        var existente = existenteOpt.get();
        existente.setNome(categoria.getNome());

        return repository.save(existente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
