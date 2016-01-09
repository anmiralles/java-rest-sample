/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myturn.business.configurations.entity;

import com.myturn.business.delegations.entity.Delegation;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
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
@Table(name = "CONFIGURATION")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Configuration.findByDelegation", query = "SELECT c FROM Configuration c WHERE c.delegation.id = :idDelegation")})
public class Configuration implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @NotNull
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "SMTP")
    private String smtp;
    
    @NotNull
    @Basic(optional = false)
    @Size(min = 9, max = 11)
    @Digits(fraction = 0, integer = 9)
    @Column(name = "PORT")
    private String port;
    
    @NotNull
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "SMTP_USER")
    private String user;
    
    @NotNull
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "SMTP_PASS")
    private String pass;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "SECURITY")
    private Boolean security;
    
    @NotNull
    @OneToOne
    @JoinColumn(name="DELEGATION")
    @XmlTransient
    private Delegation delegation;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getSecurity() {
        return security;
    }

    public void setSecurity(Boolean security) {
        this.security = security;
    }

    public Delegation getDelegation() {
        return delegation;
    }

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
    }
}
