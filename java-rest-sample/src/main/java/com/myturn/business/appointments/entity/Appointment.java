/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myturn.business.appointments.entity;

import com.myturn.business.rooms.entity.Room;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author angelmiralles
 */
@Entity
@Table(name = "APPOINTMENT")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
    @NamedQuery(name = "Appointment.findAllByRoomDate", 
            query = "SELECT a FROM Appointment a "
                    + "WHERE a.room.id = :idRoom "
                    + "AND FUNC('MONTH',a.dateAp) = FUNC('MONTH', :dateAp) "
                    //+ "AND FUNC('DAY',a.dateAp) = FUNC('DAY', :dateAp) "
                    + "ORDER BY a.dateAp ASC")})
public class Appointment implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @NotNull
    @Basic(optional = false)
    private Integer id;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "APPOINTMENT")
    private String appointment;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "DATE_APP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAp;
    
    @NotNull
    @JoinColumn(name = "ROOM", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @XmlTransient
    private Room room;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateAp() {
        return dateAp;
    }

    public void setDateAp(Date dateAp) {
        this.dateAp = dateAp;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
}
