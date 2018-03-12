/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jj.controller;

import javax.persistence.EntityManager;

/**
 *
 * @author mjapon
 */
public class BaseJpaController {
    
    protected EntityManager em;
    
    public BaseJpaController(EntityManager em){
        this.em = em;
    }
    
    public EntityManager getEntityManager(){
        return this.em;
    }
    
}
