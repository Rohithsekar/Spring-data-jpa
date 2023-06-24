package com.spring.SpringDataJPA.Repository;

import com.spring.SpringDataJPA.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/*
Spring Data JPA is able to read the method names and generate corresponding queries against the database by utilizing a
feature called Query Creation. This feature analyzes the method names and applies certain rules to derive the query.

Here's how it works:

1. Parsing Method Name: Spring Data JPA parses the method name and splits it into three parts: the subject,
 the predicate, and the object. For example, in the method name `findByProfessionIgnoreCase`, the subject is `findBy`,
 the predicate is `Profession`, and the object is `IgnoreCase`.

2. Resolving Keywords: Spring Data JPA recognizes certain keywords in the method name and understands their meaning.
 Keywords like `findBy`, `countBy`, `deleteBy`, and `getBy` indicate the type of query operation to perform
  (e.g., select, count, delete, get).

3. Analyzing Object and Predicate: Spring Data JPA analyzes the object and predicate to understand which entity
 attribute is involved and what condition should be applied. In the method `findByProfessionIgnoreCase`, the object is
 `Profession`, indicating that the `profession` attribute of the `User` entity is involved. The predicate
 `IgnoreCase` indicates that a case-insensitive search should be performed on the `profession` attribute.

4. Building the Query: Based on the parsed method name, Spring Data JPA constructs a corresponding query. It
automatically generates the SELECT, WHERE, and other clauses based on the method name and the attributes involved.
In the case of `findByProfessionIgnoreCase`, it generates a query that retrieves users where the `profession`
attribute matches the provided value in a case-insensitive manner.

By following the naming conventions and using the supported keywords, you can express various types of queries directly
 in the method names, eliminating the need to write custom queries in many cases.

 You can also write your own query using the @Query annotation provided by Spring Data JPA
 */

public interface UserRepository  extends JpaRepository<User, Integer> {
    
    List<User> findByProfession(String profession); //case-sensitive
    int countByAge(int age);
    List<User> deleteByName(String name);
    
    //multi-conditional method
    List<User> findByProfessionAndAge(String profession, int age);
    
    List<User> findByProfessionIgnoreCase(String profession); //case-insensitive
    
  
    //When you write a custom query,(method name which doesn't follow spring data jpa syntax)
    //you write in HQL. If it is a delete/update query, you should annotate the custom
    //query method with @Modifying annotation, which tells Spring data JPA that it modifies
    //entry in the table, and it will be persisted.
  @Query("select u from User u")
    List<User> getUsersCustomQuery();

     @Query(value = "SELECT * FROM user WHERE age = ?1", nativeQuery = true)
    List<User> getUsersByAge(int age);

    /*
    In the above example, the @Query annotation specifies a native SQL query SELECT * FROM users WHERE profession = ?1,
    where ?1 is a positional parameter representing the first method parameter (String profession). The nativeQuery
    attribute is set to true to indicate that it's a native SQL query.

    It's worth noting that Spring Data JPA, which builds on top of JPA, supports both HQL and JPQL. You can use the
    @Query annotation to write queries in either HQL or JPQL syntax, depending on your needs and preferences.
     */
    
    
    
}
