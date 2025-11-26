package com.ifood.ifood_java.service.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ifood.ifood_java.model.CategoriaRestaurante;
import com.ifood.ifood_java.repository.CategoriaRestauranteRepository;

import jakarta.transaction.Transactional;

public class CategoriaRestauranteService {

    @Autowired
    private CategoriaRestauranteRepository repository;

    @Transactional
    public CategoriaRestaurante save(CategoriaRestaurante categoriaRestaurante){
        categoriaRestaurante.setHabilitado(Boolean.TRUE);
        return repository.save(categoriaRestaurante);
    }

    public List<CategoriaRestaurante> listarTodos(){
        return repository.findAll();
    }

    public CategoriaRestaurante obterPorID(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada para id: " + id));
    }

    @Transactional
    public void update(Long id, CategoriaRestaurante categoriaRestauranteAlterado){
        CategoriaRestaurante categoriaRestaurante = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada para id: " + id));
        
        categoriaRestaurante.setDescricao(categoriaRestauranteAlterado.getDescricao());
        // Mantendo lógica original

        repository.save(categoriaRestaurante);
    }

    @Transactional
    public void delete(Long id){
        CategoriaRestaurante categoriaRestaurante = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada para id: " + id));
        
        categoriaRestaurante.setHabilitado(Boolean.FALSE);

        repository.save(categoriaRestaurante);
    }

    public CategoriaRestaurante salvar(CategoriaRestaurante categoria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    }

    public CategoriaRestaurante atualizar(Long id, CategoriaRestaurante categoria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }
}
