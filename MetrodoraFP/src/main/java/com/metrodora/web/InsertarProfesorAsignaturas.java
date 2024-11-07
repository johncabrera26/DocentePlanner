package com.metrodora.web;

import com.metrodora.dominio.Profesor;
import com.metrodora.service.iHorarioProfesorService;
import com.metrodora.service.iProfesorService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InsertarProfesorAsignaturasServlet", urlPatterns = {"/InsertarProfesorAsignaturasServlet"})
public class InsertarProfesorAsignaturas extends HttpServlet {

    @Inject
    private iProfesorService profesorService;

    @Inject
    private iHorarioProfesorService horarioProfesorService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener datos del formulario
        String nombreProfesor = request.getParameter("nombreProfesor");
        String apellidoProfesor = request.getParameter("apellidoProfesor");
        String[] asignaturasSeleccionadas = request.getParameterValues("asignatura");

        // Validar los datos recibidos
        if (nombreProfesor == null || apellidoProfesor == null || asignaturasSeleccionadas == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan datos obligatorios");
            return;
        }

        // Crear un nuevo profesor
        Profesor profesor = new Profesor();
        profesor.setNombre(nombreProfesor);
        profesor.setApellido(apellidoProfesor);

        // Insertar el profesor en la base de datos (asumiendo que existe un servicio llamado ProfesorService)
        profesorService.insertarProfesor(profesor);

        // Recuperar el ID del profesor insertado
        int idProfesor = profesorService.obtenerUltimoIDInsertado();

        // Asociar al profesor con las asignaturas seleccionadas en la tabla de horarios
        List<Integer> idAsignaturas = new ArrayList<>();
        for (String idAsignatura : asignaturasSeleccionadas) {
            idAsignaturas.add(Integer.parseInt(idAsignatura));
        }

        // Insertar las relaciones entre el profesor y las asignaturas seleccionadas 
        for (int idAsignatura : idAsignaturas) {
            horarioProfesorService.insertarHorarioProfesor(idProfesor, idAsignatura);
        }

        response.sendRedirect("ListadoProfesoresAsignaturas");
    }
}
