package com.sr.data.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sr.data.service.OrderService;
import com.sr.entity.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public List<Order> findAllOrder() {
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setOrderNo("P-1001");
		order.setName("气缸");
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		return orders;
	}

}
