package com.sds.practice.domain.repository.mysql;

import com.sds.practice.domain.entity.UserEntity;
import com.sds.practice.domain.repository.BaseDao;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.practice.domain.repository.mysql
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年07月26日 下午2:18
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

public interface UserDao extends BaseDao<UserEntity,Serializable> {
}
