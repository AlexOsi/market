package com.osintsev.market.database.user;

import com.osintsev.market.database.converter.Converter;
import com.osintsev.market.database.order.OrderEntity;
import com.osintsev.market.exception.UserExistsException;
import com.osintsev.market.exception.UserNotFoundException;
import com.osintsev.market.rest.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Converter converter;

    @Autowired
    UserServiceImpl(UserRepository userRepository, Converter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    @Override
    public void createUser(User user) throws UserExistsException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserExistsException("User already exists");
        }
        userRepository.save(converter.userToUserEntity(user));
    }



    @Override
    public User getUser(Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            return converter.userEntityToUser(userEntity);
        }
        else {
            throw new UserNotFoundException("No such user exists");
        }
    }

    @Override
    public List<User> getUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userEntityList.stream()
                .map(converter::userEntityToUser).collect(Collectors.toList());
    }
}
