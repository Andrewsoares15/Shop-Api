package br.com.shop.validator.service;

import br.com.shop.validator.model.DTO.ShopItemDTO;
import br.com.shop.validator.model.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidateShop {

    public boolean isValidateShopDTO(Optional<Product> product, ShopItemDTO ShopDTO){
        if(product.isEmpty() || validateAmount(product.get(), ShopDTO)) return false;

        return true;
    }

    private boolean validateAmount(Product product, ShopItemDTO ShopDTO){
        return ShopDTO.getAmount() > product.getAmount();
    }
}
