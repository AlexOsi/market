package com.osintsev.market.database.converter;

import com.osintsev.market.database.beat.AudioEntity;
import com.osintsev.market.database.beat.BeatEntity;
import com.osintsev.market.database.order.OrderEntity;
import com.osintsev.market.database.order.PurchaseEntity;
import com.osintsev.market.database.user.UserEntity;
import com.osintsev.market.rest.dto.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ConverterImpl implements Converter {

    @Override
    public BeatDetailed beatEntityToBeatDetailed(BeatEntity beatEntity) {
        BeatDetailed beatDetailed = new BeatDetailed();
        beatDetailed.setId(beatEntity.getId());
        beatDetailed.setName(beatEntity.getName());
        beatDetailed.setImage(beatEntity.getImage());
        beatDetailed.setPrice(beatEntity.getPrice());
        beatDetailed.setAudio(audioEntityToAudio(beatEntity.getAudio()));
        beatDetailed.setBPM(beatEntity.getBPM());
        beatDetailed.setGenre(beatEntity.getGenre());
        beatDetailed.setKey(beatEntity.getKey());
        beatDetailed.setLoadDate(beatEntity.getLoadDate());
        return beatDetailed;
    }

    @Override
    public Beat beatEntityToBeat(BeatEntity beatEntity) {
        Beat beat = new Beat();
        beat.setId(beatEntity.getId());
        beat.setName(beatEntity.getName());
        beat.setImage(beatEntity.getImage());
        beat.setPrice(beatEntity.getPrice());
        beat.setAudio(audioEntityToAudio(beatEntity.getAudio()));
        return beat;
    }

    public void beatDetailedToBeatEntityTransferData(BeatDetailed beatDetailed, BeatEntity beatEntity) {
        beatEntity.setName(beatDetailed.getName());
        beatEntity.setImage(beatDetailed.getImage());
        beatEntity.setPrice(beatDetailed.getPrice());
        beatEntity.setAudio(audioToAudioEntity(beatDetailed.getAudio()));
        beatEntity.setBPM(beatDetailed.getBPM());
        beatEntity.setGenre(beatDetailed.getGenre());
        beatEntity.setKey(beatDetailed.getKey());
        beatEntity.setLoadDate(beatDetailed.getLoadDate());
        System.out.println(beatEntity.getPrice());
    }
    @Override
    public BeatEntity beatDetailedToBeatEntity(BeatDetailed beatDetailed) {
        BeatEntity beatEntity = new BeatEntity();
        beatDetailedToBeatEntityTransferData(beatDetailed, beatEntity);
        return beatEntity;
    }


    @Override
    public UserEntity userToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        return userEntity;
    }
    @Override
    public User userEntityToUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setEmail(userEntity.getEmail());
        user.setName(userEntity.getName());
        return user;
    }
    @Override
    public Purchase purchaseEntityToPurchase(PurchaseEntity purchaseEntity) {
        Purchase purchase = new Purchase();
        purchase.setBeat(beatEntityToBeat(purchaseEntity.getBeat()));
        purchase.setLicense(purchaseEntity.getLicense());
        return purchase;
    }
    @Override
    public Order orderEntityToOrder(OrderEntity orderEntity) {
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setUser(userEntityToUser(orderEntity.getUser()));
        order.setPayment(orderEntity.getPayment());
        order.setStatus(orderEntity.getStatus());
        order.setPurchaseList(orderEntity.getPurchaseList().stream()
                .map(this::purchaseEntityToPurchase)
                .collect(Collectors.toList()));
        return order;
    }

    @Override
    public OrderEntity orderToOrderEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUser(userToUserEntity(order.getUser()));
        orderEntity.setPayment(order.getPayment());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setPurchaseList(order.getPurchaseList().stream()
                .map(this::purchaseToPurchaseEntity).collect(Collectors.toList()));
        return orderEntity;
    }

    private PurchaseEntity purchaseToPurchaseEntity(Purchase purchase) {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setLicense(purchase.getLicense());
        return purchaseEntity;
    }
    private AudioEntity audioToAudioEntity(Audio audio) {
        AudioEntity audioEntity = new AudioEntity();
        audioEntity.setMp3(audio.getMp3());
        audioEntity.setWav(audio.getWav());
        audioEntity.setTrackOut(audio.getTrackOut());
        return audioEntity;
    }


    private Audio audioEntityToAudio(AudioEntity audioEntity) {
        Audio audio = new Audio();
        audio.setId(audioEntity.getId());
        audio.setMp3(audioEntity.getMp3());
        audio.setWav(audioEntity.getWav());
        audio.setTrackOut(audioEntity.getTrackOut());
        return audio;
    }
}
