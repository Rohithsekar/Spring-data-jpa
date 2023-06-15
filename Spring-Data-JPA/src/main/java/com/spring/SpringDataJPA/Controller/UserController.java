package com.spring.SpringDataJPA.Controller;

import com.spring.SpringDataJPA.Model.User;
import com.spring.SpringDataJPA.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {
  
    @Autowired
    private UserService service;
    
    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return service.getUsers();
    }
    @GetMapping("/getByProfession/{profession}")
    public List<User> getByProfession(@PathVariable String profession){
        return service.getUserByProfession(profession);
    }
    
    @GetMapping("/countByAge/{age}")
    public String countByAge(@PathVariable int age){
        return "Total count:" + service.countByAge(age);
    }
    @Transactional
    @GetMapping("/delete/{name}")
    public List<User> deleteByName(@PathVariable String name){
        return service.deleteByName(name);
    }
    
    @GetMapping("/findMultiCondition/{profession}/{age}")
    public List<User> getUsersByProfessionAndAge(@PathVariable String profession, @PathVariable int age){
        return  service.findByMultiCondition(profession, age);
    }
    
    @GetMapping("/getUsersIgnoreCase")
    public List<User> getUsersByAge(@RequestParam("age") int age){
        return service.getUserIgnoreCase(age);
    }
    
    @GetMapping("/getSorted/{field}")
    public List<User> getSortedUsers(@PathVariable String field){
        return service.getUserSort(field);
    }
    
    @GetMapping("/getPaginatedData")
    public Page<User> getPaginatedRecords(){
        return service.getPaginatedUser();
    }
    
    @GetMapping("/getByCustomQuery")
    public List<User> getUsersByCustomQuery(){
        return service.getUsersCustomQuery();
    }
    
    
}