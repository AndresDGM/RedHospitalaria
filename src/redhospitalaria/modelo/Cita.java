package redhospitalaria.modelo;

public class Cita {
    private String doctor, fecha, hora, sede, razon;
    private int urgencia;

    public Cita(String doctor, String fecha, String hora, String sede, String razon, int urgencia) {
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.sede = sede;
        this.razon = razon;
        this.urgencia = urgencia;
    }

    public Cita() {
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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
}
