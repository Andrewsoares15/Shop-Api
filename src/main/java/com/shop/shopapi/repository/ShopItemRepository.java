package com.shop.shopapi.repository;

import com.shop.shopapi.model.entity.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopItemRepository extends JpaRepository<Integer, ShopItem> {
}
