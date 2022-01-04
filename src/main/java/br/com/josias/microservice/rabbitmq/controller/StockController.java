package br.com.josias.microservice.rabbitmq.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.josias.microservice.rabbitmq.constants.RabbitMQConstants;
import br.com.josias.microservice.rabbitmq.dto.StockDTO;
import br.com.josias.microservice.rabbitmq.service.RabbitMQService;

@RestController
@RequestMapping(value = "stock")
public class StockController {

	@Autowired
	private RabbitMQService rabbitMQService;
	
	@PutMapping
	private ResponseEntity<String> alterStock(@RequestBody StockDTO stockDTO) {
//		System.out.println(stockDTO.getProductCod() + " " + stockDTO.getQuantity());
		this.rabbitMQService.sendMessage(RabbitMQConstants.STOCK_QUEUE, stockDTO);
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
