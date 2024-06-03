package example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class MemberRequestDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public static class CreateDto{

        Long Id;
        String name;
        String password;
        String email;
        String phoneNumber;

    }


}
