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

@Entity
@Table(name = "horariocreado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horariocreado.findAll", query = "SELECT h FROM Horariocreado h"),
    @NamedQuery(name = "Horariocreado.findByIDHorarioCreado", query = "SELECT h FROM Horariocreado h WHERE h.iDHorarioCreado = :iDHorarioCreado"),
    @NamedQuery(name = "Horariocreado.findByDiaSemana", query = "SELECT h FROM Horariocreado h WHERE h.diaSemana = :diaSemana"),
    @NamedQuery(name = "Horariocreado.findByHoraInicio", query = "SELECT h FROM Horariocreado h WHERE h.horaInicio = :horaInicio"),
    @NamedQuery(name = "Horariocreado.findByHoraFin", query = "SELECT h FROM Horariocreado h WHERE h.horaFin = :horaFin")})
public class Horariocreado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HorarioCreado")
    private Integer iDHorarioCreado;
    @Size(max = 30)
    @Column(name = "diaSemana")
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
    @JoinColumn(name = "ID_Profesor", referencedColumnName = "ID_Profesor")
    @ManyToOne
    private Profesor iDProfesor;

    public Horariocreado() {
    }

    public Horariocreado(Integer iDHorarioCreado) {
        this.iDHorarioCreado = iDHorarioCreado;
    }

    public Integer getIDHorarioCreado() {
        return iDHorarioCreado;
    }

    public void setIDHorarioCreado(Integer iDHorarioCreado) {
        this.iDHorarioCreado = iDHorarioCreado;
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

    public Profesor getIDProfesor() {
        return iDProfesor;
    }

    public void setIDProfesor(Profesor iDProfesor) {
        this.iDProfesor = iDProfesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDHorarioCreado != null ? iDHorarioCreado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horariocreado)) {
            return false;
        }
        Horariocreado other = (Horariocreado) object;
        if ((this.iDHorarioCreado == null && other.iDHorarioCreado != null) || (this.iDHorarioCreado != null && !this.iDHorarioCreado.equals(other.iDHorarioCreado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metrodora.dominio.Horariocreado[ iDHorarioCreado=" + iDHorarioCreado + " ]";
    }

}
