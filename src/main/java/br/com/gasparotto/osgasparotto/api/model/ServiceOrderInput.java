package br.com.gasparotto.osgasparotto.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ServiceOrderInput {

	@NotBlank
	private String descrip;
	
	@NotNull
	private BigDecimal preco;
	
	@Valid
	@NotNull
	private ClientIdInput client;
	
}
