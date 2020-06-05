package com.sr.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sr.data.service.OrderService;
import com.sr.entity.Order;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("/findAllOrder")
	public List<Order> findAllOrder() {
		return orderService.findAllOrder();
	}
}
