/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml;

import controladores.EmpleadoJpaController;
import java.util.*;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;

/**
 *
 * @author jdmar
 */
@Named(value = "empleadoMBean")
@RequestScoped
public class empleadoMBean {

    /**
     * Creates a new instance of empleadoMBean
     */
    public empleadoMBean() {
    }
    
    private int codEmpleado;
    private String nombres;
    private String apellidos;
    private int idDepartamento;
    private Float salario;
    private int edad;
    private Usuarios idUsuario;

    public int getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(int codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    //Metodos de acceso a datos
    public List<Empleado> getTablaEmpleado() throws NamingException{
        EmpleadoJpaController ctrl = new EmpleadoJpaController();
        List<Empleado> lst = ctrl.findEmpleadoEntities();
        return lst;
    }
    
    public void agregarEmpleado(){
        try{
            EmpleadoJpaController ctrl = new EmpleadoJpaController();
            ctrl.create(new Empleado(codEmpleado, nombres, apellidos, salario, edad, idDepartamento, idUsuario));
        }catch(Exception e){   
            System.out.print(e);
        }
    }
    
    public void modificarEmpleado(){
        try{
            EmpleadoJpaController ctrl = new EmpleadoJpaController();
            ctrl.edit(new Empleado(codEmpleado, nombres, apellidos, salario, edad, idDepartamento, idUsuario));
        }catch(Exception e){
            System.out.print(e);
        }
    }
    
    public void eliminarEmpleado(){
        try{
            EmpleadoJpaController ctrl = new EmpleadoJpaController();
            ctrl.destroy(codEmpleado);
        }catch(Exception e){            
            System.out.print(e);
        }
    }
}
