<%-- 
    Document   : principal
    Created on : 9/09/2022, 10:42:26 AM
    Author     : Usuario Local
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Menu</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-info">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">

                    <li class="nav-item active">
                        <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=home" target="myFrame">Home</a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none; display: ${perbot.getPermisos()}" class="btn btn-outline-light" href="Controlador?menu=permisos&accion=Listar" target="myFrame">Permisos</a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none; display: ${perbot.getGrupos()}" class="btn btn-outline-light" href="Controlador?menu=grupos&accion=Listar" target="myFrame">Grupos</a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none; display: ${perbot.getGruposper()}" class="btn btn-outline-light" href="Controlador?menu=gruposPer&accion=Listar" target="myFrame">Grupo Permisos</a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none; display: ${perbot.getUsuarios()}" class="btn btn-outline-light" href="Controlador?menu=usuarios&accion=Listar" target="myFrame">Usuarios</a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none; display: ${perbot.getClientes()}" class="btn btn-outline-light" href="Controlador?menu=clientes&accion=Listar" target="myFrame">Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none; display: ${perbot.getRecibos()}" class="btn btn-outline-light" href="Controlador?menu=recibos&accion=Listar" target="myFrame">Recibos</a>
                    </li>
                    <li class="nav-item">
                        <a style="margin-left: 10px; border: none; display: ${perbot.getReportes()}" class="btn btn-outline-light" href="Controlador?menu=reportes&accion=Listar" target="myFrame">Reporte</a>
                    </li>
                </ul>
            </div>
            <div class="dropdown" >
                <button style="border: none" class="btn btn-outline-light dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                    ${usuario.getNombre()} ${usuario.getApellido()}
                </button>
                <div class="dropdown-menu text-center">
                    <li><a class="dropdown-item" href="#">
                            <img src="img/usuario.png" alt=""/>
                        </a></li>
                        <li><a class="dropdown-item" href="#">${usuario.getUser()}</a></li>
                    <li><a class="dropdown-item" href="#">ID: ${usuario.getId()}</a></li>
                    <div class="dropdown-divider"></div>
                    <form action="Validar" method="POST">
                        <button name="accion" value="Salir" class="dropdown-item" href="#">Salir</button>
                    </form>
                </div>
            </div>
        </nav>
        <div class="m-4" style="height: 550px;">
            <iframe name="myFrame" style="height: 100%; width: 100%" src="Controlador?menu=home" ></iframe>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
    </body>
</html>

