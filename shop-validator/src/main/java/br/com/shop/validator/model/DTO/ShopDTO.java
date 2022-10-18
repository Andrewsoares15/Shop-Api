package br.com.shop.validator.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ShopDTO {
    private String identifier;

    private LocalDate dateCreation;

    private String buyerIdentifier;

    private Status status;

    private List<ShopItemDTO> items;

}
