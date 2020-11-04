/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml;

import controladores.DepartamentoJpaController;
import java.util.*;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;

/**
 *
 * @author jdmar
 */
@Named(value = "departamentoMBean")
@RequestScoped
public class departamentoMBean {

    /**
     * Creates a new instance of departamentoMBean
     */
    public departamentoMBean() {
    }
    
    private int idDepartamento;
    private String nombreDepto;
    private int cantEmpleados;
    private Telefono codTels;   
    
    public int getIdDepartamento() {
        return idDepartamento;
    }   
    

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepto() {
        return nombreDepto;
    }

    public void setNombreDepto(String nombreDepto) {
        this.nombreDepto = nombreDepto;
    }

    public int getCantEmpleados() {
        return cantEmpleados;
    }

    public void setCantEmpleados(int cantEmpleados) {
        this.cantEmpleados = cantEmpleados;
    }

    public Telefono getCodTels() {
        return codTels;
    }

    public void setCodTels(Telefono codTels) {
        this.codTels = codTels;
    } 
    
    //Metodos de acceso a datos
    public List<Departamento> getTablaDepartamentos() throws NamingException{
        DepartamentoJpaController ctrl = new DepartamentoJpaController();
        List<Departamento> lst = ctrl.findDepartamentoEntities();
        return lst;
    }
    
    public void agregarDepartamento(){
        try{
            DepartamentoJpaController ctrl = new DepartamentoJpaController();
            ctrl.create(new Departamento(idDepartamento, nombreDepto, cantEmpleados, codTels));
        }catch (Exception e){      
            System.out.print(e);
        }
    }
    
    public void modificarDepartamento(){
        try{
            DepartamentoJpaController ctrl = new DepartamentoJpaController();
            ctrl.edit(new Departamento(idDepartamento, nombreDepto, cantEmpleados, codTels));
        }catch (Exception e){       
            System.out.print(e);
        }
    }
    
    public void eliminarDepartamento(){
        try{
            DepartamentoJpaController ctrl = new DepartamentoJpaController();
            ctrl.destroy(idDepartamento);
        }catch (Exception e){     
            System.out.print(e);
        }
    }
    
}
