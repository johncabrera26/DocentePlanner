/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.metrodora.dominio;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "aula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aula.findAll", query = "SELECT a FROM Aula a"),
    @NamedQuery(name = "Aula.findByIDAula", query = "SELECT a FROM Aula a WHERE a.iDAula = :iDAula"),
    @NamedQuery(name = "Aula.findByNombre", query = "SELECT a FROM Aula a WHERE a.nombre = :nombre")})
public class Aula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Aula")
    private Integer iDAula;
    @Size(max = 100)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(mappedBy = "iDAula")
    private Collection<Horariociclo> horariocicloCollection;

    public Aula() {
    }

    public Aula(Integer iDAula) {
        this.iDAula = iDAula;
    }

    public Integer getIDAula() {
        return iDAula;
    }

    public void setIDAula(Integer iDAula) {
        this.iDAula = iDAula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Horariociclo> getHorariocicloCollection() {
        return horariocicloCollection;
    }

    public void setHorariocicloCollection(Collection<Horariociclo> horariocicloCollection) {
        this.horariocicloCollection = horariocicloCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDAula != null ? iDAula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aula)) {
            return false;
        }
        Aula other = (Aula) object;
        if ((this.iDAula == null && other.iDAula != null) || (this.iDAula != null && !this.iDAula.equals(other.iDAula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metrodora.dominio.Aula[ iDAula=" + iDAula + " ]";
    }
    
}
