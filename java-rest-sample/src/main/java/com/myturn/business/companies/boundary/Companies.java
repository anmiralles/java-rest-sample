/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myturn.business.companies.boundary;

import com.myturn.business.companies.entity.Company;
import com.myturn.business.delegations.entity.Delegation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author angelmiralles
 */
@Stateless
public class Companies {
    
    @PersistenceContext
    EntityManager em;
    
    public Company find(int companyId){
        return this.em.find(Company.class, companyId);
    }
    
    public List<Company> findAll(){
        return this.em.createNamedQuery("Company.findAll").getResultList();
    }
    
    public List<Delegation> findAllByDelegation(int idCompany){
        return this.em.createNamedQuery("Delegation.findAllByCompany").setParameter("idCompany", idCompany).getResultList();
    }
    
    public Company add(Company request){
        return this.em.merge(request);
    }

    public void update(Company request){
        this.em.merge(request);
    }
    
    public void delete(Company request){
        this.em.remove(this.em.merge(request));
    }
}
