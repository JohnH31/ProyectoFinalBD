<%-- 
    Document   : fechast
    Created on : 5/11/2022, 01:11:02 AM
    Author     : Usuario Local
--%>

<%@page import="Modelo.Conector"%>
<%@page import ="java.io.*"%>
<%@page import ="java.util.*"%>
<%@page import ="net.sf.jasperreports.engine.*"%>
<%@page import ="javax.servlet.ServletOutputStream"%>
<%@page import ="java.sql.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generar Reporte</title>
    </head>
    <body>
        <%            
            // Declaración de la conexión y el conector
            Connection con;
            Conector cn = new Conector();

            try {
                // Conexión a la base de datos
                cn.conectar();
                con = cn.getcon();

                // Archivo del reporte Jasper
                File reportfile = new File(application.getRealPath("/PorFechaTotal.jasper"));

                // Parámetros del reporte
                Map<String, Object> parameter = new HashMap<String, Object>();
                String in = request.getParameter("txtinit");
                String outt = request.getParameter("txtend");
                
                // Validación de parámetros
                if (in != null && outt != null) {
                    parameter.put("init", in);
                    parameter.put("end", outt);

                    // Generar reporte como PDF
                    byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), parameter, con);
                    
                    // Configuración de respuesta HTTP para PDF
                    response.setContentType("application/pdf");
                    response.setContentLength(bytes.length);

                    try (ServletOutputStream outputstream = response.getOutputStream()) {
                        outputstream.write(bytes, 0, bytes.length);
                        outputstream.flush();
                    }
                } else {
                    out.println("Por favor, proporciona las fechas de inicio y fin.");
                }
            } catch (Exception e) {
                out.println("Error al generar el reporte: " + e.getMessage());
            } finally {
                // Cerrar la conexión
                cn.desconectar();
            }
        %>
    </body>
</html>
