<%-- 
    Document   : vistatelefonos
    Created on : 3/11/2020, 10:35:31 PM
    Author     : AlcidesMedina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uml.Usuarios"%>
<%@page import="controladores.UsuariosJpaController"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TELEFONOS</title>
        
        <script>
            function cargar(idus,us,con,niv)
            {
                document.modalito.txtIdusuario.value = idus; 
                document.modalito.txtUsuario.value = us; 
                document.modalito.txtContra.value = con;
                document.modalito.txtNivel.value = niv;
            }
        </script>
        
    </head>
    <body>
       <%
            HttpSession sesion = request.getSession(); 
            //validar que solo si hay sesion activa pueda entrar
            //la unica sesion es usuario y nivel
            if(sesion.getAttribute("usuario")==null && sesion.getAttribute("nivel")==null)
            {
                response.sendRedirect("../index.xhtml");
            }
            
            if(sesion.getAttribute("usuario")!=null && sesion.getAttribute("nivel")!=null)
            {
                String useruser = sesion.getAttribute("usuario").toString();
                String nivel = sesion.getAttribute("nivel").toString();
        %>
        
        <nav class="navbar navbar-expand navbar-dark bg-dark static-top">
            
        <hr>
        <p style="color:white; font-size: 20px;"> Bienvenido <%=useruser%> (Nivel: <%=nivel%>) | <a style="color:white;" href="${pageContext.request.contextPath}/index.jsp"> | Home | </a></p> 
        <hr>
        
        </nav>
     
         <%
            }
            response.setHeader("Pragma","no-cache");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
            response.setDateHeader ("Expires", 0); 
        %>
         
        <h1 align="center">Formulario Usuarios</h1>
        <div style="width: 100%; position: relative">
            <hr>

            <button style="margin-left: 45%;" type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#miModal">
                Ingresar Nuevo
                </button>
                <br><br>

                <pre align="center">CONTENIDO DE LA TABLA USUARIOS</pre>
                <br>
        
            <%
                List <Usuarios> ls; 
                UsuariosJpaController ctrl = new UsuariosJpaController(); 
                Usuarios usu = new Usuarios();
                ls = ctrl.findUsuariosEntities(); 


                if(request.getParameter("btnGuardar")!=null || request.getParameter("btnMod")!=null || request.getParameter("btnEliminar")!=null)
                {
                    //guardar en un objeto producto lo del form
                    usu.setIdusuario(Integer.parseInt(request.getParameter("txtIdusuario")));
                    usu.setUsuario(request.getParameter("txtUsuario"));
                    usu.setContra(request.getParameter("txtContra"));
                    usu.setNivel(request.getParameter("txtNivel"));
                }



                if(request.getParameter("btnGuardar")!=null)
                {
                    ctrl.create(usu);
                    out.print("<script>alert('Exito al insertar');window.location.href = 'vistausuarios.jsp';</script>");
                    ls=ctrl.findUsuariosEntities();
                }
                else if(request.getParameter("btnMod")!=null)
                {                    
                    ctrl.edit(usu);
                    out.print("<script>alert('Exito al modificar');window.location.href = 'vistausuarios.jsp';</script>");
                    ls=ctrl.findUsuariosEntities();
                }
                else if(request.getParameter("btnEliminar")!=null)
                {                    
                    ctrl.destroy(usu.getIdusuario());
                    out.print("<script>alert('Exito al eliminar');window.location.href = 'vistausuarios.jsp';</script>");
                    ls=ctrl.findUsuariosEntities();
                }

            %>

            <div style="width: 700px; position: relative; margin-left: 27%;">
                <table border="1" class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th class="text-center">MODIFICAR</th>
                            <th class="text-center">ID USUARIO</th>
                            <th class="text-center">NOMBRE DE USAURIO</th>
                            <th class="text-center">CONTRASEÑA</th>
                            <th class="text-center">NIVEL</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for(Usuarios c : ls)
                            {

                        %>
                        <tr>
                            <td class="text-center"><%= c.getIdusuario()%>
                                <a href="javascript:cargar('<%= c.getIdusuario()%>','<%= c.getUsuario()%>','<%= c.getContra()%>','<%= c.getNivel()%>')">
                                <img src="../recursos/editar.jpg" width="25px" height="25px" data-toggle="modal" data-target="#miModal">
                                </a>
                            </td>
                            <td class="text-center"><%= c.getIdusuario()%></td>
                            <td class="text-center"><%= c.getUsuario()%></td>
                            <td class="text-center"><%= c.getContra()%></td>
                            <td class="text-center"><%= c.getNivel()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
            
    </body>

        <!--Este es el formulario modal-->
            <div class="modal fade" id="miModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                        <div class="modal-content">
                                <div class="modal-header">

                                        <h4 class="modal-title" id="myModalLabel">Formulario de Registro</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                        </button>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <form action="vistausuarios.jsp" method="POST" name="modalito">
                                            <input type="text" name="txtIdusuario" placeholder="ID de Usuario" class="form-control"  /><br>
                                            <input type="text" name="txtUsuario" placeholder="Nombre de Usuario" class="form-control" /><br>
                                            <input type="text" name="txtContra" placeholder="Contraseña" class="form-control" /><br>
                                            <input type="text" name="txtNivel" placeholder="Nivel" class="form-control" /><br>

                                            <input type="submit" name="btnGuardar" class="btn btn-primary" value="Guardar"/>
                                            <input type="submit" name="btnMod" class="btn btn-info" value="Modificar"/>
                                            <input type="submit" name="btnEliminar" class="btn btn-danger" value="Eliminar"/>
                                            <input type="hidden" value="nuevo" name="opcion" /><br>
                                        </form>
                                    </div>
                                </div>
                        </div>
                </div>
            </div>
        <!--Hasta aquí el modal-->
    </body>
</html>
