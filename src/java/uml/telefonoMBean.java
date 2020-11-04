/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml;

import controladores.TelefonoJpaController;
import java.util.*;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;

/**
 *
 * @author jdmar
 */
@Named(value = "telefonoMBean")
@RequestScoped
public class telefonoMBean {

    /**
     * Creates a new instance of telefonoMBean
     */
    public telefonoMBean() {
    }
    
    private int codTels;
    private String telefono;
    private String telDescrip;

    public int getCodTels() {
        return codTels;
    }

    public void setCodTels(int codTels) {
        this.codTels = codTels;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelDescrip() {
        return telDescrip;
    }

    public void setTelDescrip(String telDescrip) {
        this.telDescrip = telDescrip;
    }
    
    public List<Telefono> getTablaTelefono() throws NamingException{
        TelefonoJpaController ctrl = new TelefonoJpaController();
        List<Telefono> lst = ctrl.findTelefonoEntities();
        return lst;
    }
    
    public void crearTelefono(){
        try{
            TelefonoJpaController ctrl = new TelefonoJpaController();
            ctrl.create(new Telefono(codTels, telefono, telDescrip));            
        }catch(Exception e){  
            System.out.print(e);
        }
    }
    
    public void modificarTelefono(){
        try{
            TelefonoJpaController ctrl = new TelefonoJpaController();
            ctrl.edit(new Telefono(codTels, telefono, telDescrip));            
        }catch(Exception e){    
            System.out.print(e);
        }
    }
    
    public void eliminarTelefono(){
        try{
            TelefonoJpaController ctrl = new TelefonoJpaController();
            ctrl.destroy(codTels);
        }catch(Exception e){   
            System.out.print(e);
        }
    }
}
