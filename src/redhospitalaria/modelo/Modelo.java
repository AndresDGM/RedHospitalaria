/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.modelo;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;
import redhospitalaria.visual.DateChooserDJRaven.DateChooser;
import redhospitalaria.visual.Util;

public class Modelo {

    private final int port = 8001;
    private ServerSocket serverSocket;
    private Socket socket;
    private HashMap<Long, Paciente> datos = new HashMap<>();
    private final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final String idRegex = "^.\\d+$";
    private final String passwordRegex = "^.{8,}$";

    public Modelo() throws IOException {
        serverSocket = new ServerSocket(port);
        //datos de prueba
        for (int i = 0; i < 50; i++) {
            int ran = (int) (Math.random() * (Util.departamentos.length - 2) + 1);
            int ran2 = (int) (Math.random() * (Util.municipios[ran - 1].length - 2) + 1);
            Paciente u = new Doctor(
                    Util.idsPruebaDocs[i],
                    Util.nombresPruebaDoc[i],
                    "Xd",
                    "EPS",
                    "12345678",
                    Util.CorreosPruebaDocs[i],
                    Util.fechasdeNacimientoDocs[i],
                    DateChooser.getToDay(),
                    Util.departamentos[ran],
                    Util.municipios[ran - 1][ran2],
                    Util.direccionesPrueba[i],
                    Util.especializaciones[(int) (Math.random() * Util.especializaciones.length - 1)]
            );
            datos.put(u.getCedula(), u);
        }
        datos.replace(986742189L, new Administrador(datos.get(986742189L)));
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Modelo m = new Modelo();
        while (true) {
            UserSocket s;
            System.out.println("Esperando Peticion");
            s = new UserSocket(m.getServerSocket().accept());
            System.out.println("Peticion Aceptada");
            String comando = (String) s.getIn().readObject();
            System.out.println(comando);
            String peticion = comando.split(":")[0];
            switch (peticion) {
                case "sesion" ->
                    m.sesion(Long.valueOf(comando.split(":")[1]), comando.split(":")[2], s.getOut());
                case "exits" ->
                    m.exits(Long.valueOf(comando.split(":")[1]), s.getOut());
                case "create" -> {
                    Paciente u = (Paciente) s.getIn().readObject();
                    if (u != null) {
                        m.getDatos().put(u.getCedula(), u);
                    }
                    s.getOut().writeObject(u != null);
                }
                case "val" ->
                    m.validation(comando.split(":")[1], comando.split(":")[2], s.getOut());
                case "get" ->
                    m.get(Long.valueOf(comando.split(":")[1]), s.getOut());
                case "delete" ->
                    m.delete(Long.valueOf(comando.split(":")[1]), s.getOut());
                case "list" -> {
                    Predicado<Paciente> p = (Predicado<Paciente>) s.getIn().readObject();
                    m.list(p, s.getOut());
                }
                case "cont"->{
                    Predicado<Paciente> p = (Predicado<Paciente>) s.getIn().readObject();
                    s.getOut().writeObject(m.cont(p));
                }
                case "cant" ->{
                    if(comando.split(":")[1].equals("all")){
                        s.getOut().writeObject(m.cant());
                    }else{
                        s.getOut().writeObject(m.getCantDoctors());
                    }
                }
                case "appointment" -> {
                    if (comando.split(":")[1].equals("new")) {
                        m.newAppoitment(Long.valueOf(comando.split(":")[2]),
                                Integer.parseInt(comando.split(":")[3]),
                                comando.split(":")[4],
                                comando.split(":")[5],
                                s.getOut());
                    } else {
                        m.removeAppoitment(Long.valueOf(comando.split(":")[3]), Long.valueOf(comando.split(":")[2]), s.getOut());
                    }
                }
                case "modify" -> {
                    Paciente u = (Paciente) s.getIn().readObject();
                    m.modify(u, s.getOut());
                }
                case "notify" -> {
                    m.notify(Long.valueOf(comando.split(":")[1]), s.getOut());
                }
            }
            s.close();
            System.out.println("Peticion finalizada");
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public HashMap<Long, Paciente> getDatos() {
        return datos;
    }

    public void setDatos(HashMap<Long, Paciente> datos) {
        this.datos = datos;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void sesion(Long key, String password, ObjectOutputStream out) throws IOException {
        if (!datos.containsKey(key)) {
            out.writeObject("Not found");
        } else if (!datos.get(key).getPassword().equals(password)) {
            out.writeObject("User and Password dont match");
        } else {
            out.writeObject(datos.get(key));
        }
    }

    public void exits(Long key, ObjectOutputStream out) throws IOException {
        out.writeObject(datos.containsKey(key));
    }

    public void get(Long key, ObjectOutputStream out) throws IOException {
        if (datos.containsKey(key)) {
            out.writeObject(datos.get(key));
        } else {
            out.writeObject(false);
        }
    }

    public void delete(Long key, ObjectOutputStream out) throws IOException {
        if (datos.containsKey(key)) {
            Paciente p = datos.remove(key);
            Set<Long> keys = datos.keySet();
            for (Long k : keys) {
                Queue<Cita> citas = datos.get(k).getCitas();
                Queue<Cita> temp = new LinkedList<>();
                boolean val = false;
                while (!citas.isEmpty()) {
                    Cita c = citas.poll();
                    if (!c.getPaciente().equals(p) &&
                            p.getClass() == Doctor.class &&
                            !c.getDoctor().equals((Doctor) p) ) {
                        temp.offer(c);
                    } else {
                        val = true;
                    }
                }
                if (val) {
                    datos.get(k).getNotify().push("Su/s) cita/s con" + p.getNombre() + " " + p.getCedula() + " han sido removidas ya que este usuario ya no pertenece al sistema");
                }
                datos.get(k).setCitas(temp);
            }
            out.writeObject(p);
        } else {
            out.writeObject(false);
        }
    }

    public void validation(String type, String data, ObjectOutputStream out) throws IOException {
        switch (type) {
            case "email" ->
                out.writeObject(data.matches(emailRegex));
            case "id" ->
                out.writeObject(data.matches(idRegex));
            case "password" ->
                out.writeObject(data.matches(passwordRegex));
        }
    }

    public void list(Predicado<Paciente> p, ObjectOutputStream out) throws IOException {
        ArrayList<String[]> lista = new ArrayList<>();
        Set<Long> keys = datos.keySet();
        for (Long key : keys) {
            if (p.test(datos.get(key))) {
                lista.add(datos.get(key).getData());
            }
        }
        out.writeObject(lista);
    }
    
    public int cont(Predicado<Paciente> p) throws IOException {
        int cont = 0;
        Set<Long> keys = datos.keySet();
        for (Long key : keys) {
            if (p.test(datos.get(key))) {
                cont++;
            }
        }
        return cont;
    }
    
    public int getCantDoctors(){
        int cont = 0;
        Set<Long> keys = datos.keySet();
        for (Long key : keys) {
            if (datos.get(key).getClass() == Doctor.class) {
                cont++;
            }
        }
        return cont;
    }
    
    public int cant(){
        return datos.size();
    }

    public void newAppoitment(Long idUser, int urgencia, String area, String razon, ObjectOutputStream out) throws IOException {
        ArrayList<Long> keys = new ArrayList<>(datos.keySet());
        long doctorKey = -1;
        long menor = Long.MAX_VALUE;
        String esp = Util.especializaciones[Arrays.asList(Util.areas).indexOf(area)];
        for (int i = 0; i < keys.size(); i++) {
            Paciente p = datos.get(keys.get(i));
            if (p.getClass() == Doctor.class) {
                long diff = Util.dayBeetwen(DateChooser.getToDay(), ((Doctor) datos.get(keys.get(i))).getUltimasCitasfecha()[urgencia]);
                if (p.getMunicipio().equals(datos.get(idUser).getMunicipio())
                        && ((Doctor) p).getEspecializacion().equals(esp)) {
                    if (diff < menor) {
                        menor = diff;
                        doctorKey = keys.get(i);
                    }
                } else if (((Doctor) p).getEspecializacion().equals(esp)
                        && p.getDepartamento().equals(datos.get(idUser).getDepartamento())) {
                    boolean val = doctorKey == -1 || !datos.get(doctorKey).getMunicipio().equals(datos.get(idUser).getMunicipio());
                    if (diff < menor && val) {
                        menor = diff;
                        doctorKey = keys.get(i);
                    }
                } else if (((Doctor) p).getEspecializacion().equals(esp)) {
                    boolean val = doctorKey == -1 || !datos.get(doctorKey).getDepartamento().equals(datos.get(idUser).getDepartamento());
                    if (diff < menor && val) {
                        menor = diff;
                        doctorKey = keys.get(i);
                    }
                }
            }
        }
        if (doctorKey != -1) {
            Cita c = new Cita((Doctor) datos.get(doctorKey), datos.get(datos.get(idUser).getCedula()), "", "", area, razon, urgencia);
            ((Doctor) datos.get(doctorKey)).agregarCita(c);
            datos.get(doctorKey).getNotify().push("Se le ha agendado una cita con " + datos.get(datos.get(idUser).getCedula()).getNombre()
                    + " " + datos.get(datos.get(idUser).getCedula()).getCedula() + "\nRazon: " + c.getRazon());
            out.writeObject(datos.get(datos.get(idUser).getCedula()).agregarCita(c));
        } else {
            out.writeObject(false);
        }
    }

    public void removeAppoitment(Long idUser, Long idDoctor, ObjectOutputStream out) throws IOException {
        if (Objects.equals(idUser, idDoctor)) {
            if (!datos.get(idDoctor).getCitas().isEmpty()) {
                Cita cita = datos.get(idDoctor).citas.poll();
                datos.get(idUser).getCitas().poll();
                datos.get(idUser).incrementCitasAtendidas(1);
                datos.get(idDoctor).incrementCitasAtendidas(1);
                datos.get(idUser).getNotify().push("Su cita con "
                        + ((Doctor) datos.get(idDoctor)).getNombre()
                        + " " + ((Doctor) datos.get(idDoctor)).getEspecializacion()
                        + " fue atendida correctamente\nRazon: " + cita.getRazon());

                out.writeObject("La cita se atendio correctamente");
            } else {
                out.writeObject("No hay citas por el momento");
            }
        } else {
            if (datos.containsKey(idDoctor)) {
                if (!datos.get(idDoctor).getCitas().isEmpty()) {
                    if (datos.get(idDoctor).getCitas().peek().equals(datos.get(idUser).citas.peek())) {
                        Cita cita = datos.get(idUser).citas.poll();
                        datos.get(idDoctor).getCitas().poll();
                        datos.get(idUser).incrementCitasAtendidas(1);
                        datos.get(idDoctor).incrementCitasAtendidas(1);
                        datos.get(idDoctor).getNotify().push("Su cita con " + datos.get(idUser).getNombre()
                                + " " + datos.get(idUser).getCedula() + " fue atendida correctamente\nRazon: " + cita.getRazon());
                        out.writeObject("La cita se atendio correctamente");
                    } else {
                        out.writeObject("Todavia no es su turno");
                    }
                } else {
                    out.writeObject("No hay citas por el momento");
                }
            } else {
                out.writeObject("No hay citas con este doctor/a, ya que ya no pertenece al sistema");
            }
        }
    }

    public void modify(Paciente u, ObjectOutputStream out) throws IOException {
        if (u.getClass() == Paciente.class) {
            out.writeObject(datos.replace(u.getCedula(), datos.get(u.getCedula()), u));
        } else if (u.getClass() == Doctor.class) {
            out.writeObject(datos.replace(u.getCedula(), datos.get(u.getCedula()), (Doctor) u));
        } else {
            out.writeObject(datos.replace(u.getCedula(), datos.get(u.getCedula()), (Administrador) u));
        }
    }

    public void notify(long id, ObjectOutputStream out) throws IOException {
        if (!datos.get(id).getNotify().isEmpty()) {
            out.writeObject(datos.get(id).getNotify().pop());
        } else {
            out.writeObject("");
        }
    }
}
