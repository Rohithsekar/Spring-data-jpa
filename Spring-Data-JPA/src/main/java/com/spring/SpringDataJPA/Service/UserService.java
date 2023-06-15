package com.spring.SpringDataJPA.Service;

import com.spring.SpringDataJPA.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.spring.SpringDataJPA.Model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    
    @PostConstruct
    public void initialize_database(){
        List<User> users = new ArrayList<>();
        users.add(new User(11, "Adam","It", 23));
        users.add(new User(12,"Ben","It", 23));
        users.add(new User(13,"Cedric", "Designer",24));
        users.add(new User(14, "Elsa", "Modelling", 23));
        users.add(new User(15,"Fiona","Designer",24));
        repository.saveAll(users);
    }
    

    public List<User> getUsers(){
         return repository.findAll();
    }
    
    public List<User> getUserByProfession(String profession){
        return repository.findByProfession(profession);
    }
    
    public int countByAge(int age){
        return repository.countByAge(age);
    }
    
    public List<User> deleteByName(String name){
        return repository.deleteByName(name);
    }
    
    public List<User> findByMultiCondition(String profession, int age){
              return repository.findByProfessionAndAge(profession,age);
    }
    
    public List<User> getUserIgnoreCase(int age){
        return repository.findByAgeIgnoreCase(age);
    }
    
    //sort
    public List<User> getUserSort(String field){
        return repository.findAll(Sort.by(field));
    }
    
    //paging.  The PageRequest.of() method returns a PageRequest object
    public Page<User> getPaginatedUser(){
        return repository.findAll(PageRequest.of(0,3));
    }
    
   public List<User> getUsersCustomQuery() {
        return repository.getUsersCustomQuery();
   }
    
    
}

















