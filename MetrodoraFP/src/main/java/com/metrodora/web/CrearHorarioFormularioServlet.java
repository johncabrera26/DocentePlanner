package com.metrodora.web;

import com.metrodora.dominio.Horarioprofesor;
import com.metrodora.dominio.Profesor;
import com.metrodora.service.iHorarioProfesorService;
import com.metrodora.service.iProfesorService;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CrearHorarioFormularioServlet", urlPatterns = {"/CrearHorarioFormularioServlet"})
public class CrearHorarioFormularioServlet extends HttpServlet {
    
    @Inject
    
    iHorarioProfesorService horarioprofesorService;
    
    @Inject
    
    iProfesorService profesorservice;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        int profesorId = Integer.parseInt(request.getParameter("profesorId"));

        // Crear una nueva franja horaria (aquí deberías validar los parámetros del formulario antes de crear la franja)
        Horarioprofesor nuevaFranjaHoraria = new Horarioprofesor();
        Profesor profesor = profesorservice.findById(profesorId);
        nuevaFranjaHoraria.setIDProfesor(profesor);
        // Establecer otros atributos de la nueva franja horaria según los parámetros del formulario

        // Aquí deberías llamar a tu servicio para guardar la nueva franja horaria en la base de datos
        horarioprofesorService.guardarHorario(nuevaFranjaHoraria);

        // Redirigir de vuelta a la página de horarios de profesores
        response.sendRedirect("HorarioProfesoresServlet");
    }
}
