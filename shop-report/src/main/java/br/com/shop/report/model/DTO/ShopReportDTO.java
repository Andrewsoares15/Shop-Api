package br.com.shop.report.model.DTO;

import br.com.shop.report.model.entity.ShopReport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopReportDTO {

    private	String	identifier;
    private	Integer	amount;
    public	static	ShopReportDTO	convert(ShopReport shopReport) {
        ShopReportDTO	shopDTO	=	new	ShopReportDTO();
        shopDTO.setIdentifier(shopReport.getIdentifier().name());
        shopDTO.setAmount(shopReport.getAmount());
        return	shopDTO;
    }
}
