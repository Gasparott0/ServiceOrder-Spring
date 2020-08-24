package br.com.gasparotto.osgasparotto.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.gasparotto.osgasparotto.domain.exception.ServiceException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter @EqualsAndHashCode(of = "id")
public class ServiceOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Client client;
	
	private String descrip;
	private BigDecimal preco;
	
	@Enumerated(EnumType.STRING)
	private ServiceOrderStatus status;
	
	private OffsetDateTime dateOpen;
	private OffsetDateTime dateClose;
	
	@OneToMany(mappedBy = "serviceOrder")
	private List<Comment> comments = new ArrayList<>();
	
	public void close() {
		if(notCanBeClosed())
			throw new ServiceException("Ordem de serviço não pode ser finalizada");
			
		setStatus(ServiceOrderStatus.CLOSED);
		setDateClose(OffsetDateTime.now());
	}
	
	public boolean canBeClosed() {
		return ServiceOrderStatus.OPEN.equals(getStatus());
	}
	
	public boolean notCanBeClosed() {
		return !canBeClosed();
	}
	
}
