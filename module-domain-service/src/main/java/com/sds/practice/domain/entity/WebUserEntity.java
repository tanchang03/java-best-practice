package com.sds.practice.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.practice.domain.entity
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年08月10日 上午11:44
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Entity
@Table(name="tb_user_web")
@Data
public class WebUserEntity extends UserEntity {
    private String openid;
}

