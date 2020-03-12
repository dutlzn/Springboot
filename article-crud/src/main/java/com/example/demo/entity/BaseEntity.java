package com.example.demo.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 基础实体类
 */
//父类不会映射到数据库
@MappedSuperclass
/**
 * 对实体属性变化的跟踪，它提供了保存前，
 * 保存后，更新前，更新后，删除前，删除后等状态，就像是拦截器一样，你可以在拦截方法里重写你的个性化逻辑。
 */
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    /*    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        protected Integer id;*/
/*
创建时间
*/
    @CreatedDate
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long createTime;

    /*    最后修改时间*/
    @LastModifiedDate
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long updateTime;


    /*
     * 创建人
     */
    @Column(name = "create_by")
    @CreatedBy
    private Long createBy;
    /*
     * 修改人
     */
    @Column(name = "lastmodified_by")
    @LastModifiedBy
    private String lastmodifiedBy;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getLastmodifiedBy() {
        return lastmodifiedBy;
    }

    public void setLastmodifiedBy(String lastmodifiedBy) {
        this.lastmodifiedBy = lastmodifiedBy;
    }
}