package com.sds.practice.domain.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.practice.domain.entity
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年08月06日 下午1:51
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Entity
@Data
@Table(name="tb_organization")
@Builder
public class OrganizationEntity extends BaseEntity{
    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private OrganizationEntity parent;
}
