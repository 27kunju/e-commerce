package com.e_commerce.e_commerce_demo.Services;

import com.e_commerce.e_commerce_demo.Dtos.AddressDto;
import com.e_commerce.e_commerce_demo.Dtos.UserDto;
import com.e_commerce.e_commerce_demo.Repository.UserRepository;
import com.e_commerce.e_commerce_demo.model.Address;
import com.e_commerce.e_commerce_demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserDto createUser(UserDto user){
        AddressDto addressDto = user.getAddress();
        Address address = new Address(addressDto.getStreet(), addressDto.getPin_code(), addressDto.getState(), addressDto.getCountry());
        User users = new User(user.getName(), address , user.getPhone());
        User savedUser = userRepository.save(users);
        AddressDto savedAddressDto = new AddressDto(savedUser.getAddress().getId(), savedUser.getAddress().getStreet(), savedUser.getAddress().getPin_code(), savedUser.getAddress().getState(),savedUser.getAddress().getCountry());
        return new UserDto(savedUser.getId(), savedUser.getName(), savedAddressDto, savedUser.getPhone());
    }

    public UserDto getUserDetail(Long id){
        Optional<User> userFromDb = userRepository.findById(id);
        UserDto userDto = null;
        if(userFromDb.isPresent()){
            User user = userFromDb.get();
            AddressDto address = new AddressDto(user.getAddress().getId(), user.getAddress().getStreet(), user.getAddress().getPin_code(), user.getAddress().getState(), user.getAddress().getCountry());
            return new UserDto(user.getId(), user.getName(), address, user.getPhone());

        }

       return null;
    }
}
