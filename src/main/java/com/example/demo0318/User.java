package com.example.demo0318;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class User {

    @NotNull(message = "名字不能为空")
    private String name;

    @Email(message = "邮箱的格式不正确")
    private String email;


}
