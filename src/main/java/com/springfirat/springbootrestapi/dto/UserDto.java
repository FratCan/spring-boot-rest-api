package com.springfirat.springbootrestapi.dto;

import jakarta.persistence.Column;
import lombok.Data;

//dto entity dönüşümünde modelmapper kullanıcaz maven repodan çekicez.
// modelmapper ın instace sini oluşturcam birdaha kullanmam gerekebilir.
@Data
public class UserDto {


    private String firstname;
    private String lastname;

}
