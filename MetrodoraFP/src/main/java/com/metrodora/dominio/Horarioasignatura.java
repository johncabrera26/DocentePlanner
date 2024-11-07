/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.metrodora.dominio;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "horarioasignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horarioasignatura.findAll", query = "SELECT h FROM Horarioasignatura h"),
    @NamedQuery(name = "Horarioasignatura.findByIDHorarioAsignatura", query = "SELECT h FROM Horarioasignatura h WHERE h.iDHorarioAsignatura = :iDHorarioAsignatura"),
    @NamedQuery(name = "Horarioasignatura.findByDiaSemana", query = "SELECT h FROM Horarioasignatura h WHERE h.diaSemana = :diaSemana"),
    @NamedQuery(name = "Horarioasignatura.findByHoraInicio", query = "SELECT h FROM Horarioasignatura h WHERE h.horaInicio = :horaInicio"),
    @NamedQuery(name = "Horarioasignatura.findByHoraFin", query = "SELECT h FROM Horarioasignatura h WHERE h.horaFin = :horaFin")})
public class Horarioasignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HorarioAsignatura")
    private Integer iDHorarioAsignatura;
    @Size(max = 20)
    @Column(name = "DiaSemana")
    private String diaSemana;
    @Column(name = "HoraInicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "HoraFin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @JoinColumn(name = "ID_Asignatura", referencedColumnName = "ID_Asignatura")
    @ManyToOne
    private Asignatura iDAsignatura;
    @JoinColumn(name = "ID_Ciclo", referencedColumnName = "ID_Ciclo")
    @ManyToOne
    private Ciclo iDCiclo;

    public Horarioasignatura() {
    }

    public Horarioasignatura(Integer iDHorarioAsignatura) {
        this.iDHorarioAsignatura = iDHorarioAsignatura;
    }

    public Integer getIDHorarioAsignatura() {
        return iDHorarioAsignatura;
    }

    public void setIDHorarioAsignatura(Integer iDHorarioAsignatura) {
        this.iDHorarioAsignatura = iDHorarioAsignatura;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Asignatura getIDAsignatura() {
        return iDAsignatura;
    }

    public void setIDAsignatura(Asignatura iDAsignatura) {
        this.iDAsignatura = iDAsignatura;
    }

    public Ciclo getIDCiclo() {
        return iDCiclo;
    }

    public void setIDCiclo(Ciclo iDCiclo) {
        this.iDCiclo = iDCiclo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDHorarioAsignatura != null ? iDHorarioAsignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horarioasignatura)) {
            return false;
        }
        Horarioasignatura other = (Horarioasignatura) object;
        if ((this.iDHorarioAsignatura == null && other.iDHorarioAsignatura != null) || (this.iDHorarioAsignatura != null && !this.iDHorarioAsignatura.equals(other.iDHorarioAsignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metrodora.dominio.Horarioasignatura[ iDHorarioAsignatura=" + iDHorarioAsignatura + " ]";
    }
    
}
