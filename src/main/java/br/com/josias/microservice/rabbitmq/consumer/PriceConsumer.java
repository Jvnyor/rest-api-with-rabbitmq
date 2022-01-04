package br.com.josias.microservice.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.josias.microservice.rabbitmq.constants.RabbitMQConstants;
import br.com.josias.microservice.rabbitmq.dto.PriceDTO;

@Component
public class PriceConsumer {

	@RabbitListener(queues = RabbitMQConstants.PRICE_QUEUE)
	private void consumer(PriceDTO priceDTO) {
		System.out.println("---------------------------------");
		System.out.println("PRICE QUEUE");
		System.out.println(priceDTO.getProductCod());
		System.out.println(priceDTO.getPrice());
		System.out.println("---------------------------------");
	}
	
}
