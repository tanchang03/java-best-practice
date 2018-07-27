package com.sds.practice.domain.service;

import com.sds.practice.domain.entity.BaseEntity;
import com.sds.practice.domain.repository.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.practice.domain.service
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年07月26日 下午2:27
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public abstract class BaseService<T extends BaseEntity,ID extends Serializable> {

    public static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    private EntityManager em;

    public T findOne(ID id){
        return dao().findById(id).orElse(null);
    }

    public T save(T obj){
        return dao().save(obj);
    };

    public void delete(T obj){
        dao().delete(obj);
    }

    public void deleteById(ID id){
        dao().deleteById(id);
    }

    @Transactional
    public void logicDelete(T entity){
        String hql = "update "+entity.getClass().getName()+ " obj set obj.deleted=1 where obj.id=?1";
        em.createQuery(hql).setParameter(1,entity.getId()).executeUpdate();
    }

    public boolean exist(ID id){
        return dao().existsById(id);
    }

    public long count(){
        return dao().count();
    }

    public abstract BaseDao<T,ID> dao();

    public void saveAll(List<T> objects){
        dao().saveAll(objects);
    };

}
