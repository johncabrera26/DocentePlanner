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
@Table(name = "ciclo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciclo.findAll", query = "SELECT c FROM Ciclo c"),
    @NamedQuery(name = "Ciclo.findByIDCiclo", query = "SELECT c FROM Ciclo c WHERE c.iDCiclo = :iDCiclo"),
    @NamedQuery(name = "Ciclo.findByNombre", query = "SELECT c FROM Ciclo c WHERE c.nombre = :nombre")})
public class Ciclo implements Serializable {

    @Size(max = 100)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(mappedBy = "iDCiclo")
    private Collection<Horariocreado> horariocreadoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Ciclo")
    private Integer iDCiclo;
    @OneToMany(mappedBy = "iDCiclo")
    private Collection<Horarioasignatura> horarioasignaturaCollection;
    @OneToMany(mappedBy = "iDCiclo")
    private Collection<Horariociclo> horariocicloCollection;

    public Ciclo() {
    }
   
    public Ciclo(Integer iDCiclo, String nombre, Collection<Horarioasignatura> horarioasignaturaCollection) {
        this.iDCiclo = iDCiclo;
        this.nombre = nombre;
        this.horarioasignaturaCollection = horarioasignaturaCollection;
    }

    public Ciclo(Integer iDCiclo) {
        this.iDCiclo = iDCiclo;
    }

    public Integer getIDCiclo() {
        return iDCiclo;
    }

    public void setIDCiclo(Integer iDCiclo) {
        this.iDCiclo = iDCiclo;
    }


    @XmlTransient
    public Collection<Horarioasignatura> getHorarioasignaturaCollection() {
        return horarioasignaturaCollection;
    }

    public void setHorarioasignaturaCollection(Collection<Horarioasignatura> horarioasignaturaCollection) {
        this.horarioasignaturaCollection = horarioasignaturaCollection;
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
        hash += (iDCiclo != null ? iDCiclo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciclo)) {
            return false;
        }
        Ciclo other = (Ciclo) object;
        if ((this.iDCiclo == null && other.iDCiclo != null) || (this.iDCiclo != null && !this.iDCiclo.equals(other.iDCiclo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metrodora.dominio.Ciclo[ iDCiclo=" + iDCiclo + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Horariocreado> getHorariocreadoCollection() {
        return horariocreadoCollection;
    }

    public void setHorariocreadoCollection(Collection<Horariocreado> horariocreadoCollection) {
        this.horariocreadoCollection = horariocreadoCollection;
    }
    
}
