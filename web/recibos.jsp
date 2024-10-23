<%-- 
    Document   : recibos
    Created on : 4/11/2022, 10:33:30 AM
    Author     : Usuario Local
--%>

<%@page import="Modelo.Venta"%>
<%@page import="Modelo.UsuarioDAO"%>
<%@page import="Modelo.NoFactura"%>
<%@page import="Modelo.VentaDAO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.TipoClienteDAO"%>
<%@page import="Modelo.TipoCliente"%>
<%@page import="Modelo.ClienteDAO"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="Modelo.Cliente"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Recibos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    </head>
    <body>
        <form action="Controlador?menu=recibos" method="POST">
            <div class="d d-flex">
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group">

                                <label for="lbl_id">ID</label>
                                <input type="text" name="txtid" id="txtid" class="form-control" value="${cliente.getId()}"  readonly> 
                            </div>
                            <div class="form-group">
                                <label for="lbl_nit">NIT</label>
                                <input type="text" name="txtnit" id="txtnit" value="${cliente.getNit()}" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="lbl_nom">NOMBRES</label>
                                <input type="text" name="txtno" id="txtno" value="${cliente.getNombre()}" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="lbl_ape">APELLIDOS</label>
                                <input type="text" name="txtap" id="txtap" value="${cliente.getApellido()}" class="form-control">
                            </div>
                            <br>
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" name="agregar11">
                                Buscar Cliente
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Agregar Cliente</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="dataTables_scrollBody">
                                                <table class="table table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>NIT</th>
                                                            <th>NOMBRES</th>
                                                            <th>APELLIDOS</th>
                                                            <th>DIRECCION</th>
                                                            <th>TELEFONO</th>
                                                            <th>FECHA NACIMIENTO</th>
                                                            <th>IDTIPO</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody name="tbl_Cliente" id="tbl_Cliente">
                                                        <tr>
                                                            <%                                ClienteDAO cli = new ClienteDAO();
                                                                DefaultTableModel tabla = new DefaultTableModel();
                                                                tabla = cli.leer();
                                                                for (int t = 0; t < tabla.getRowCount(); t++) {
                                                                    out.print("<tr data-id=" + tabla.getValueAt(t, 0) + " data-id_t=" + tabla.getValueAt(t, 8) + ">");
                                                                    out.print("<td>" + tabla.getValueAt(t, 1) + "</td>");
                                                                    out.print("<td>" + tabla.getValueAt(t, 2) + "</td>");
                                                                    out.print("<td>" + tabla.getValueAt(t, 3) + "</td>");
                                                                    out.print("<td>" + tabla.getValueAt(t, 4) + "</td>");
                                                                    out.print("<td>" + tabla.getValueAt(t, 5) + "</td>");
                                                                    out.print("<td>" + tabla.getValueAt(t, 6) + "</td>");
                                                                    out.print("<td>" + tabla.getValueAt(t, 7) + "</td>");
                                                                    //out.print("<td>");
                                                                    //out.print("<a style=\"margin-left: 10px; border: none\" class=\"btn btn-warning\" href=\"Controlador?accion=Editar&id=" + tabla.getValueAt(t, 0) + "\">Editar</a>");
                                                                    //out.print("<a style=\"margin-left: 10px; border: none\" class=\"btn btn-danger\" href=\"Controlador?accion=Delete&id=" + tabla.getValueAt(t, 0) + "\">Eliminar</a>");
                                                                    //out.print("</td>");
                                                                    out.print("</tr>");
                                                                }

                                                            %>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>

                                            </div> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group">
                                <label for="lbl_id">NO. FACTURA</label>
                                <input type="text" name="txtfac" id="txtfac" value="<%                                    String numSerie = "";
                                    VentaDAO vdao = new VentaDAO();
                                    numSerie = vdao.GenerarNoFac();
                                    if (numSerie == null) {

                                        numSerie = "00001";
                                        out.print(numSerie);
                                        //request.setAttribute("nserie", numSerie);
                                    } else {
                                        int incremetar = Integer.parseInt(numSerie);
                                        NoFactura gs = new NoFactura();
                                        numSerie = gs.NumeroSerie(incremetar);
                                        out.print(numSerie);
                                        //request.setAttribute("nserie", numSerie);
                                    }

                                       %>" class="form-control" readonly> 
                            </div>
                            <div class="form-group">
                                <label for="lbl_nit">FECHA</label>
                                <input type="date" name="txtfecv" id="fechaActual" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="lbl_nom">ID VENDEDOR</label>
                                <input type="text" name="txtidven" id="txtidven" value="${usuario.getId()}" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label for="lbl_nom">VENDEDOR</label>
                                <input type="text" name="txtven" id="txtven" value="${usuario.getNombre()} ${usuario.getApellido()}" class="form-control" readonly>
                            </div>
                            <br>
                            <!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal2">
  Buscar Vendedor
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel2">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NOMBRES</th>
                    <th>APELLIDOS</th>
                    <th>USUARIO</th>
                </tr>
            </thead>
            <tbody id="tbl_usuarios">
                <tr>
                    <%
                        UsuarioDAO cli1 = new UsuarioDAO();
                        DefaultTableModel tabla1 = new DefaultTableModel();
                        tabla1 = cli1.leer();
                        for (int t = 0; t < tabla1.getRowCount(); t++) {
                            out.print("<tr data-id=" + tabla1.getValueAt(t, 0) + " data-id_t=" + tabla1.getValueAt(t, 7) + ">");
                            out.print("<td>" + tabla1.getValueAt(t, 0) + "</td>");
                            out.print("<td>" + tabla1.getValueAt(t, 1) + "</td>");
                            out.print("<td>" + tabla1.getValueAt(t, 2) + "</td>");
                            out.print("<td>" + tabla1.getValueAt(t, 3) + "</td>");
                            //out.print("<td>");
                            //out.print("<a style=\"margin-left: 10px; border: none\" class=\"btn btn-warning\" href=\"Controlador?menu=usuarios&accion=Editar&id=" + tabla.getValueAt(t, 0) + "\">Editar</a>");
                            //out.print("<a style=\"margin-left: 10px; border: none\" class=\"btn btn-danger\" href=\"Controlador?menu=usuarios&accion=Delete&id=" + tabla.getValueAt(t, 0) + "\">Eliminar</a>");
                            //out.print("</td>");
                            out.print("</tr>");
                        }

                    %>
                </tr>
            </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
                        </div>
                    </div>
                </div>   
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group">
                                <label for="lbl_nit">DESCRIPCION</label>
                                <input type="text" name="txtdes" id="txtdes" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="lbl_nom">MONTO</label>
                                <input type="number" name="txtmon" id="txtmon" class="form-control">
                            </div>
                            <div class="col-sm-4 ml-auto">
                                <label for="lbl_total">TOTAL VENTA</label>
                                <input type="text" name="txttotal" value="Q. ${totalv}" id="txtmon" class="form-control" readonly>
                            </div>
                        </div>
                    </div>
                </div>  
            </div>
            <div class="text-center align-items-center justify-content-center">
                <button name="accion" id="btn_agregar" value="Agregar" class="btn bg-info">Agregar Pago</button>
                <button name="accion" id="btn_generar" value="Generar" class="btn btn-success">Generar Factura</button>
                <button name="accion" id="btn_cancelar" value="Cancelar" class="btn btn-danger">Cancelar</button>
            </div>
        </form>
        <div class="container">
            <div class="row justify-content-center">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>DESCRIPCION</th>
                            <th>MONTO</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="list" items="${lista}">
                            <tr>
                                <td>${list.getDescripcion()}</td>
                                <td>${list.getMonto()}</td>
                                <td>
                                    <a style="margin-left: 10px; border: none" class="btn btn-danger" href="Controlador?menu=recibos&accion=Eliminar&id=${list.getConteo()}">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

        <script type="text/javascript">
            window.onload = function () {
                var fecha = new Date(); //Fecha actual
                var mes = fecha.getMonth() + 1; //obteniendo mes
                var dia = fecha.getDate(); //obteniendo dia
                var ano = fecha.getFullYear(); //obteniendo año
                if (dia < 10)
                    dia = '0' + dia; //agrega cero si el menor de 10
                if (mes < 10)
                    mes = '0' + mes //agrega cero si el menor de 10
                document.getElementById('fechaActual').value = ano + "-" + mes + "-" + dia;
            }
            $('#tbl_Cliente').on('click', 'tr td', function (evt) {
                var target, id, id_t, nit, nombre, apellido, direccion, telefono, fechaNacimiento;
                target = $(event.target);
                id = target.parent().data('id');
                id_t = target.parent().data('id_t');
                nit = target.parent("tr").find("td").eq(0).html();
                nombre = target.parent("tr").find("td").eq(1).html();
                apellido = target.parent("tr").find("td").eq(2).html();
                direccion = target.parent("tr").find("td").eq(3).html();
                telefono = target.parent("tr").find("td").eq(4).html();
                fechaNacimiento = target.parent("tr").find("td").eq(5).html();
                $("#txtid").val(id);
                $("#txtnit").val(nit);
                $("#txtno").val(nombre);
                $("#txtap").val(apellido);
                $("#txtdir").val(direccion);
                $("#txttel").val(telefono);
                $("#txtfec").val(fechaNacimiento);
                $("#txtidtipo").val(id_t);
                $("#exampleModal").modal('hide');
            });
            $('#tbl_usuarios').on('click', 'tr td', function (evt) {
                var target, id, id_t, nombre, apellido, usuario, password, estado;
                target = $(event.target);
                id = target.parent().data('id');
                id_t = target.parent().data('id_t');
                nombre = target.parent("tr").find("td").eq(1).html();
                apellido = target.parent("tr").find("td").eq(2).html();
                usuario = target.parent("tr").find("td").eq(3).html();
                password = target.parent("tr").find("td").eq(4).html();
                estado = target.parent("tr").find("td").eq(5).html();
                $("#txtidven").val(id);
                $("#txtven").val(nombre+" "+apellido);
                $("#txtus").val(usuario);
                $("#exampleModal2").modal('hide');
                });
        </script>
    </body>
</html>