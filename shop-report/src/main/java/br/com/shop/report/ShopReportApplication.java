package br.com.shop.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ShopReportApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopReportApplication.class, args);
    }

}
