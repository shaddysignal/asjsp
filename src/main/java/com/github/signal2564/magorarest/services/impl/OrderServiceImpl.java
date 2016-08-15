package com.github.signal2564.magorarest.services.impl;

import com.github.signal2564.magorarest.dao.OrderRepository;
import com.github.signal2564.magorarest.models.Order;
import com.github.signal2564.magorarest.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Iterable<Order> list() {
        return orderRepository.findAll();
    }

    @Override
    public Order get(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Long id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(id);
    }
}
