package com.metrodora.service;

import com.metrodora.data.iAsignaturaDao;
import com.metrodora.dominio.Asignatura;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AsignaturaService implements iAsignaturaService {

    @Inject
    private iAsignaturaDao AsignaturaDao;

    @Override
    public List<Asignatura> listarAsignatura() {

        return AsignaturaDao.findAllAsignaturas();
    }

    @Override
    public List<String> obtenerNombresAsignaturas(String[] idAsignaturas) {

        return AsignaturaDao.obtenerNombresAsignaturas(idAsignaturas);

    }

    @Override
    public void insertarAsignatura(Asignatura asignatura) {

        AsignaturaDao.insertarAsignatura(asignatura);
    }

    @Override
    public List<Asignatura> obtenerAsignaturasPorCicloId(int cicloId) {

        return AsignaturaDao.obtenerAsignaturasPorCicloId(cicloId);
    }

    @Override
    public void eliminarAsignaturaPorId(int asignaturaId) {

        AsignaturaDao.eliminarAsignaturaPorId(asignaturaId);

    }

    @Override
    public void eliminarAsignaturasPorCicloId(int cicloId) {

        AsignaturaDao.eliminarAsignaturasPorCicloId(cicloId);
    }

    @Override
    public String obtenerNombreAsignaturaPorId(int idAsignatura) {
        return AsignaturaDao.obtenerNombreAsignaturaPorId(idAsignatura);

    }

    @Override
    public Asignatura findById(int id) {
        return AsignaturaDao.findById(id);
    }
}
