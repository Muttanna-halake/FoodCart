package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Orders;
import com.example.demo.entities.User;
import com.example.demo.repositories.OrderRepository;

@Component
public class OrderServices {

	@Autowired
	private OrderRepository orderRepository;

	// GET ALL ORDERS
	public List<Orders> getOrders() {
		return this.orderRepository.findAll();
	}

	// SAVE ORDER (use this everywhere)
	public void saveOrder(Orders order) {
		this.orderRepository.save(order);
	}

	// UPDATE ORDER
	public void updateOrder(int id, Orders order) {
		order.setoId(id);
		this.orderRepository.save(order);
	}

	// DELETE ORDER
	public void deleteOrder(int id) {
		this.orderRepository.deleteById(id);
	}

	// GET ORDERS FOR USER
	public List<Orders> getOrdersForUser(User user) {
		return this.orderRepository.findOrdersByUser(user);
	}

	// GET SINGLE ORDER BY ID
	public Orders getOrderById(int id) {
		return orderRepository.findById(id).orElse(null);
	}
}
