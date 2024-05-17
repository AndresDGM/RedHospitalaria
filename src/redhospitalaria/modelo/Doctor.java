/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import redhospitalaria.visual.DateChooserDJRaven.DateChooser;
import redhospitalaria.visual.Util;

/**
 *
 * @author david
 */
public class Doctor extends Paciente {

    private String especializacion;
    private String[] ultimasCitasfecha = new String[5];
    private int[] ultimasCitasCont = new int[5];

    public Doctor() {
        for (int i = 0; i < ultimasCitasfecha.length; i++) {
            ultimasCitasfecha[i] = DateChooser.getToDay();
        }
    }

    public Doctor(long cedula, String nombre, String genero, String eps, String password, String correo, String fechaNacimiento, String fechaRegistro, String departamento, String municipio, String direccion, String especializacion) {
        super(cedula, nombre, genero, eps, password, correo, fechaNacimiento, fechaRegistro, departamento, municipio, direccion);
        for (int i = 0; i < ultimasCitasfecha.length; i++) {
            ultimasCitasfecha[i] = DateChooser.getToDay();
        }
        this.especializacion = especializacion;
    }

    public Doctor(Paciente user, String especializacion) {
        super(user.cedula, user.nombre, user.genero, user.eps, user.password, user.correo, user.fechaNacimiento, user.fechaRegistro, user.departamento, user.municipio, user.direccion);
        for (int i = 0; i < ultimasCitasfecha.length; i++) {
            ultimasCitasfecha[i] = DateChooser.getToDay();
        }
        this.especializacion = especializacion;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }
    
    public String[] getUltimasCitasfecha() {
        return ultimasCitasfecha;
    }

    public void setUltimasCitasfecha(String[] ultimasCitasfecha) {
        this.ultimasCitasfecha = ultimasCitasfecha;
    }
    
    @Override
    public String[] getData(){
        String[] arr = super.getData();
        arr[1] = especializacion;
        return arr;
    }

    @Override
    public boolean agregarCita(Cita date) {
        int index = date.getUrgencia();
        Queue<Cita> temp = new LinkedList<>();
        boolean val;
        while (!citas.isEmpty()
                && Util.calcularPrioridad(citas.peek().getPaciente(), citas.peek()) <= Util.calcularPrioridad(date.getPaciente(), date)) {
            temp.offer(citas.poll());
        }
        date.setFecha(ultimasCitasfecha[index]);
        ultimasCitasCont[index]++;
        date.setSede(municipio);
        val = temp.offer(date);
        while (!citas.isEmpty()) {
            temp.offer(citas.poll());
        }
        citas = temp;
        if (ultimasCitasCont[index] >= 6) {
            ultimasCitasfecha[index] = Util.getNextDate(ultimasCitasfecha[date.getUrgencia()]);
            ultimasCitasCont[index] = 0;
        }
        return val;
    }
}
