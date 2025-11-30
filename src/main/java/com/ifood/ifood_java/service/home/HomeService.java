package com.ifood.ifood_java.service.home;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ifood.ifood_java.dto.HomeResponse;
import com.ifood.ifood_java.entity.categoria.CategoriaRestaurante;
import com.ifood.ifood_java.entity.restaurante.Restaurante;
import com.ifood.ifood_java.repository.CategoriaRestauranteRepository;
import com.ifood.ifood_java.repository.RestauranteRepository;

@Service
public class HomeService {

    private final CategoriaRestauranteRepository categoriaRepo;
    private final RestauranteRepository restauranteRepo;

    public HomeService(
        CategoriaRestauranteRepository categoriaRepo,
        RestauranteRepository restauranteRepo
    ) {
        this.categoriaRepo = categoriaRepo;
        this.restauranteRepo = restauranteRepo;
    }

    public HomeResponse getHomeData() {
        List<CategoriaRestaurante> categorias = categoriaRepo.findAll();
        List<Restaurante> restaurantes = restauranteRepo.findAll();

        return new HomeResponse(categorias, restaurantes);
    }
}
