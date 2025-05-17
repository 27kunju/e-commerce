package com.e_commerce.e_commerce_demo.Services;

import com.e_commerce.e_commerce_demo.Dtos.AddressDto;
import com.e_commerce.e_commerce_demo.Dtos.UserDto;
import com.e_commerce.e_commerce_demo.Exception.ResourceNotFoundException;
import com.e_commerce.e_commerce_demo.Repository.AddressRepository;
import com.e_commerce.e_commerce_demo.Repository.UserRepository;
import com.e_commerce.e_commerce_demo.model.Address;
import com.e_commerce.e_commerce_demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private AddressRepository addressRepository;

    public UserDto createUser(UserDto userDto) {
        // Convert List<AddressDto> to List<Address>
        List<Address> addressList  = new ArrayList<>();
        if( !userDto.getAddress().isEmpty()) {
            addressList = userDto.getAddress().stream()
                    .map(addrDto -> new Address(
                            addrDto.getStreet(),
                            addrDto.getPin_code(),
                            addrDto.getState(),
                            addrDto.getCountry()))
                    .collect(Collectors.toList());
        }

        // Create User entity
        User user = new User(userDto.getName(), addressList, userDto.getPhone());

        // Save user
        User savedUser = userRepository.save(user);

        // Map saved addresses to List<AddressDto>
        List<AddressDto> savedAddressDtos = savedUser.getAddress().stream()
                .map(addr -> new AddressDto(
                        addr.getId(),
                        addr.getStreet(),
                        addr.getPin_code(),
                        addr.getState(),
                        addr.getCountry()))
                .collect(Collectors.toList());

        // Return mapped UserDto
        return new UserDto(savedUser.getId(), savedUser.getName(), savedAddressDtos, savedUser.getPhone());
    }

    public UserDto getUserDetail(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", HttpStatus.NOT_FOUND));

        // Map List<Address> to List<AddressDto>
        List<AddressDto> addressDtos = user.getAddress().stream()
                .map(address -> new AddressDto(
                        address.getId(),
                        address.getStreet(),
                        address.getPin_code(),
                        address.getState(),
                        address.getCountry()))
                .collect(Collectors.toList());

        return new UserDto(user.getId(), user.getName(), addressDtos, user.getPhone());
    }


    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }

    public void deleteAddress(long id){
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("address id not found", HttpStatus.NOT_FOUND));
        addressRepository.delete(address);
    }
}
