package br.com.josias.microservice.rabbitmq.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class StockDTO implements Serializable{
	
	private String productCod;
	private int quantity;
	
}
