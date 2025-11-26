package com.ifood.ifood_java.controller.categoria;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifood.ifood_java.model.CategoriaRestaurante;
import com.ifood.ifood_java.service.categoria.CategoriaRestauranteService;

@RestController
@RequestMapping("/categorias/restaurantes")
public class CategoriaRestauranteController {

     private final CategoriaRestauranteService service;

    public CategoriaRestauranteController(CategoriaRestauranteService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoriaRestaurante> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public CategoriaRestaurante salvar(@RequestBody CategoriaRestaurante categoria) {
        return service.salvar(categoria);
    }

    @PutMapping("/{id}")
    public CategoriaRestaurante atualizar(@PathVariable Long id, @RequestBody CategoriaRestaurante categoria) {
        return service.atualizar(id, categoria);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.delete(id);
    }
    
}
