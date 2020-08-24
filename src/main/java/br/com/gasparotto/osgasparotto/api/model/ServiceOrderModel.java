package br.com.gasparotto.osgasparotto.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import br.com.gasparotto.osgasparotto.domain.model.ServiceOrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ServiceOrderModel {

	private Long id;
	private ClientAbstractModel client;
	private String descrip;
	private BigDecimal preco;
	private ServiceOrderStatus status;
	private OffsetDateTime dateOpen;
	private OffsetDateTime dateClose;
	
}
