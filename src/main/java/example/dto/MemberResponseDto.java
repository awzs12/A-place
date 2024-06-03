package example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MemberResponseDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListDto{
        Long Id;
        String name;
        String password;
        String email;
        String phoneNumber;

    }
}
