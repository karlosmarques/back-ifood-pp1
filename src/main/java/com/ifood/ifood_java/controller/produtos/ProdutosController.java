package com.ifood.ifood_java.controller.produtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifood.ifood_java.entity.produtos.Produtos;
import com.ifood.ifood_java.entity.produtos.ProdutosRequest;
import com.ifood.ifood_java.service.produto.ProdutoService;
import com.ifood.ifood_java.service.restaurante.RestauranteService;

@Controller
@RequestMapping("/produto")
@CrossOrigin
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produtos> criarPordutos(@RequestBody ProdutosRequest request){
        Produtos produtos = produtoService.criarPordutos(request);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping
    public ResponseEntity<?>mostrarProdutos(){
        var resultado = produtoService.mostrarProdutos();
        return ResponseEntity.ok(resultado);
    }
    
}
