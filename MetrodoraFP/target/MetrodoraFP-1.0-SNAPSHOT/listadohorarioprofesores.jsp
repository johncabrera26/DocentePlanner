<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Horarios de Profesores</title>
    <style>
        body {
            background-color: white;
            font-family: Arial, sans-serif;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        .libre {
            background-color: green;
        }
        .profesor-section {
            margin-bottom: 20px;
        }
        .button-container {
            display: flex;
            gap: 10px;
        }
        .add-button {
            background-color: #153544;
            color: white;
            font-size: 20px;
            border: none;
            cursor: pointer;
            padding: 10px;
            border-radius: 50%;
            display: block;
            margin: 20px auto;
            width: 50px; /* Ajustar el ancho */
            height: 50px; /* Ajustar la altura */
        }
        .edit-button, .delete-button {
            background-color: #153544;
            color: white;
            font-size: 16px;
            border: none;
            cursor: pointer;
            padding: 5px 10px;
            border-radius: 4px;
        }
        .delete-button {
            background-color: red;
        }
        #buscador {
            width: 50%;
            padding: 12px 20px;
            margin: 12px 0;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
    </style>
    <script>
        function buscarProfesor() {
            const input = document.getElementById('buscador').value.toLowerCase();
            const profesorSections = document.getElementsByClassName('profesor-section');

            for (let section of profesorSections) {
                const nombreProfesor = section.getElementsByTagName('h3')[0].innerText.toLowerCase();
                if (nombreProfesor.includes(input)) {
                    section.style.display = '';
                } else {
                    section.style.display = 'none';
                }
            }
        }
    </script>
</head>
<body>
    <h2>Horarios de Profesores</h2>
    <input type="text" id="buscador" onkeyup="buscarProfesor()" placeholder="Buscar profesor...">

    <c:forEach items="${horariosPorProfesor}" var="entry">
        <div class="profesor-section">
            <h3>${entry.key.nombre}</h3> <!-- Esto muestra el nombre del profesor -->
            <table>
                <thead>
                    <tr>
                        <th>Día</th>
                        <th>Asignatura</th>
                        <th>Hora de inicio</th>
                        <th>Hora de fin</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${entry.value}" var="horario">
                        <tr style="background-color: ${horario.diaSemana eq 'LUNES' ? 'TURQUOISE' :
                                                       horario.diaSemana eq 'MARTES' ? 'gray' :
                                                       horario.diaSemana eq 'MIERCOLES' ? 'white' :
                                                       horario.diaSemana eq 'JUEVES' ? 'burlywood' :
                                                       horario.diaSemana eq 'VIERNES' ? 'peru' : 'white'};">
                            <td>${horario.diaSemana}</td>
                            <td class="${nombreAsignaturaMap[horario.IDHorarioProfesor] eq 'Libre' ? 'libre' : ''}">
                                ${nombreAsignaturaMap[horario.IDHorarioProfesor]}
                            </td>
                            <td>${horario.horaInicio.hours}:${horario.horaInicio.minutes}</td>
                            <td>${horario.horaFin.hours}:${horario.horaFin.minutes}</td>
                            <td>
                                <div class="button-container">
                                    <form action="EditarHorarioFormularioServlet" method="get">
                                        <input type="hidden" name="idHorario" value="${horario.IDHorarioProfesor}" />
                                        <input type="hidden" name="asignaturas" value="${asignaturas}" />
                                        <button type="submit" class="edit-button">Editar</button>
                                    </form>
                                        <form action="EliminarFranjaHorarioServlet" method="POST">
                                        <input type="hidden" name="idHorario" value="${horario.IDHorarioProfesor}" />
                                        
                                        <button type="submit" class="delete-button">Eliminar</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form action="CrearHorarioFormularioServlet" method="get">
                <input type="hidden" name="profesorId" value="${entry.key.id}" />
                <button type="submit" class="add-button">+</button>
            </form>
        </div>
    </c:forEach>
</body>
</html>
