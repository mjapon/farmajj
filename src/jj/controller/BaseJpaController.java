/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jj.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
/**
 *
 * @author mjapon
 */
public class BaseJpaController<T> {
    
    protected EntityManager em;
    
    public BaseJpaController(EntityManager em){
        this.em = em;
    }
    
    public EntityManager getEntityManager(){
        return this.em;
    }
    
    public Query newQuery(String queryStr){
        Query query = em.createQuery(queryStr);
        return query;
    }
    
    public T getFirstResult(Query query){        
        List<T> resultList = query.getResultList();
        if (resultList.size()>0){
            return resultList.get(0);
        }
        else{
            return null;
        }
    }
}
