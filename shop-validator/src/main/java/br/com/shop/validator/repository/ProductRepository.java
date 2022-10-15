package br.com.shop.validator.repository;

import br.com.shop.validator.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByIdentifier(String productIdentifier);
}
