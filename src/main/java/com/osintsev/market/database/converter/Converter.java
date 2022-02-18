package com.osintsev.market.database.converter;

import com.osintsev.market.database.beat.BeatEntity;
import com.osintsev.market.database.order.OrderEntity;
import com.osintsev.market.database.order.PurchaseEntity;
import com.osintsev.market.database.user.UserEntity;
import com.osintsev.market.rest.dto.*;
import org.springframework.stereotype.Component;


@Component
public interface Converter {
    BeatDetailed beatEntityToBeatDetailed(BeatEntity beatEntity);

    Beat beatEntityToBeat(BeatEntity beatEntity);

    BeatEntity beatDetailedToBeatEntity(BeatDetailed beatDetailed);

    UserEntity userToUserEntity(User user);

    User userEntityToUser(UserEntity userEntity);

    Purchase purchaseEntityToPurchase(PurchaseEntity purchaseEntity);

    Order orderEntityToOrder(OrderEntity orderEntity);

    OrderEntity orderToOrderEntity(Order order);
}
