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
@Table(name = "horariociclo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horariociclo.findAll", query = "SELECT h FROM Horariociclo h"),
    @NamedQuery(name = "Horariociclo.findByIDHorarioCiclo", query = "SELECT h FROM Horariociclo h WHERE h.iDHorarioCiclo = :iDHorarioCiclo"),
    @NamedQuery(name = "Horariociclo.findByDiaSemana", query = "SELECT h FROM Horariociclo h WHERE h.diaSemana = :diaSemana"),
    @NamedQuery(name = "Horariociclo.findByHoraInicio", query = "SELECT h FROM Horariociclo h WHERE h.horaInicio = :horaInicio"),
    @NamedQuery(name = "Horariociclo.findByHoraFin", query = "SELECT h FROM Horariociclo h WHERE h.horaFin = :horaFin")})
public class Horariociclo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HorarioCiclo")
    private Integer iDHorarioCiclo;
    @Size(max = 20)
    @Column(name = "DiaSemana")
    private String diaSemana;
    @Column(name = "HoraInicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "HoraFin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @JoinColumn(name = "ID_Aula", referencedColumnName = "ID_Aula")
    @ManyToOne
    private Aula iDAula;
    @JoinColumn(name = "ID_Ciclo", referencedColumnName = "ID_Ciclo")
    @ManyToOne
    private Ciclo iDCiclo;

    public Horariociclo() {
    }

    public Horariociclo(Integer iDHorarioCiclo) {
        this.iDHorarioCiclo = iDHorarioCiclo;
    }

    public Integer getIDHorarioCiclo() {
        return iDHorarioCiclo;
    }

    public void setIDHorarioCiclo(Integer iDHorarioCiclo) {
        this.iDHorarioCiclo = iDHorarioCiclo;
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

    public Aula getIDAula() {
        return iDAula;
    }

    public void setIDAula(Aula iDAula) {
        this.iDAula = iDAula;
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
        hash += (iDHorarioCiclo != null ? iDHorarioCiclo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horariociclo)) {
            return false;
        }
        Horariociclo other = (Horariociclo) object;
        if ((this.iDHorarioCiclo == null && other.iDHorarioCiclo != null) || (this.iDHorarioCiclo != null && !this.iDHorarioCiclo.equals(other.iDHorarioCiclo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metrodora.dominio.Horariociclo[ iDHorarioCiclo=" + iDHorarioCiclo + " ]";
    }
    
}
