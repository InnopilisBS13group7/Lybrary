package services;

import Models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.OrderRepository;

import java.util.List;

public class OrderServiceC implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return (List<Order>)orderRepository.findAll();
    }
}
