package com.shop.shopapi.controller;

import com.shop.shopapi.model.DTO.ShopDTO;
import com.shop.shopapi.model.entity.Shop;
import com.shop.shopapi.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping
    public ResponseEntity<ShopDTO> createShop(@RequestBody ShopDTO shopDTO){
        ShopDTO shop = shopService.saveShop(shopDTO);
        return new ResponseEntity<>(shop, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ShopDTO>> get(){
        List<ShopDTO> shopDTOS = shopService.get();
        return new ResponseEntity<>(shopDTOS, HttpStatus.OK);
    }
}
