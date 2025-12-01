package com.ifood.ifood_java.controller.home;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifood.ifood_java.dto.HomeResponse;
import com.ifood.ifood_java.service.home.HomeService;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "*")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/home")
    public HomeResponse getHome() {
        return homeService.getHomeData();
    }
    
}
