package com.metrodora.web;

import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Horarioprofesor;
import com.metrodora.service.iAsignaturaService;
import com.metrodora.service.iHorarioProfesorService;
import java.io.IOException;
import java.sql.Time;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditarHorarioServlet", urlPatterns = {"/EditarHorarioServlet"})
public class EditarHorarioServlet extends HttpServlet {

    @Inject
    private iHorarioProfesorService horarioProfesorService;
    @Inject

    private iAsignaturaService asignaturaservice;

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idHorarioParam = request.getParameter("idHorario");
        if (idHorarioParam == null || idHorarioParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID del horario es obligatorio");
            return;
        }

        int idHorario;
        try {
            idHorario = Integer.parseInt(idHorarioParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de horario inválido");
            return;
        }

        Horarioprofesor horario = horarioProfesorService.findById(idHorario);
        if (horario == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontró el horario especificado");
            return;
        }

        request.setAttribute("horario", horario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditarHorario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idHorario = Integer.parseInt(request.getParameter("idHorario"));
        String diaSemana = request.getParameter("diaSemana");
        String horaInicioString = request.getParameter("horaInicio");
        String horaFinString = request.getParameter("horaFin");
        String asignaturaIdString = request.getParameter("asignaturaId");

        if (horaInicioString.matches("\\d{2}:\\d{2}") && horaFinString.matches("\\d{2}:\\d{2}")) {
            horaInicioString += ":00";
            horaFinString += ":00";

            // Convertir los Strings de hora a objetos Time
            Time horaInicio = Time.valueOf(horaInicioString);
            Time horaFin = Time.valueOf(horaFinString);

            Horarioprofesor horario = horarioProfesorService.findById(idHorario);
            if (horario != null) {
                horario.setDiaSemana(diaSemana);
                horario.setHoraInicio(horaInicio);
                horario.setHoraFin(horaFin);

                // Actualizar la asignatura del horario
                if (asignaturaIdString == null || asignaturaIdString.isEmpty()) {
                    horario.setIDAsignatura(null); // Si el ID de asignatura está vacío, establecer como null
                } else {
                    int asignaturaId = Integer.parseInt(asignaturaIdString);
                    Asignatura asignatura = asignaturaservice.findById(asignaturaId);
                    horario.setIDAsignatura(asignatura);
                }

                horarioProfesorService.actualizarHorario(horario);
            }

            response.sendRedirect("HorarioProfesoresServlet");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de hora incorrecto");
        }
    }
}
