/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myturn.business.rooms.entity;

import com.myturn.business.appointments.entity.Appointment;
import com.myturn.business.delegations.entity.Delegation;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author angelmiralles
 */
@Entity
@Table(name = "ROOM")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Room.findAllByDelegation", query = "SELECT r FROM Room r WHERE r.delegation.id = :idDelegation")})
public class Room implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @NotNull
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "ROOM")
    private String room;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "AVERAGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date average;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "INIT_HOUR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date initHour;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "END_HOUR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endHour;
    
    @NotNull
    @JoinColumn(name = "DELEGATION", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @XmlTransient
    private Delegation delegation;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="Room")
    @XmlTransient
    private List<Appointment> lstApps;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getAverage() {
        return average;
    }

    public void setAverage(Date average) {
        this.average = average;
    }

    public Date getInitHour() {
        return initHour;
    }

    public void setInitHour(Date initHour) {
        this.initHour = initHour;
    }

    public Date getEndHour() {
        return endHour;
    }

    public void setEndHour(Date endHour) {
        this.endHour = endHour;
    }

    public Delegation getDelegation() {
        return delegation;
    }

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
    }

    @XmlTransient
    public List<Appointment> getLstApps() {
        return lstApps;
    }

    public void setLstApps(List<Appointment> lstApps) {
        this.lstApps = lstApps;
    }
    
}
