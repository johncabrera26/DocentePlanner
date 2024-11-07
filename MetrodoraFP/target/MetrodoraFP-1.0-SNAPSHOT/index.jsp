<%-- 
    Document   : index
    Created on : 09-abr-2024, 13:55:59
    Author     : PC
--%>






<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Metrodora FP</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            body {
                background: aliceblue;
                margin: 0;
                padding: 0;
                overflow: hidden;
                display: flex;
                font-family: sans-serif;
                font-size: 18px;
                font-family: Arial, sans-serif;
            }
            .sidebar {
                position: fixed;
                top: 0;
                left: 0;
                height: 100%;
                width: 250px;
                background-color: #153544;
                padding-top: 20px;
                z-index: 1;
                display: flex;
                flex-direction: column;
                align-items: center;
            }
            .sidebar a {
                display: block;
                color: white;
                padding: 10px 20px;
                text-decoration: none;
            }
            .sidebar a:hover {
                background-color: #495057;
            }
            #contentWrapper {
                margin-left: 250px;
                padding: 20px;
                box-sizing: border-box;
                height: 100vh;
                overflow-y: auto;
                flex: 1;
                position: relative;
            }
            #contentFrame {
                width: 100%;
                height: 100%;
                border: none;
            }
            .right-image {
                position: absolute;
                top: 0;
                right: 0;
                width: calc(120% - 200px);
                height: 100%;
                object-fit: cover;
                z-index: -1;
            }
            .menu {
                margin-top: 90px;
                width: 100%;
            }
            .logo-container {
                display: flex;
                justify-content: center;
                width: 100%;
            }
            .logo {
                max-width: 90%;
                margin-top: 15%;
            }
            
            
        </style>
    </head>
    <body>
        <div class="sidebar">
            <div class="logo-container">
               
                <img class="logo" src="assets/logo_final.png" alt="logo"/>
               
            </div>
            <div class="menu">
                <a href="#" data-page="HorarioProfesoresServlet">Horario de profesores</a>
                <a href="#" data-page="ListadoProfesoresAsignaturas">Profesores y asignaturas</a>
                <a href="#" data-page="AsignaturaServlet">Añadir profesor y asignatura</a>
                <a href="#" data-page="registrarcicloAsignatura.jsp">Añadir ciclo y asignatura</a>
                <a href="#" data-page="ListadoCiclosAsignaturas">Ciclos y asignaturas</a>
            </div>
        </div>

        <div id="contentWrapper">
            <iframe id="contentFrame" src=""></iframe>
            <!-- Imagen fija a la derecha -->
            <img src="https://campus.metrodorafp.com/pluginfile.php/1/theme_space/loginbg/1708277299/entrada.jpg" alt="Right Image" class="right-image">
        </div>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                $(".sidebar a").click(function (e) {
                    e.preventDefault(); // Evita el comportamiento predeterminado del enlace
                    var page = $(this).data("page"); // Obtiene la URL de la página desde el atributo data
                    $("#contentFrame").attr("src", page); // Actualiza la URL del iframe
                });
            });
        </script>
    </body>
</html>

