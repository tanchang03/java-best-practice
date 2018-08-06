package com.sds.practice.domain.service;

import com.sds.practice.domain.entity.BaseEntity;
import com.sds.practice.domain.repository.BaseDao;
import com.sds.practice.helper.HqlUtil;
import com.sds.practice.helper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    /**
     * 获取分页里面的数据 注：传进来的 pageNo 要先减去 1
     * @return
     */
    public List getPageList(String jpql, boolean excludeDeleted,int pageNo, int pageSize, Object... params) {
        if(logger.isDebugEnabled()){
            logger.debug("findPageList:"+jpql);
        }
        if(excludeDeleted){
            jpql = HqlUtil.addCondition(jpql, "deleted", 0,HqlUtil.LOGIC_AND,HqlUtil.TYPE_NUMBER);
        }
        Query query = this.em.createQuery(jpql);
        for (int i = 0; i < params.length; i++) {
            Object object = params[i];
            query.setParameter(i+1, object);
        }
        query.setMaxResults(pageSize);
        query.setFirstResult(pageNo*pageSize);
        return query.getResultList();
    }



    /**
     * 自定义查询方法，不带分页,默认排除掉deleted数据
     * @param jpql
     * @param params
     * @return
     */
    public List findByCustomWithParams(String jpql,Object ... params){
        return this.findByCustomWithParams(jpql,true, params);
    }

    /**
     * from LoginLog ll where ll.personId='' order by ll.dt desc
     */
    public List findByCustomWithParams(String jpql,boolean excludeDeleted,Object ... params){
        if(excludeDeleted)
            jpql = HqlUtil.addCondition(jpql, "deleted", 0,HqlUtil.LOGIC_AND,HqlUtil.TYPE_NUMBER);
        Query query = this.em.createQuery(jpql);
        for (int i = 0; i < params.length; i++) {
            Object object = params[i];
            query.setParameter(i+1, object);
        }
        List loglist = query.getResultList();
        return loglist;
    }
    /**
     * 自定义查询方法，带分页
     * 默认排除deleted
     */
    public Page findByCustom(String jpql, int pageNo, int pageSize){
        return this.findByCustom(jpql,true, pageNo, pageSize);
    }

    /**
     * from LoginLog ll where ll.personId='' order by ll.dt desc
     */
    public Page findByCustom(String jpql,boolean excludeDeleted,int pageNo,int pageSize,Object ... params){

        long totalCount = countByCustom(jpql, excludeDeleted,params);
        pageNo--;
        List list = getPageList(jpql,excludeDeleted, pageNo, pageSize, params);
        Page page = new Page((pageNo)*pageSize+1,totalCount,pageSize,list);
        return page;
    }

    /**
     * 根据条件查询总数
     * @param jpql
     * @param excludeDeleted 是否排除已删除的
     * @param params 参数
     * @return
     */
    public long countByCustom(String jpql,boolean excludeDeleted,Object ... params){
        if(logger.isDebugEnabled()){
            logger.debug("countByCustom:"+jpql);
        }
        if(excludeDeleted){
            jpql = HqlUtil.addCondition(jpql, "deleted", 0,HqlUtil.LOGIC_AND,HqlUtil.TYPE_NUMBER);
        }
        jpql = jpql.replaceAll("fetch", "");
        String countJpql = " select count(1) " + HqlUtil.removeOrders(HqlUtil.removeSelect(jpql));
        Query query = this.em.createQuery(countJpql);
        for (int i = 0; i < params.length; i++) {
            Object object = params[i];
            query.setParameter(i+1, object);
        }
        Object obj = query.getSingleResult();
        long totalCount = (Long) obj;
        return totalCount;
    }
}
