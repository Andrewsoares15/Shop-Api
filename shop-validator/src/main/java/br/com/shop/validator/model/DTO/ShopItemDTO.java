package br.com.shop.validator.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopItemDTO {

    private String productIdentifier;

    private Integer amount;

    private Double price;

}

