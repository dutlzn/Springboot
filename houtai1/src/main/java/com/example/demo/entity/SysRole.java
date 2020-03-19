package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "sys_role")
public class SysRole {
    @Id
    @GeneratedValue
    private Integer id;
    private String cnname;
}
