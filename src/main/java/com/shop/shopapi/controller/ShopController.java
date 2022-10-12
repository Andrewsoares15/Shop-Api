package com.shop.shopapi.controller;

import com.shop.shopapi.model.DTO.ShopDTO;
import com.shop.shopapi.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private ShopService shopService;

    @PostMapping
    public ResponseEntity<Integer> createShop(ShopDTO shopDTO){
        var shop = shopService.createShop(shopDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
