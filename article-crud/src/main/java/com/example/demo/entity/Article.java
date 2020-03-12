package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 文章 实体类
 */
@Entity(name = "t_article")
@Data
public class Article extends BaseEntity implements Serializable {
//    id由数据库控制
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

// 标题唯一不能为空
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "标题不能为空")
    private String title;

    @Column(nullable = false)
    @NotEmpty(message = "文章内容不能为空")
    private String body;
}
