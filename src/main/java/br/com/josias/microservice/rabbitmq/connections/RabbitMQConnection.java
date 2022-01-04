package br.com.josias.microservice.rabbitmq.connections;

import org.springframework.stereotype.Component;

import br.com.josias.microservice.rabbitmq.constants.RabbitMQConstants;

import org.springframework.amqp.core.Queue;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;

@Component
public class RabbitMQConnection {
	
	private AmqpAdmin amqpAdmin;
	
	public RabbitMQConnection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}
	
	private static final String NAME_EXCHANGE = "amq.direct";

	private Queue queue(String nameQueue) {
		return new Queue(nameQueue, true, false, false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(NAME_EXCHANGE);
	}
	
	private Binding relationship(Queue queue, DirectExchange exchange) {
		return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
	}
	
	@PostConstruct
	private void add() {
		Queue stockQueue = this.queue(RabbitMQConstants.STOCK_QUEUE);
		Queue priceQueue = this.queue(RabbitMQConstants.PRICE_QUEUE);
		
		DirectExchange exchange = this.directExchange();
		
		Binding stockLink = this.relationship(stockQueue, exchange);
		Binding priceLink = this.relationship(priceQueue, exchange);
		
		//criando as filas no RabbitMQ
		this.amqpAdmin.declareQueue(stockQueue);
		this.amqpAdmin.declareQueue(priceQueue);

		this.amqpAdmin.declareExchange(exchange);
		
		this.amqpAdmin.declareBinding(stockLink);
		this.amqpAdmin.declareBinding(priceLink);
	}
}
