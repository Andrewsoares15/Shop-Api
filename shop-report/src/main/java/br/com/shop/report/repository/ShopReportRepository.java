package br.com.shop.report.repository;

import br.com.shop.report.model.entity.ShopReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopReportRepository extends JpaRepository<ShopReport, Integer> {

    @Modifying
    @Query(value = "update shop_report " +
            "set amount = amount + 1" +
            "where identifier = :shopStatus",
            nativeQuery = true)
    void shopReportIncrement(@Param(value = "shopStatus") String status);
}
