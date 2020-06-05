package com.sr.ribbon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sr.entity.Order;
import com.sr.ribbon.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("/orders")
	public String orders(Model m) {
		List<Order> orders = orderService.findAllOrderInfo();
		m.addAttribute("orderList", orders);
		return "order";
	}

}
