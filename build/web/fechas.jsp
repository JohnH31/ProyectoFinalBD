<%-- 
    Document   : fechas
    Created on : 4/11/2022, 10:56:15 PM
    Author     : Usuario Local
--%>

<%@page import="Modelo.recibir"%>
<%@page import="Modelo.Conector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.io.*"%>
<%@page import ="java.util.*"%>
<%@page import ="net.sf.jasperreports.engine.*"%>
<%@page import ="net.sf.jasperreports.view.JasperViewer"%>
<%@page import ="javax.servlet.ServletResponse"%>
<%@page import ="net.sf.jasperreports.view.JasperViewer"%>
<%@page import ="java.sql.*"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%            
           Connection con;
           Conector cn = new Conector();
           cn.conectar();
           con=cn.getcon();
           File reportfile = new File (application.getRealPath("/PorFechas.jasper")); // Ruta del reporte
           Map<String, Object> parameter = new HashMap<String, Object>();
           String in = request.getParameter("txtin");
           String outt = request.getParameter("txtout");
           parameter.put("in",new String(in));
           parameter.put("out",new String(outt));
           byte [] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(),parameter,con);
           response.setContentType("application/pdf");
           response.setContentLength(bytes.length);
           ServletOutputStream outputstream = response.getOutputStream();
           outputstream.write(bytes,0, bytes.length);
           outputstream.flush();
           outputstream.close();
        %>
    </body>
    
</html>