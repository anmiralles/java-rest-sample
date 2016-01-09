/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myturn.business.companies.boundary;

import com.myturn.business.JAXRSConfiguration;
import com.myturn.business.appointments.entity.Appointment;
import com.myturn.business.companies.entity.Company;
import com.myturn.business.configurations.entity.Configuration;
import com.myturn.business.delegations.entity.Delegation;
import com.myturn.business.rooms.entity.Room;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author angelmiralles
 */
@RunWith(Arquillian.class)
public class CompaniesResourceTest {
    
    public CompaniesResourceTest(){}
    
    private static int companyId;
    
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "myturn-rest-test.war")
                .addClasses(CompaniesResources.class, JAXRSConfiguration.class,
                            Companies.class, Company.class, Delegation.class, 
                            Configuration.class, Room.class, Appointment.class)
                .addAsWebInfResource("test-beans.xml", "beans.xml")
                .addAsWebInfResource("test-persistence-web.xml", "web.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }
    
    @Test
    @InSequence(1)
    public void testAdd() {
        WebTarget target = ClientBuilder.newClient()
                .target("http://localhost:8080/myturn-rest-test/resources/companies");
    
        Company co = new Company(Integer.SIZE, "Peluquerias Alfonso");
        
        co = target.request("application/json").post(Entity.json(co), Company.class);
        
        companyId = co.getId();
        
        co = target.path("{id}").resolveTemplate("id", companyId)
                .request("application/json").get(Company.class);
        
        assertEquals("Peluquerias Alfonso", co.getCompany());
    }
    
    @Test
    @InSequence(2)
    public void testUpdate() {
        WebTarget target = ClientBuilder.newClient()
                .target("http://localhost:8080/myturn-rest-test/resources/companies/{id}")
                .resolveTemplate("id", companyId);

        // Update bid.
        Company co = target.request("application/json").get(Company.class);

        co.setCompany("Prueba");

        target.request().put(Entity.json(co));

        // Make sure bid was updated.
        co = target.request("application/json").get(Company.class);

        assertEquals("Prueba", co.getCompany());
    }
    
    @Test
    @InSequence(3)
    public void testDelete() {
        WebTarget target = ClientBuilder.newClient()
                .target("http://localhost:8080/myturn-rest-test/resources/companies/{id}")
                .resolveTemplate("id", companyId);

        target.request().delete();

        assertNull(target.request("application/json").get(Company.class));
    }
    
}
