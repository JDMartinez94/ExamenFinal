/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml;

import controladores.UsuariosJpaController;
import java.util.*;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jdmar
 */
@Named(value = "usuariosMBean")
@RequestScoped
public class usuariosMBean {

    /**
     * Creates a new instance of usuariosMBean
     */
    public usuariosMBean() {
    }
    
    private int idusuario;
    private String usuario;
    private String contra;
    private String nivel;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    public List<Usuarios> getTablaUsuarios() throws NamingException{
        UsuariosJpaController ctrl = new UsuariosJpaController();
        List<Usuarios> lst = ctrl.findUsuariosEntities();
        return lst;
    }
    
    public void crearUsuarios(){
        try{
            UsuariosJpaController ctrl = new UsuariosJpaController();
            ctrl.create(new Usuarios(idusuario, usuario, contra, nivel));
        }catch(Exception e){
            System.out.print(e);
        }
    }
    
    public void modificarUsuarios(){
        try{
            UsuariosJpaController ctrl = new UsuariosJpaController();
            ctrl.edit(new Usuarios(idusuario, usuario, contra, nivel));
        }catch(Exception e){
            System.out.print(e);
        }
    }
    
    public void eliminarUsuarios(){
        try{
            UsuariosJpaController ctrl = new UsuariosJpaController();
            ctrl.destroy(idusuario);
        }catch(Exception e){
            System.out.print(e);
        }
    }
    
    public String validateUsernamePassword()
    {
        try {
            List<Usuarios> ls = new ArrayList<>();
            UsuariosJpaController ctrl = new UsuariosJpaController();
            ls = ctrl.findUsuariosEntities();

            if(usuario.isEmpty() && contra.isEmpty())
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Debe ingresar usuario y contraseña."));
            }
            else
            {
                for(Usuarios us : ls)
                {
                    if(us.getUsuario().equals(usuario) && us.getContra().equals(contra))
                    {
                        setNivel(us.getNivel());
                        String uss = us.getUsuario();
                        String niv = us.getNivel();
                        int idus = us.getIdusuario();
                        HttpSession session = SessionUtils.getSession();
                        session.setAttribute("username", uss);
                        session.setAttribute("level", niv);
                        session.setAttribute("iduser", idus);                        
                        return "admin"; 
                    }
                    else
                    {
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Usuario o contraseña inválidos. Intente de nuevo."));
                    }
                }
                
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return "login_index";
    }
    
}
