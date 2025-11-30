package com.ifood.ifood_java.service.restaurante;

import org.springframework.stereotype.Service;

import com.ifood.ifood_java.entity.restaurante.Restaurante;
import com.ifood.ifood_java.entity.restaurante.RestauranteRequest;
import com.ifood.ifood_java.entity.categoria.CategoriaRestaurante;
import com.ifood.ifood_java.repository.CategoriaRestauranteRepository;
import com.ifood.ifood_java.repository.RestauranteRepository;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CategoriaRestauranteRepository categoriaRepo;

    public RestauranteService(
        RestauranteRepository restauranteRepository,
        CategoriaRestauranteRepository categoriaRepo
    ) {
        this.restauranteRepository = restauranteRepository;
        this.categoriaRepo = categoriaRepo;
    }

    public Restaurante criarRestaurante(RestauranteRequest request) {

        Long categoriaId = request.getCategoriaId();
        if (categoriaId == null) {
            throw new IllegalArgumentException("categoriaId não pode ser nulo");
        }

        // Agora funciona corretamente
        CategoriaRestaurante categoria = categoriaRepo.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Restaurante r = new Restaurante();
        r.setNome(request.getNome());
        r.setRaio_entrega(request.getRaio_entrega());
        // r.setDescricao(request.getDescricao());
        r.setCategoria(categoria);

        return restauranteRepository.save(r);
    }

    public Object mostrarRestaurante() {
        return restauranteRepository.findAll();
    }
}
