package br.com.gasparotto.osgasparotto.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gasparotto.osgasparotto.api.model.ServiceOrderInput;
import br.com.gasparotto.osgasparotto.api.model.ServiceOrderModel;
import br.com.gasparotto.osgasparotto.domain.model.ServiceOrder;
import br.com.gasparotto.osgasparotto.domain.repository.ServiceOrderRepository;
import br.com.gasparotto.osgasparotto.domain.service.ServiceOrderManagementService;

@RestController
@RequestMapping("/service-orders")
public class ServiceOrderController {

	@Autowired
	private ServiceOrderManagementService serviceOrderService;
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServiceOrderModel save(@Valid @RequestBody ServiceOrderInput serviceOrderInput) {
		
		ServiceOrder serviceOrder = toEntity(serviceOrderInput);
		
		return toModel(serviceOrderService.save(serviceOrder));
	}
	
	@GetMapping
	public List<ServiceOrderModel> list() {
		return toCollectionModel(serviceOrderRepository.findAll());
	}
	
	@GetMapping("/{serviceOrderId}")
	public ResponseEntity<ServiceOrderModel> search(@PathVariable Long serviceOrderId) {
		Optional<ServiceOrder> serviceOrder = serviceOrderRepository.findById(serviceOrderId);
		
		if(serviceOrder.isPresent()) {
			ServiceOrderModel serviceOrderModel = toModel(serviceOrder.get());
			return ResponseEntity.ok(serviceOrderModel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{serviceOrderId}/close")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void close(@PathVariable Long serviceOrderId) {
		serviceOrderService.close(serviceOrderId);
	}
	
	private ServiceOrderModel toModel(ServiceOrder serviceOrder) {
		return modelMapper.map(serviceOrder, ServiceOrderModel.class);
	}
	
	private List<ServiceOrderModel> toCollectionModel(List<ServiceOrder> serviceOrders) {
		return serviceOrders.stream()
				.map(serviceOrder -> toModel(serviceOrder))
				.collect(Collectors.toList());
	}
	
	private ServiceOrder toEntity(ServiceOrderInput serviceOrderInput) {
		return modelMapper.map(serviceOrderInput, ServiceOrder.class);
	}
}
