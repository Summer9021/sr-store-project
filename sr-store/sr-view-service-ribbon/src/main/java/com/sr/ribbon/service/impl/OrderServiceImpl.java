package com.sr.ribbon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sr.entity.Order;
import com.sr.ribbon.client.OrderRibbonClient;
import com.sr.ribbon.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRibbonClient orderRibbonClient;

	@Override
	public List<Order> findAllOrderInfo() {
		return orderRibbonClient.findAllOrderInfo();
	}

}
