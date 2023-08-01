package com.springfirat.springbootrestapi.Service.impl;

import com.springfirat.springbootrestapi.Entity.User;
import com.springfirat.springbootrestapi.Repository.UserRepository;
import com.springfirat.springbootrestapi.Service.UserService;
import com.springfirat.springbootrestapi.advice.UserNotFound;
import com.springfirat.springbootrestapi.dto.UserDto;
import com.springfirat.springbootrestapi.util.CustomPage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //Constructor injection yerine kullanabilirim
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        //userDto nesnesini bir entity'e dönüştürmem lazım.
        //map metodunna dönüştürceğim class ve dönüşeceği class yazılacak
        //Böylece iç modelimizi ilgilendiren kısımları cliet' dönmemiş olduk

        User user=modelMapper.map(userDto,User.class);
        user.setCreDate(new Date());
        user.setCreatedBy("Admin");
        return modelMapper.map(userRepository.save(user),UserDto.class);
        //return userRepository.save(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users=userRepository.findAll();
        List<UserDto> dtos=users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return dtos;
        //return userRepository.findAll();
    }

    @Override
    public UserDto getUser(Long id) {

        //Null pointer exception hatasının düşmesini engelliyor
        Optional<User> user= userRepository.findById(id);
        if(user.isPresent()){
           // return user.get();
              return modelMapper.map(user.get(),UserDto.class);
        }
        //illegal
        //usernotfound
        throw new RuntimeException("Kullanıcı Bulunamadı");

    }

    @Override
    public UserDto updateUser(Long id,UserDto user) {
        Optional<User> ruser= userRepository.findById(id);
        /*
        if(ruser.isPresent()){
            ruser.get().setFirstname(user.getFirstname());
            ruser.get().setLastname(user.getLastname());
            ruser.get().setUpdateBy("Admin");
            ruser.get().setUpdateDate(new Date());
            return userRepository.save(ruser.get());
        }
        return null;
        */
         if(ruser.isPresent()){
             ruser.get().setFirstname(user.getFirstname());
             ruser.get().setLastname(user.getLastname());
             ruser.get().setUpdateBy("Admin");
             ruser.get().setUpdateDate(new Date());
             return modelMapper.map(userRepository.save(ruser.get()),UserDto.class);
         }
         return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        Optional<User> user= userRepository.findById(id);
        if(user.isPresent()){
           userRepository.deleteById(id);
           return true;
        }
        else{
             return false;
        }

    }

    @Override
    public Page<User> pagination(int currentpage, int pagesize) {

        Pageable pageable= PageRequest.of(currentpage,pagesize);
        return userRepository.findAll(pageable);
    }


    @Override
    public Page<User> pagination(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Slice<User> slice(Pageable pageable) {
        return  userRepository.findAll(pageable);
    }

    @Override
    public CustomPage<UserDto> customPagination(Pageable pageable) {
         Page<User> data =userRepository.findAll(pageable);
         UserDto[] dtos=modelMapper.map(data.getContent(),UserDto[].class);
         return new CustomPage<UserDto>(data, Arrays.asList(dtos));
    }
    
}
