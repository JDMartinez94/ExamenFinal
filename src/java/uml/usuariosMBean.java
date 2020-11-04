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
import javax.naming.NamingException;

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
    
}
