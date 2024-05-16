package redhospitalaria.modelo;

import java.io.Serializable;
import java.util.*;

public class Paciente implements Serializable {

    protected long cedula;
    protected String nombre;
    protected String genero;
    protected String eps;
    protected String password;
    protected String correo;
    protected String fechaNacimiento;
    protected String fechaRegistro;
    protected String departamento;
    protected String municipio;
    protected String direccion;
    protected int edad;
    protected int citasAtendidas;
    protected int citasSolicitadas;
    protected Queue<Cita> citas = new LinkedList<>();
    protected Stack<String> notify = new Stack<>();

    public Paciente() {
    }

    public Paciente(long cedula, String nombre, String genero, String eps, String password, String correo, String fechaNacimiento, String fechaRegistro, String departamento, String municipio, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.genero = genero;
        this.eps = eps;
        this.password = password;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.departamento = departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        edad = calcularEdad();
    }
    
    public Paciente(Paciente u) {
        this.cedula = u.cedula;
        this.nombre = u.nombre;
        this.genero = u.genero;
        this.eps = u.eps;
        this.password = u.password;
        this.correo = u.correo;
        this.fechaNacimiento = u.fechaNacimiento;
        this.fechaRegistro = u.fechaRegistro;
        this.departamento = u.departamento;
        this.municipio = u.municipio;
        this.direccion = u.direccion;
        edad = calcularEdad();
    }

    public int calcularEdad() {
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

    public Stack<String> getNotify() {
        return notify;
    }

    public void setNotify(Stack<String> notify) {
        this.notify = notify;
    }
    
    public String[] getData(){
        return new String[]{String.valueOf(cedula),
                    "-",
                    nombre,
                    genero,
                    eps,
                    String.valueOf(edad),
                    correo,
                    fechaNacimiento,
                    fechaRegistro,
                    direccion,
                    departamento,
                    municipio
        };
    }
    

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }
    
    public int getCitasAtendidas() {
        return citasAtendidas;
    }

    public void setCitasAtendidas(int citasAtendidas) {
        this.citasAtendidas = citasAtendidas;
    }
    
    public void incrementCitasAtendidas(int num) {
        citasAtendidas += num;
    }

    public int getCitasSolicitadas() {
        return citasSolicitadas;
    }

    public void setCitasSolicitadas(int citasSolicitadas) {
        this.citasSolicitadas = citasSolicitadas;
    }
    
    public void incrementCitasSolicitadas(int num) {
        citasSolicitadas += num;
    }

    public boolean agregarCita(Cita date) {
        Queue<Cita> temp = new LinkedList<>();
        boolean val;
        while (!citas.isEmpty()
                && citas.peek().getUrgencia() <=  date.getUrgencia()) {
            temp.offer(citas.poll());
        }
        if(val = temp.offer(date)){
            citasSolicitadas++;
        }
        while (!citas.isEmpty()) {
            temp.offer(citas.poll());
        }
        citas = temp;
        return val;
    }
    
    @Override
    public String toString() {
        return  cedula + "," + nombre + "," + password + "," + correo + "," + fechaNacimiento + "," + fechaRegistro;
    }
}
