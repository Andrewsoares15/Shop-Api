package com.shop.shopapi.repository;

import com.shop.shopapi.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {

    Optional<Shop> findByIdentifier(String identifier);
}
