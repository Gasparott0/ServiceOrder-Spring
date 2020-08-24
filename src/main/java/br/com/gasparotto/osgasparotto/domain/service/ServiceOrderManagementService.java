package br.com.gasparotto.osgasparotto.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gasparotto.osgasparotto.domain.exception.EntityNotFoundException;
import br.com.gasparotto.osgasparotto.domain.exception.ServiceException;
import br.com.gasparotto.osgasparotto.domain.model.Client;
import br.com.gasparotto.osgasparotto.domain.model.Comment;
import br.com.gasparotto.osgasparotto.domain.model.ServiceOrder;
import br.com.gasparotto.osgasparotto.domain.model.ServiceOrderStatus;
import br.com.gasparotto.osgasparotto.domain.repository.ClientRepository;
import br.com.gasparotto.osgasparotto.domain.repository.CommentRepository;
import br.com.gasparotto.osgasparotto.domain.repository.ServiceOrderRepository;

@Service
public class ServiceOrderManagementService {

	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	public ServiceOrder save(ServiceOrder serviceOrder) {
		
		Client client = clientRepository.findById(serviceOrder.getClient().getId())
				.orElseThrow(() -> new ServiceException("Cliente não encontrado"));
		
		serviceOrder.setClient(client);
		serviceOrder.setStatus(ServiceOrderStatus.OPEN);
		serviceOrder.setDateOpen(OffsetDateTime.now());
		
		return serviceOrderRepository.save(serviceOrder);
	}
	
	public void close(Long serviceOrderId) {
		ServiceOrder serviceOrder = search(serviceOrderId);
		
		serviceOrder.close();
		
		serviceOrderRepository.save(serviceOrder);
	}
	
	public Comment saveComment(Long serviceOrderId, String descrip) {
		
		ServiceOrder serviceOrder = search(serviceOrderId);
		
		Comment comment = new Comment();
		comment.setDateSend(OffsetDateTime.now());
		comment.setDescrip(descrip);
		comment.setServiceOrder(serviceOrder);
		
		return commentRepository.save(comment);
	}

	private ServiceOrder search(Long serviceOrderId) {
		return serviceOrderRepository.findById(serviceOrderId)
				.orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada"));
	}
}
