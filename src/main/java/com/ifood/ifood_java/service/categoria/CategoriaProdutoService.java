package com.ifood.ifood_java.service.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifood.ifood_java.model.CategoriaProduto;
import com.ifood.ifood_java.repository.CategoriaProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaProdutoService {

    @Autowired
    private CategoriaProdutoRepository repository;

  @Transactional
    public CategoriaProduto save(CategoriaProduto categoriaProduto){
        categoriaProduto.setHabilitado(Boolean.TRUE);
        return repository.save(categoriaProduto);
    }

    public List<CategoriaProduto> listarTodos(){
        return repository.findAll();
    }

    public CategoriaProduto obterPorID(Long id){
        // Corrigido: evita erro caso o id não exista
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada para id: " + id));
    }

    @Transactional
    public void update(Long id, CategoriaProduto categoriaProdutoAlterado){
        CategoriaProduto categoriaProduto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada para id: " + id));
        
        categoriaProduto.setDescricao(categoriaProdutoAlterado.getDescricao());
        repository.save(categoriaProduto);
    }

    @Transactional
    public void delete(Long id){
        CategoriaProduto categoriaProduto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada para id: " + id));
        
        categoriaProduto.setHabilitado(Boolean.FALSE);
        repository.save(categoriaProduto);
    }

    public CategoriaProduto salvar(CategoriaProduto categoria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    }

    public CategoriaProduto atualizar(Long id, CategoriaProduto categoria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }
}
