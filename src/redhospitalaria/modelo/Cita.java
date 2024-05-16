package redhospitalaria.modelo;

import java.io.Serializable;
import redhospitalaria.visual.Util;

public class Cita implements Serializable{
    
    private String fecha, sede, razon, area;
    private int urgencia;
    private Doctor doctor;
    private Paciente paciente;
    
    public Cita(Doctor doctor, Paciente paciente, String fecha, String sede, String area, String razon, int urgencia) {
        this.doctor = doctor;
        this.paciente = paciente;
        this.fecha = fecha;
        this.sede = sede;
        this.razon = razon;
        this.area = area;
        this.urgencia = urgencia;
    }

    public Cita() {
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
    
    public int getUrgencia() {
        return urgencia;
    }

    public void getUrgencia(int urgencia) {
        this.urgencia = urgencia;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    public String[] getData(Paciente p){
        if(p.getClass() == Doctor.class){
            return new String[]{paciente.getNombre(),
                String.valueOf(paciente.getCedula()),
                doctor.getMunicipio(),
                Util.urgencias[urgencia],
                area,
                fecha,
                razon
            };
        }else{
            return new String[]{doctor.getNombre(),
                String.valueOf(doctor.getCedula()),
                doctor.getEspecializacion(),
                doctor.getMunicipio(),
                Util.urgencias[urgencia],
                area,
                fecha,
                razon
            };
        }
    }
    
    public String[] getDataName(Paciente p){
        if(p.getClass() == Doctor.class){
            return new String[]{"Nombre",
                "Cedula",
                "Sede",
                "Urgencia",
                "Area",
                "Fecha",
                "Razon"
            };
        }else{
            return new String[]{"Nombre",
                "Cedula",
                "Especializacion",
                "Sede",
                "Urgencia",
                "Area",
                "Fecha",
                "Razon"
            };
        }
    }
}
