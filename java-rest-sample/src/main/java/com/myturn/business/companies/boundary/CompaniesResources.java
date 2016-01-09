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
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author angelmiralles
 */
@Stateless
@Path("companies")
public class CompaniesResources {
    
    @Inject
    Companies companies;
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Company find(@PathParam("id") int companyId) {
        return companies.find(companyId);
    }
    
    @GET
    @Produces("application/json")
    public List<Company> findAll(){
        return companies.findAll();
    }
    
    @GET
    @Path("/{idCompany}/delegations")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Delegation> findAllByCompany(@PathParam("idCompany") Integer idCompany) {
        return companies.findAllByDelegation(idCompany);
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Company add(Company request) {
        return companies.add(request);
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public void update(@PathParam("id") int companyId, Company app) {
        companies.update(app);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int companyId) {
        Company company = companies.find(companyId);
        companies.delete(company);
    }
}
