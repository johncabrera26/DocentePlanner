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
@Table(name = "asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a"),
    @NamedQuery(name = "Asignatura.findByIDAsignatura", query = "SELECT a FROM Asignatura a WHERE a.iDAsignatura = :iDAsignatura"),
    @NamedQuery(name = "Asignatura.findByNombre", query = "SELECT a FROM Asignatura a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Asignatura.findByHorasSemanales", query = "SELECT a FROM Asignatura a WHERE a.horasSemanales = :horasSemanales"),
    @NamedQuery(name = "Asignatura.findByDuracionAnual", query = "SELECT a FROM Asignatura a WHERE a.duracionAnual = :duracionAnual")})
public class Asignatura implements Serializable {

    @Size(max = 100)
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(mappedBy = "iDAsignatura")
    private Collection<Horariocreado> horariocreadoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Asignatura")
    private Integer iDAsignatura;
    @Column(name = "HorasSemanales")
    private Integer horasSemanales;
    @Column(name = "Duracion_Anual")
    private Integer duracionAnual;
    @OneToMany(mappedBy = "iDAsignatura")
    private Collection<Horarioasignatura> horarioasignaturaCollection;
    @OneToMany(mappedBy = "iDAsignatura")
    private Collection<Horarioprofesor> horarioprofesorCollection;

    public Asignatura() {
    }

    public Asignatura(Integer iDAsignatura) {
        this.iDAsignatura = iDAsignatura;
    }

    public Integer getIDAsignatura() {
        return iDAsignatura;
    }

    public void setIDAsignatura(Integer iDAsignatura) {
        this.iDAsignatura = iDAsignatura;
    }


    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(Integer horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public Integer getDuracionAnual() {
        return duracionAnual;
    }

    public void setDuracionAnual(Integer duracionAnual) {
        this.duracionAnual = duracionAnual;
    }

    @XmlTransient
    public Collection<Horarioasignatura> getHorarioasignaturaCollection() {
        return horarioasignaturaCollection;
    }

    public void setHorarioasignaturaCollection(Collection<Horarioasignatura> horarioasignaturaCollection) {
        this.horarioasignaturaCollection = horarioasignaturaCollection;
    }

    @XmlTransient
    public Collection<Horarioprofesor> getHorarioprofesorCollection() {
        return horarioprofesorCollection;
    }

    public void setHorarioprofesorCollection(Collection<Horarioprofesor> horarioprofesorCollection) {
        this.horarioprofesorCollection = horarioprofesorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDAsignatura != null ? iDAsignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.iDAsignatura == null && other.iDAsignatura != null) || (this.iDAsignatura != null && !this.iDAsignatura.equals(other.iDAsignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metrodora.dominio.Asignatura[ iDAsignatura=" + iDAsignatura + " ]";
    }


    @XmlTransient
    public Collection<Horariocreado> getHorariocreadoCollection() {
        return horariocreadoCollection;
    }

    public void setHorariocreadoCollection(Collection<Horariocreado> horariocreadoCollection) {
        this.horariocreadoCollection = horariocreadoCollection;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
