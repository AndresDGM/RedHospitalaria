/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.modelo;

/**
 *
 * @author david
 */
public class Administrador extends Doctor{
    

    public Administrador() {
    }

    public Administrador(long cedula, String nombre, String genero, String eps, String password, String correo, String fechaNacimiento, String fechaRegistro, String departamento, String municipio, String direccion) {
        super(cedula, nombre, genero, eps, password, correo, fechaNacimiento, fechaRegistro, departamento, municipio, direccion, "");
    }
    
    public Administrador(Paciente user){
        super(user, "-");
    }
}
