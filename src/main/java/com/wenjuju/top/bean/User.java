package com.wenjuju.top.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;//主键ID
    private String username;
    @JsonIgnore //转换JSON前忽略这个字段
    private  String password;
    private  String nickname;
    private String email;
    private String userPic;
//    private LocalDateTime create_time;
//    private LocalDateTime update_time;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
