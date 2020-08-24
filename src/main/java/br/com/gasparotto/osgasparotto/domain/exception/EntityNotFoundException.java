package br.com.gasparotto.osgasparotto.domain.exception;

public class EntityNotFoundException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
