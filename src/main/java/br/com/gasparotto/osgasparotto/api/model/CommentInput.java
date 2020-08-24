package br.com.gasparotto.osgasparotto.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentInput {

	@NotBlank
	private String descrip;
}
