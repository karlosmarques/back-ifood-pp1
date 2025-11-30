package com.ifood.ifood_java.controller.restaurante;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifood.ifood_java.entity.restaurante.Restaurante;
import com.ifood.ifood_java.entity.restaurante.RestauranteRequest;
import com.ifood.ifood_java.service.restaurante.RestauranteService;

import jakarta.validation.Valid;



@Controller
@RequestMapping("restaurante")
@CrossOrigin
public class RestauranteController {
    
    @Autowired
    private RestauranteService restauranteService;

   @PostMapping
    public ResponseEntity<Restaurante> criarRestaurante(@Valid @RequestBody RestauranteRequest request){
    Restaurante restaurante = restauranteService.criarRestaurante(request);
    return ResponseEntity.ok(restaurante);
}


    @GetMapping
    public ResponseEntity<?> mostrarRestaurante(){
    var resultado = restauranteService.mostrarRestaurante();
        return ResponseEntity.ok(resultado);
    }

    
}


