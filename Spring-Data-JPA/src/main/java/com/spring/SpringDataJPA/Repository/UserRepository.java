package com.spring.SpringDataJPA.Repository;

import com.spring.SpringDataJPA.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Integer> {
    
    public List<User> findByProfession(String profession); //case-sensitive
    public int countByAge(int age);
    public List<User> deleteByName(String name);
    
    //multi-conditional method
    public List<User> findByProfessionAndAge(String profession, int age);
    
    public List<User> findByAgeIgnoreCase(int age); //case-insensitive
    
  
    //When you write a custom query,(method name which doesn't follow spring data jpa syntax)
    //you write in HQL. If it is a delete/update query, you should annotate the custom
    //query method with @Modifying annotation, which tells Spring data JPA that it modifies
    //entry in the table and it will be persisted.
  @Query("select u from User u")
    public List<User> getUsersCustomQuery();
    
    
    
}
