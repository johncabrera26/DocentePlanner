package com.metrodora.service;

import com.metrodora.data.iCicloDao;
import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Ciclo;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CicloService implements iCicloService {

    @Inject
    private iCicloDao cicloDao;

    @Override
    public List<Ciclo> listarCiclos() {

        return cicloDao.findAllCiclos();
    }

    @Override
    public void insertarCicloConAsignaturas(Ciclo ciclo, List<Asignatura> asignaturas) {

        cicloDao.insertarCicloConAsignaturas(ciclo, asignaturas);
    }

    @Override
    public String ciloByID(String idCiclo) {

        return cicloDao.ciloByID(idCiclo);

    }

    @Override
    public void InsertarCiclo(Ciclo ciclo) {

        cicloDao.InsertarCiclo(ciclo);
    }

    @Override
    public void eliminarCicloPorId(int cicloId) {

        cicloDao.eliminarCicloPorId(cicloId);
    }

    @Override
    public String obtenerNombreCicloPorId(int idCiclo) {
        return cicloDao.obtenerNombreCicloPorId(idCiclo);
    }

}
