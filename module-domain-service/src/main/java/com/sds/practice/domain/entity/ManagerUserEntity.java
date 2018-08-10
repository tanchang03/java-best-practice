package com.sds.practice.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.practice.domain.entity
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年08月10日 上午11:45
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Entity
@Table(name="tb_user_manager")
@Data
public class ManagerUserEntity extends UserEntity {

    private String account263;
}

