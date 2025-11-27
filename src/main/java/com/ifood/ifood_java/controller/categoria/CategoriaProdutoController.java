/* 
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

import com.ifood.ifood_java.model.CategoriaProduto;
import com.ifood.ifood_java.service.categoria.CategoriaProdutoService;

@RestController
@RequestMapping("/categorias/produtos")
public class CategoriaProdutoController {

    private final CategoriaProdutoService service;

    public CategoriaProdutoController(CategoriaProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoriaProduto> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public CategoriaProduto salvar(@RequestBody CategoriaProduto categoria) {
        return service.salvar(categoria);
    }

    @PutMapping("/{id}")
    public CategoriaProduto atualizar(@PathVariable Long id, @RequestBody CategoriaProduto categoria) {
        return service.atualizar(id, categoria);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.delete(id);
    }
    
}
*/