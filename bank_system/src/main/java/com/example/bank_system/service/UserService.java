package com.example.bank_system.service;

import com.example.bank_system.Api.ApiException;
import com.example.bank_system.Dto.In.UserDTOIn;
import com.example.bank_system.Dto.Out.UserDTOOut;
import com.example.bank_system.Model.User;
import com.example.bank_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<UserDTOOut> getAllUser (){
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTOOut.class)).collect(Collectors.toList());
    }
    public void addUser(UserDTOIn userIn){
        User user = modelMapper.map(userIn , User.class);
        userRepository.save(user);
    }
    public void updateUser(Integer id ,UserDTOIn userIn){
        User user = modelMapper.map(userIn , User.class);
        User oldUser = userRepository.findUserById(id);
        if (oldUser ==null) throw new ApiException("User not found");

        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());
        userRepository.save(oldUser);
    }
    public  void deleteUser (Integer id){
        User user = userRepository.findUserById(id);
        if (user ==null) throw new ApiException("User not found");
        userRepository.delete(user);
    }

}
