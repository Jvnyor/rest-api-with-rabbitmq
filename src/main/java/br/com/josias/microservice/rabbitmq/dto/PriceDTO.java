package br.com.josias.microservice.rabbitmq.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PriceDTO implements Serializable {

	private String productCod;
	private double price;
	
}
