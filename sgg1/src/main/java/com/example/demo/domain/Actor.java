package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * 演员
 */
@JsonIdentityInfo(generator = JSOGGenerator.class) // 防止查询数据时引发递归访问效应
@NodeEntity // 标志这个类是一个节点实体
public class Actor {
    @Id
    @GeneratedValue
    private Long id;
}
