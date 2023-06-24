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

    @GetMapping("/getById/{id}")
    public User getById(@PathVariable int id){ return service.getUserById(id);}
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

    //The URL looks like this: http://<host>:<port>/getUsersIgnoreCase?profession=<profession>
    @GetMapping("/getUsersIgnoreCase")
    public List<User> getUsersByProfessionIgnoreCase(@RequestParam("profession") String profession){
        return service.getUserIgnoreCase(profession);
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

    @GetMapping("/getUsersByAge")
    public List<User> getUsersByAge(@RequestParam("age")  int age){ return service.getUsersByAge(age);}

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteUser(id);
    }




    
    
}
