package com.sds.practice.domain.service;

import com.sds.practice.domain.entity.OrganizationEntity;
import com.sds.practice.domain.repository.BaseDao;
import com.sds.practice.domain.repository.mysql.OrganizationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.practice.domain.service
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年08月06日 下午1:59
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Service
public class OrganizationService extends BaseService<OrganizationEntity,Serializable> {

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public BaseDao<OrganizationEntity, Serializable> dao() {
        return organizationDao;
    }
}
