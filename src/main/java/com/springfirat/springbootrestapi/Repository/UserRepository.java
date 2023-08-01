package com.springfirat.springbootrestapi.Repository;

import com.springfirat.springbootrestapi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//<hizmet edeceği class,primary key alanı>
public interface UserRepository extends JpaRepository<User, Long> {

    /*
    //Yapmak istediğm sorgu yoksa veya kendi sorgumu yazmak istersem;
    User findByFirstName(String firstname);
    User findByFirstNameAndLastName(String firstname,String lastname);

    // ya da istediğim soruguyu query içinde de döndürebilirim

    @Query("")
    User getUser(String user);
    */
}

