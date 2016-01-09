/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myturn.business.configurations.boundary;

import com.myturn.business.AbstractFacade;
import com.myturn.business.configurations.entity.Configuration;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Path("configurations")
public class ConfigurationFacadeREST extends AbstractFacade<Configuration> {
    @PersistenceContext(unitName = "mypu")
    private EntityManager em;

    public ConfigurationFacadeREST() {
        super(Configuration.class);
    }

    @POST
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Configuration entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Configuration entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Configuration find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("/delegation/{idDelegation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Configuration findByDelegation(@PathParam("idDelegation") Integer idDelegation) {
        return (Configuration)this.em.createNamedQuery("Configuration.findByDelegation").setParameter("idDelegation", idDelegation).getSingleResult();
    }

    @GET
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public List<Configuration> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Configuration> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
