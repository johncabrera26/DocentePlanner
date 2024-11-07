package com.metrodora.service;

import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Ciclo;
import java.util.List;

public interface iCicloService {

    public List<Ciclo> listarCiclos();

    public String ciloByID(String idCiclo);

    public void insertarCicloConAsignaturas(Ciclo ciclo, List<Asignatura> asignaturas);

    public void InsertarCiclo(Ciclo ciclo);

    public void eliminarCicloPorId(int cicloId);

    public String obtenerNombreCicloPorId(int idCiclo);
}
