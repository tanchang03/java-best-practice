package com.sds.practice.domain.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.practice.domain.repository
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年07月26日 下午2:28
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@NoRepositoryBean
public interface BaseDao<T,ID extends Serializable> extends PagingAndSortingRepository<T,ID>{
}
