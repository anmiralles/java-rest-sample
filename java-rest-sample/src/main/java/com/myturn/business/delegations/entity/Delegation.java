/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myturn.business.delegations.entity;

import com.myturn.business.companies.entity.Company;
import com.myturn.business.configurations.entity.Configuration;
import com.myturn.business.rooms.entity.Room;
import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "DELEGATION")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Delegation.findAllByCompany", query = "SELECT d FROM Delegation d WHERE d.company.id = :idCompany")})
public class Delegation implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @NotNull
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "DELEGATION")
    private String delegation;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "COMPANY", referencedColumnName = "ID")
    @XmlTransient
    private Company company;
    
    @NotNull
    @OneToOne
    @JoinColumn(name="CONFIGURATION")
    @XmlTransient
    private Configuration configuration;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="Delegation")
    @XmlTransient
    private List<Room> lstRooms;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @XmlTransient
    public List<Room> getLstRooms() {
        return lstRooms;
    }

    public void setLstRooms(List<Room> lstRooms) {
        this.lstRooms = lstRooms;
    }
    
  
}
