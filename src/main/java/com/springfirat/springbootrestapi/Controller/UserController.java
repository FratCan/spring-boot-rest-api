package com.springfirat.springbootrestapi.Controller;


import com.springfirat.springbootrestapi.Entity.User;
import com.springfirat.springbootrestapi.Service.UserService;
import com.springfirat.springbootrestapi.dto.UserDto;
import com.springfirat.springbootrestapi.util.CustomPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController ile api nin dışarı açılacağını söyleriz.
@RestController
//Hangi adreste yayınlanacağını belirtebiliriz.
@RequestMapping("/User")
public class UserController {

    //Autowired yerine privateoın yanına final keyword unu eklerim ve constructor injection yaparım
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //POST sunucuya veri yazdırma işlemlerinde kullanılır..web sitesi üyelik işlemi
    //RequestBody json nesnesini User class ı ile eşleştirir.
    //ResponseEntity metotlara ortak bir imza yeteneği kazandırır.
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto resultUser = userService.createUser(user);
        return ResponseEntity.ok(resultUser);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> users= userService.getUsers();
        //Static builder bir metoddur ResponseEntity.ok.HTTP 200'e karşılık geliyor
        return ResponseEntity.ok(users);
    }

    //Pathvariable ile url den gelen id değerini alırım.url den gelen değerleri alıp işleme koymak için vardır.
    @GetMapping("/getById/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        UserDto user=userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,@RequestBody UserDto user){
        UserDto resultuser=userService.updateUser(id,user);
        return ResponseEntity.ok(resultuser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity <Boolean> deleteUser(@PathVariable("id") Long id){
       Boolean s=userService.deleteUser(id);
        return ResponseEntity.ok(s);
    }
    //Servers Side Pagination da sayfa bilgilerini manuel olarak gönderip listeleme işlemi yaparız
    @GetMapping("/pagination")
    public ResponseEntity<Page<User>> pagination (@RequestParam int currentPage,@RequestParam int pageSize){
        return ResponseEntity.ok(userService.pagination(currentPage,pageSize));
    }

    //Yukarıdaki ile aynı fakat page size değerini metotdan almıyo controllerda pageable nesnesinden geliyor.
    @GetMapping("/pagination/v1")
    public ResponseEntity<Page<User>> pagination (Pageable pageable){
        return ResponseEntity.ok(userService.pagination(pageable));
    }

    //Yukarıdakiyle aynı bu sadece burda toplam eleman sayısını bulurken mevcut eleman sayısını
    // 1 arttırıp kontrol eder slice yapısı yapar bunu.
    //Yukarıda page kayıt sayısını bulmak için count çalıştırıyor bu daha avantajlı.
    @GetMapping("/pagination/v2")
    public ResponseEntity<Slice<User>> slice (Pageable pageable){
        return ResponseEntity.ok(userService.slice(pageable));
    }

    @GetMapping("/pagination/v3")
    public ResponseEntity<CustomPage<UserDto>> customPagination (Pageable pageable){
        return ResponseEntity.ok(userService.customPagination(pageable));
    }
}
