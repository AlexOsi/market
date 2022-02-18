package com.osintsev.market.database.order;

import com.osintsev.market.database.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final OrderService orderService;
    private final Converter converter;

    public PurchaseServiceImpl(OrderService orderService, Converter converter) {
        this.orderService = orderService;
        this.converter = converter;
    }

}
