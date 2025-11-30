package com.ifood.ifood_java.dto;

import java.util.List;

public class HomeResponse {

    private List<?> categorias;
    private List<?> restaurantes;

    public HomeResponse(List<?> categorias, List<?> restaurantes) {
        this.categorias = categorias;
        this.restaurantes = restaurantes;
    }

    public List<?> getCategorias() {
        return categorias;
    }

    public List<?> getRestaurantes() {
        return restaurantes;
    }
}
