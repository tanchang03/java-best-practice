package com.sds.practice.domain.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.practice.domain.entity
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年07月26日 下午3:29
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name="id",length = 32)
    private String id;

    @Column(name="create_time",columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    @Column(name="modify_time",columnDefinition = "")
    private Date modifyTime;

    @Column(name="deleted",columnDefinition = "bit default 0")
    private Boolean deleted=false;

    @Column(name="version",columnDefinition = "int default 0")
    private int version;

    @Column(name="sortno",columnDefinition = "int default 0")
    private Long sortNo=0L;
}
