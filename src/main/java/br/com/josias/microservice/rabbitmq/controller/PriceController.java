package br.com.josias.microservice.rabbitmq.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.josias.microservice.rabbitmq.constants.RabbitMQConstants;
import br.com.josias.microservice.rabbitmq.dto.PriceDTO;
import br.com.josias.microservice.rabbitmq.service.RabbitMQService;

@RestController
@RequestMapping(value = "price")
public class PriceController {

	@Autowired
	private RabbitMQService rabbitMQService;
	
	@PutMapping
	private ResponseEntity<String> alterPrice(@RequestBody PriceDTO priceDTO) {
//		System.out.println(priceDTO.getProductCod() + " " + priceDTO.getPrice());
		this.rabbitMQService.sendMessage(RabbitMQConstants.PRICE_QUEUE, priceDTO);
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
