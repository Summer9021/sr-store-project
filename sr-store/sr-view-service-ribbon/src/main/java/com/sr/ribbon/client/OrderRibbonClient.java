package com.sr.ribbon.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sr.entity.Order;

@Component
public class OrderRibbonClient {

	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	public List<Order> findAllOrderInfo() {
		return restTemplate.getForObject("http://SR-DATA-SERVICE/findAllOrder", List.class);
	}
}
