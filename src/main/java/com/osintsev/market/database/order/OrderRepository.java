package com.osintsev.market.database.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserId(Long userId);
}
