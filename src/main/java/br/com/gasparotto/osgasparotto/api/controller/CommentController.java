package br.com.gasparotto.osgasparotto.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gasparotto.osgasparotto.api.model.CommentInput;
import br.com.gasparotto.osgasparotto.api.model.CommentModel;
import br.com.gasparotto.osgasparotto.domain.model.Comment;
import br.com.gasparotto.osgasparotto.domain.model.ServiceOrder;
import br.com.gasparotto.osgasparotto.domain.repository.ServiceOrderRepository;
import br.com.gasparotto.osgasparotto.domain.service.ServiceOrderManagementService;

@RestController
@RequestMapping("/service-orders/{serviceOrderId}/comments")
public class CommentController {

	@Autowired
	private ServiceOrderManagementService serviceOrderManagementService;
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<CommentModel> list(@PathVariable Long serviceOrderId) {
		ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
				.orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada"));
		
		return toCollectionModel(serviceOrder.getComments());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommentModel save(@PathVariable Long serviceOrderId, @Valid @RequestBody CommentInput commentInput) {
		Comment comment = serviceOrderManagementService.saveComment(serviceOrderId, commentInput.getDescrip());
		
		return toModel(comment);
	}

	private CommentModel toModel(Comment comment) {
		return modelMapper.map(comment, CommentModel.class);
	}
	
	private List<CommentModel> toCollectionModel(List<Comment> comments) {
		return comments.stream()
				.map(comment -> toModel(comment))
				.collect(Collectors.toList());
	}	
}
