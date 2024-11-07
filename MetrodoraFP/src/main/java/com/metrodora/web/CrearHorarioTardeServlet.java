package com.metrodora.web;

import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Horarioasignatura;
import com.metrodora.dominio.Horarioprofesor;
import com.metrodora.dominio.Profesor;
import com.metrodora.service.iHorarioAsignaturaService;
import com.metrodora.service.iHorarioProfesorService;
import com.metrodora.service.iProfesorService;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CrearHorarioTardeServlet", urlPatterns = {"/CrearHorarioTardeServlet"})
public class CrearHorarioTardeServlet extends HttpServlet {

    @Inject
    private iProfesorService profesorService;

    @Inject
    private iHorarioAsignaturaService horarioAsignaturaService;

    @Inject
    private iHorarioProfesorService horarioProfesorService;

    private Map<Profesor, List<Asignatura>> profesorAsignaturasMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cicloIdParam = request.getParameter("idCiclo");
        if (cicloIdParam == null || cicloIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID del ciclo es obligatorio");
            return;
        }

        int idCiclo;
        try {
            idCiclo = Integer.parseInt(cicloIdParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de ciclo inválido");
            return;
        }

        List<Horarioasignatura> horarios = horarioAsignaturaService.obtenerAsignaturasPorCiclo(idCiclo);
        if (horarios == null || horarios.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontraron asignaturas para el ciclo especificado");
            return;
        }

        Map<String, String> mapaAsignacion = asignarAsignaturasAFranjasHorarias(horarios);
        Map<Profesor, List<Asignatura>> profesorAsignaturaMap = obtenerProfesorAsignaturaMap(horarios);

        asignarProfesoresAAsignaturas(mapaAsignacion, profesorAsignaturaMap);

        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        String[] franjasHorarias = {"15:30:00-16:25:00", "16:25:00-17:20:00", "17:20:00-18:15:00", "18:45:00-19:40:00", "19:40:00-20:35:00", "20:35:00-21:30:00"};

        request.setAttribute("diasSemana", diasSemana);
        request.setAttribute("franjasHorarias", franjasHorarias);
        request.setAttribute("mapaAsignacion", mapaAsignacion);
        RequestDispatcher dispatcher = request.getRequestDispatcher("HorarioCreadoTarde.jsp");
        dispatcher.forward(request, response);
    }

    private Map<String, String> asignarAsignaturasAFranjasHorarias(List<Horarioasignatura> horarios) {
        Map<String, String> mapaAsignacion = new HashMap<>();
        Map<Asignatura, Integer> horasAsignadasPorAsignatura = new HashMap<>();
        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        String[] franjasHorarias = {"15:30:00-16:25:00", "16:25:00-17:20:00", "17:20:00-18:15:00", "18:45:00-19:40:00", "19:40:00-20:35:00", "20:35:00-21:30:00"};

        List<String> franjasHorariasList = new ArrayList<>();
        for (String dia : diasSemana) {
            for (String franjaHoraria : franjasHorarias) {
                franjasHorariasList.add(dia + " - " + franjaHoraria);
            }
        }

        Random random = new Random();
        while (!franjasHorariasList.isEmpty()) {
            String key = franjasHorariasList.remove(random.nextInt(franjasHorariasList.size()));
            Asignatura asignaturaDisponible = obtenerAsignaturaDisponible(horarios, horasAsignadasPorAsignatura);
            if (asignaturaDisponible != null) {
                String value = asignaturaDisponible.getNombre();
                mapaAsignacion.put(key, value);
                horasAsignadasPorAsignatura.put(asignaturaDisponible, horasAsignadasPorAsignatura.getOrDefault(asignaturaDisponible, 0) + 1);
            } else {
                mapaAsignacion.put(key, "Sin asignar");
            }
        }
        return mapaAsignacion;
    }

    private Asignatura obtenerAsignaturaDisponible(List<Horarioasignatura> horarios, Map<Asignatura, Integer> horasAsignadasPorAsignatura) {
        for (Horarioasignatura horario : horarios) {
            Asignatura asignatura = horario.getIDAsignatura();
            int horasAsignadas = horasAsignadasPorAsignatura.getOrDefault(asignatura, 0);
            if (horasAsignadas < asignatura.getHorasSemanales()) {
                return asignatura;
            }
        }
        return null;
    }

    private void asignarProfesoresAAsignaturas(Map<String, String> mapaAsignacion, Map<Profesor, List<Asignatura>> profesorAsignaturasMap) {
        for (Map.Entry<String, String> entry : mapaAsignacion.entrySet()) {
            String key = entry.getKey();
            String asignaturaNombre = entry.getValue();
            if (!asignaturaNombre.equals("Sin asignar")) {
                String[] parts = key.split(" - ");
                String dia = parts[0];
                String franjaHoraria = parts[1];
                Profesor profesorDisponible = obtenerProfesorDisponibleParaAsignatura(asignaturaNombre, dia, franjaHoraria, profesorAsignaturasMap);
                if (profesorDisponible != null) {
                    String profesorNombre = profesorDisponible.getNombre();
                    String profesorApellido = profesorDisponible.getApellido();
                    String newValue = "<b>" + profesorNombre + " " + profesorApellido + "</b> - " + asignaturaNombre;
                    mapaAsignacion.put(key, newValue);
                }
            }
        }
    }

    private boolean profesorEstaDisponibleEnFranja(Profesor profesor, String dia, String franjaHoraria) {
        if (profesor == null) {
            return false;
        }

        List<Horarioprofesor> horarios = horarioProfesorService.obtenerHorariosPorProfesorYDia(profesor.getIDProfesor(), dia);
        LocalTime franjaInicio = LocalTime.parse(franjaHoraria.split("-")[0]);
        LocalTime franjaFin = LocalTime.parse(franjaHoraria.split("-")[1]);

        for (Horarioprofesor horario : horarios) {
            Date horaInicioDate = horario.getHoraInicio();
            Date horaFinDate = horario.getHoraFin();
            Asignatura asignatura = horario.getIDAsignatura();

            // Convertir Date a LocalTime
            Instant instantInicio = horaInicioDate.toInstant();
            LocalTime horaInicio = LocalDateTime.ofInstant(instantInicio, ZoneId.systemDefault()).toLocalTime();

            Instant instantFin = horaFinDate.toInstant();
            LocalTime horaFin = LocalDateTime.ofInstant(instantFin, ZoneId.systemDefault()).toLocalTime();

            // Verificar si hay superposición de horarios
            if (!(horaInicio.isAfter(franjaFin) || horaFin.isBefore(franjaInicio))) {
                // Si hay superposición y asignatura es null, significa que el profesor no está enseñando en esa franja
                if (asignatura == null) {
                    return true; // Disponible porque no está asignado a ninguna clase en esa franja
                } else {
                    return false; // No disponible porque está enseñando otra clase
                }
            }
        }

        // Si no hay superposición con ningún horario del profesor, está disponible en esta franja horaria
        return true;
    }

    private Profesor obtenerProfesorDisponibleParaAsignatura(String asignaturaNombre, String dia, String franjaHoraria, Map<Profesor, List<Asignatura>> profesorAsignaturasMap) {
        for (Map.Entry<Profesor, List<Asignatura>> entry : profesorAsignaturasMap.entrySet()) {
            Profesor profesor = entry.getKey();
            List<Asignatura> asignaturas = entry.getValue();

            // Verifica si el profesor puede enseñar la asignatura requerida
            for (Asignatura asignatura : asignaturas) {
                if (asignatura != null && asignatura.getNombre().equals(asignaturaNombre)) {
                    // Verifica si el profesor está disponible en la franja horaria y el día requeridos
                    if (profesorEstaDisponibleEnFranja(profesor, dia, franjaHoraria)) {
                        return profesor;
                    }
                }
            }
        }
        return null; // Devuelve null si no se encuentra ningún profesor disponible
    }

    private <T> Map<Profesor, List<Asignatura>> obtenerProfesorAsignaturaMap(List<T> horarios) {
        Map<Profesor, List<Asignatura>> profesorAsignaturasMap = new HashMap<>();

        for (T horario : horarios) {
            Profesor profesor = null;
            Asignatura asignatura = null;

            if (horario instanceof Horarioasignatura) {
                asignatura = ((Horarioasignatura) horario).getIDAsignatura();
                List<Profesor> profesores = profesorService.obtenerProfesoresPorAsignatura(asignatura.getIDAsignatura());
                for (Profesor p : profesores) {
                    if (p != null) {
                        profesor = p;
                        if (!profesorAsignaturasMap.containsKey(profesor)) {
                            profesorAsignaturasMap.put(profesor, new ArrayList<>());
                        }
                        profesorAsignaturasMap.get(profesor).add(asignatura);
                    }
                }
            } else if (horario instanceof Horarioprofesor) {
                profesor = ((Horarioprofesor) horario).getIDProfesor();
                asignatura = ((Horarioprofesor) horario).getIDAsignatura();
                if (profesor != null && asignatura != null) {
                    profesorAsignaturasMap.computeIfAbsent(profesor, k -> new ArrayList<>()).add(asignatura);
                }
            }
        }

        return profesorAsignaturasMap;
    }

}
