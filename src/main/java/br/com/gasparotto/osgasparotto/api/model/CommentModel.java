package br.com.gasparotto.osgasparotto.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentModel {

	private Long id;
	private String descrip;
	private OffsetDateTime dateSend;
	
}
