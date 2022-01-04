package br.com.josias.microservice.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.josias.microservice.rabbitmq.constants.RabbitMQConstants;
import br.com.josias.microservice.rabbitmq.dto.StockDTO;

@Component
public class StockConsumer {

	@RabbitListener(queues = RabbitMQConstants.STOCK_QUEUE)
	private void consumer(StockDTO stockDTO) {
		System.out.println("---------------------------------");
		System.out.println("STOCK QUEUE");
		System.out.println(stockDTO.getProductCod());
		System.out.println(stockDTO.getQuantity());
		System.out.println("---------------------------------");
	}
	
}
