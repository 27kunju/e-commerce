package com.e_commerce.e_commerce_demo.controller;


import com.e_commerce.e_commerce_demo.Dtos.UserDto;
import com.e_commerce.e_commerce_demo.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto addedUser = userService.createUser(user);
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        UserDto user = userService.getUserDetail(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") Long id){
        userService.deleteAddress(id);
        return ResponseEntity.ok("Deleted Successfully");
    }


}
