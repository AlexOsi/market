package com.osintsev.market.database.order;

import com.osintsev.market.database.beat.BeatRepository;
import com.osintsev.market.database.converter.Converter;
import com.osintsev.market.database.user.UserRepository;
import com.osintsev.market.exception.BeatNotFoundException;
import com.osintsev.market.exception.OrderNotFoundException;
import com.osintsev.market.rest.dto.Order;
import com.osintsev.market.rest.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BeatRepository beatRepository;
    private final UserRepository userRepository;
    private final Converter converter;

    public OrderServiceImpl(OrderRepository orderRepository, BeatRepository beatRepository,
                            UserRepository userRepository, Converter converter) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.converter = converter;
        this.beatRepository = beatRepository;
    }

    @Override
    public void createOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setPayment(order.getPayment());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setPurchaseList(order.getPurchaseList().stream()
                .map(purchase -> {
                    PurchaseEntity purchaseEntity = new PurchaseEntity();
                    purchaseEntity.setOrder(orderEntity);
                    purchaseEntity.setBeat(beatRepository.findById(purchase.getBeat().getId())
                            .orElseThrow(() -> new BeatNotFoundException("Beat not found")));
                    purchaseEntity.setLicense(purchase.getLicense());
                    return purchaseEntity;
                }).collect(Collectors.toList()));
        orderEntity.setUser(userRepository.findByEmail(order.getUser().getEmail()));
        orderRepository.save(orderEntity);
    }

    @Override
    public List<Order> getOrderList(User user) {
        return orderRepository.findByUserId(user.getId()).stream()
                .map(converter::orderEntityToOrder).collect(Collectors.toList());
    }

    @Override
    public Order getOrder(Long id) {
    OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("No such orders"));
    return converter.orderEntityToOrder(orderEntity);
    }

    @Override
    public List<Order> getOrderList() {
        List<OrderEntity> orderEntityList = orderRepository.findAll();
        return orderEntityList.stream()
                .map(converter::orderEntityToOrder).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void changeStatus(Long id, OrderStatus orderStatus) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() ->
                new OrderNotFoundException("Order doesn't exist"));
        orderEntity.setStatus(orderStatus);
        orderRepository.save(orderEntity); // saveAndFlush?
    }
}
