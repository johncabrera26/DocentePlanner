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
@Table(name = "horarioprofesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horarioprofesor.findAll", query = "SELECT h FROM Horarioprofesor h"),
    @NamedQuery(name = "Horarioprofesor.findByIDHorarioProfesor", query = "SELECT h FROM Horarioprofesor h WHERE h.iDHorarioProfesor = :iDHorarioProfesor"),
    @NamedQuery(name = "Horarioprofesor.findByDiaSemana", query = "SELECT h FROM Horarioprofesor h WHERE h.diaSemana = :diaSemana"),
    @NamedQuery(name = "Horarioprofesor.findByHoraInicio", query = "SELECT h FROM Horarioprofesor h WHERE h.horaInicio = :horaInicio"),
    @NamedQuery(name = "Horarioprofesor.findByHoraFin", query = "SELECT h FROM Horarioprofesor h WHERE h.horaFin = :horaFin")})
public class Horarioprofesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HorarioProfesor")
    private Integer iDHorarioProfesor;
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
    @JoinColumn(name = "ID_Profesor", referencedColumnName = "ID_Profesor")
    @ManyToOne
    private Profesor iDProfesor;

    public Horarioprofesor() {
    }

    public Horarioprofesor(Integer iDHorarioProfesor) {
        this.iDHorarioProfesor = iDHorarioProfesor;
    }

    public Integer getIDHorarioProfesor() {
        return iDHorarioProfesor;
    }

    public void setIDHorarioProfesor(Integer iDHorarioProfesor) {
        this.iDHorarioProfesor = iDHorarioProfesor;
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

    public Profesor getIDProfesor() {
        return iDProfesor;
    }

    public void setIDProfesor(Profesor iDProfesor) {
        this.iDProfesor = iDProfesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDHorarioProfesor != null ? iDHorarioProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Horarioprofesor)) {
            return false;
        }
        Horarioprofesor other = (Horarioprofesor) object;
        if ((this.iDHorarioProfesor == null && other.iDHorarioProfesor != null) || (this.iDHorarioProfesor != null && !this.iDHorarioProfesor.equals(other.iDHorarioProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metrodora.dominio.Horarioprofesor[ iDHorarioProfesor=" + iDHorarioProfesor + " ]";
    }

}
