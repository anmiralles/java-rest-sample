/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myturn.business.appointments.boundary;

import com.myturn.business.appointments.entity.Appointment;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author angelmiralles
 */
@Stateless
public class Appointments {
    
    @PersistenceContext
    EntityManager em;
    
    public Appointment find(int appointmentId){
        return this.em.find(Appointment.class, appointmentId);
    }
    
    public List<Appointment> findAll(){
        return this.em.createNamedQuery("Appointment.findAll").getResultList();
    }
    
    public List<Appointment> findAllByRoomDate(Integer idRoom, String strFec){
        
        Date dateAp = Calendar.getInstance().getTime();
                
        return this.em.createNamedQuery("Appointment.findAllByRoomDate").
                setParameter("idRoom", idRoom).
                setParameter("dateAp", dateAp).
                getResultList();
    }
    
    public Appointment add(Appointment request){
        return this.em.merge(request);
    }

    public void update(Appointment request){
        this.em.merge(request);
    }
    
    public void delete(Appointment request){
        this.em.remove(this.em.merge(request));
    }
}
