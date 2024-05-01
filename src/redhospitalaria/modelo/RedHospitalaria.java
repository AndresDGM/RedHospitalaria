package redhospitalaria.modelo;

import redhospitalaria.controlador.PrincipalControl;

import java.io.IOException;

public class RedHospitalaria {

    public static void main(String[] args) throws IOException {
        PrincipalControl p = new PrincipalControl();
        p.mostrarGui();
    }
}
