package br.com.shop.streams.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShopDTO {
    private String identifier;
    private String status;
    private String buyerIdentifier;
}
