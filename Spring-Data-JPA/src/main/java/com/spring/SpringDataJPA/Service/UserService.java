package com.spring.SpringDataJPA.Service;

import com.spring.SpringDataJPA.Repository.UserRepository;
import com.spring.SpringDataJPA.exception.RequestEntityAbsentException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.spring.SpringDataJPA.Model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    public User getUserById(int id) {
        return  repository.findById(id).orElseThrow(() -> new RequestEntityAbsentException("No record exists with id: " + id)
        );

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
    
    public List<User> getUserIgnoreCase(String profession){
        return repository.findByProfessionIgnoreCase(profession);
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

   public List<User> getUsersByAge(int age){return repository.getUsersByAge(age);}

    public User updateUser(User user){
        User existingUser = repository.findById(user.getId()).orElse(null);
        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        existingUser.setProfession(user.getProfession());
        return repository.save(existingUser);

    }

    public void deleteUser(int id) {
        repository.deleteById(id);
        Optional<User> deletedUser = repository.findById(id);
        if (deletedUser.isPresent()) {
            throw new RequestEntityAbsentException("No such id exists");
        }
    }




}

















