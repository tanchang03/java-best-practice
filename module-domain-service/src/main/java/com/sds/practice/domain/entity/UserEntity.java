package com.sds.practice.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.practice.domain.entity
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年07月26日 下午2:16
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Entity
@Table(name="tb_user")
@Data
@EqualsAndHashCode(callSuper = true)
@Where(clause = "deleted=0")
public class UserEntity extends BaseEntity{

    private String userName;

    private String name;

    private Date birthday;

    private String sex;

    @ManyToOne
    @JoinColumn(name="org_id")
    private OrganizationEntity org;

    public UserEntity(){}
    public UserEntity(String userName,String name){
        this.userName = userName;
        this.name = name;
    }

}
