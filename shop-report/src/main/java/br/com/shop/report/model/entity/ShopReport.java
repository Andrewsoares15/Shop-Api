package br.com.shop.report.model.entity;

import lombok.Getter;
import lombok.Setter;
import br.com.shop.report.model.DTO.Status;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "shop_report")
public class ShopReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Status identifier;

    private Integer amount;
}
