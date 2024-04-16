package com.poly.datn.sd18.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDTO {
    private String name;

    private String email;

    private String phone;

    private String  avatar;

    private String address;

    private String password;

    private Integer roleId;

}
