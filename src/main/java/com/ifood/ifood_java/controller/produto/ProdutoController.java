package com.ifood.ifood_java.controller.produto;
import org.springframework.web.bind.annotation.*;

import com.ifood.ifood_java.entity.produto.Produto;
import com.ifood.ifood_java.entity.produto.ProdutoRequest;
import com.ifood.ifood_java.service.produto.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public Produto criar(@RequestBody ProdutoRequest req) {
        return service.criar(req);
    }

    @GetMapping
    public Object listar() {
        return service.listar();
    }
    
}
