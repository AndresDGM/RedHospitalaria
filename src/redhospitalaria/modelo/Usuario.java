package redhospitalaria.modelo;

import java.io.Serializable;
import java.util.*;

public class Usuario implements Serializable {

    long cedula;
    String nombre;
    String password;
    String tipo;
    String correo;
    String fechaNacimiento;
    String fechaRegistro;
    String departamento;
    String municipio;
    String direccion;
    int edad;
    Queue<Cita> citas = new LinkedList<>();

    public Usuario() {
    }

    public Usuario(long cedula, String nombre, String password, String tipo, String correo, String fechaNacimiento, String fechaRegistro, String departamento, String municipio, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.password = password;
        this.tipo = tipo;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.departamento = departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        edad = calcularEdad();
    }

    private int calcularEdad() {
    Calendar today = Calendar.getInstance();
    int diffYear = Integer.parseInt(fechaRegistro.split("-")[2]) - Integer.parseInt(fechaNacimiento.split("-")[2]);
    int diffMonth = Integer.parseInt(fechaRegistro.split("-")[1]) - Integer.parseInt(fechaNacimiento.split("-")[1]);
    int diffDay = Integer.parseInt(fechaRegistro.split("-")[0]) - Integer.parseInt(fechaNacimiento.split("-")[0]);
    if (diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
        diffYear = diffYear - 1;
    }
    return diffYear;
}

    public long getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Queue<Cita> getCitas() {
        return citas;
    }

    public void setCitas(Queue<Cita> citas) {
        this.citas = citas;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public String[] getData(){
        return new String[]{String.valueOf(cedula),
                    nombre,
                    String.valueOf(edad),
                    correo,
                    fechaNacimiento,
                    fechaRegistro,
                    direccion,
                    departamento,
                    municipio
        };
    }

    @Override
    public String toString() {
        return  cedula + "," + nombre + "," + password + "," + tipo + "," + correo + "," + fechaNacimiento + "," + fechaRegistro;
    }
}
