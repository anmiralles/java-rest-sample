/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myturn.business.appointments.boundary;

import com.myturn.business.appointments.entity.Appointment;
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

/**
 *
 * @author angelmiralles
 */
@Stateless
@Path("appointments")
public class AppointmentsResource {
    
    @Inject
    Appointments appointments;
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Appointment find(@PathParam("id") int appointmentId) {
        return appointments.find(appointmentId);
    }
    
    @GET
    @Path("{idRoom}/{strFec}")
    @Produces("application/json")
    public List<Appointment> findAllByRoomDate(@PathParam("idRoom") int idRoom, 
            @PathParam("strFec") String strFec) {
        return appointments.findAllByRoomDate(idRoom, strFec);
    }
    
    @GET
    @Produces("application/json")
    public List<Appointment> findAll(){
        return appointments.findAll();
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Appointment add(Appointment request) {
        return appointments.add(request);
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public void update(@PathParam("id") int appointmentId, Appointment app) {
        appointments.update(app);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int appointmentId) {
        Appointment appointment = appointments.find(appointmentId);
        appointments.delete(appointment);
    }
}
