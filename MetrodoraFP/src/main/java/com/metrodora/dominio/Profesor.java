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

@Entity
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p"),
    @NamedQuery(name = "Profesor.findByIDProfesor", query = "SELECT p FROM Profesor p WHERE p.iDProfesor = :iDProfesor"),
    @NamedQuery(name = "Profesor.findByNombre", query = "SELECT p FROM Profesor p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Profesor.findByApellido", query = "SELECT p FROM Profesor p WHERE p.apellido = :apellido")})
public class Profesor implements Serializable {

    @Size(max = 100)
    @Column(name = "Nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "Apellido")
    private String apellido;
    @OneToMany(mappedBy = "iDProfesor")
    private Collection<Horariocreado> horariocreadoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Profesor")
    private Integer iDProfesor;
    @OneToMany(mappedBy = "iDProfesor")
    private Collection<Horarioprofesor> horarioprofesorCollection;

    public Profesor() {
    }

    public Profesor(Integer iDProfesor) {
        this.iDProfesor = iDProfesor;
    }

    public Integer getIDProfesor() {
        return iDProfesor;
    }

    public void setIDProfesor(Integer iDProfesor) {
        this.iDProfesor = iDProfesor;
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
        hash += (iDProfesor != null ? iDProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.iDProfesor == null && other.iDProfesor != null) || (this.iDProfesor != null && !this.iDProfesor.equals(other.iDProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metrodora.dominio.Profesor[ iDProfesor=" + iDProfesor + " ]";
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}
