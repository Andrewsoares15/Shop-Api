package br.com.shop.report.controller;

import br.com.shop.report.model.DTO.ShopReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.shop.report.repository.ShopReportRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop_report")
public class ShopController {

    @Autowired
    private ShopReportRepository reportRepository;

    @GetMapping
    public List<ShopReportDTO> getShopReport() {
        return reportRepository.findAll()
                .stream()
                .map(shop -> ShopReportDTO.convert(shop))
                .collect(Collectors.toList());
    }
}
