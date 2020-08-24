package br.com.gasparotto.osgasparotto.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gasparotto.osgasparotto.domain.exception.ServiceException;
import br.com.gasparotto.osgasparotto.domain.model.Client;
import br.com.gasparotto.osgasparotto.domain.repository.ClientRepository;

@Service
public class RegisterClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public Client save(Client client) {
		Client clientExist = clientRepository.findByEmail(client.getEmail());
		
		if(clientExist != null && !clientExist.equals(client))
			throw new ServiceException("Já existe cliente cadastrado com esse email!");
		
		return clientRepository.save(client);
	}
	
	public void delete(Long id) {
		clientRepository.deleteById(id);
	}
}
