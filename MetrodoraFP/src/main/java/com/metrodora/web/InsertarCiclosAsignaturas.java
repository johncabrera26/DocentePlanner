package com.metrodora.web;

import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Ciclo;
import com.metrodora.dominio.Horarioasignatura;
import com.metrodora.service.iAsignaturaService;
import com.metrodora.service.iCicloService;
import com.metrodora.service.iHorarioAsignaturaService;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InsertarCiclosAsignaturas", urlPatterns = {"/InsertarCiclosAsignaturas"})
public class InsertarCiclosAsignaturas extends HttpServlet {

    @Inject
    private iCicloService cicloService;

    @Inject
    private iAsignaturaService asignaturaService;

    @Inject
    private iHorarioAsignaturaService horarioAsignaturaService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener datos del formulario
        String nombreCiclo = request.getParameter("nombreCiclo");
        String[] nombresAsignaturas = request.getParameterValues("asignatura[]");
        String[] horasSemanales = request.getParameterValues("horasSemanales[]");

        // Crear un nuevo ciclo
        Ciclo ciclo = new Ciclo();
        ciclo.setNombre(nombreCiclo);
        // Insertar el ciclo en la base de datos
        cicloService.InsertarCiclo(ciclo);

        for (int i = 0; i < nombresAsignaturas.length; i++) {
            String nombreAsignatura = nombresAsignaturas[i];
            String horas = horasSemanales[i];

            // Crear una nueva instancia de Asignatura
            Asignatura asignatura = new Asignatura();
            asignatura.setNombre(nombreAsignatura.trim());
            asignatura.setHorasSemanales(Integer.parseInt(horas)); // Asignar las horas semanales a la asignatura
            // Insertar la asignatura en la base de datos
            asignaturaService.insertarAsignatura(asignatura);

            // Crear un nuevo horario de asignatura con el ciclo y la asignatura
            Horarioasignatura horarioAsignatura = new Horarioasignatura();
            horarioAsignatura.setIDCiclo(ciclo);
            horarioAsignatura.setIDAsignatura(asignatura);
            // Insertar el horario de asignatura en la base de datos
            horarioAsignaturaService.insertarHorarioAsignatura(horarioAsignatura);
        }

        response.sendRedirect("ListadoCiclosAsignaturas");
    }
}
