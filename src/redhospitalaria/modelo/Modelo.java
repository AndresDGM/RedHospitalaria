/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Predicate;
import redhospitalaria.visual.DateChooserDJRaven.DateChooser;
import redhospitalaria.visual.Util;

public class Modelo {

    private final int port = 8001;
    private ServerSocket serverSocket;
    private Socket socket;
    private HashMap<Long, Usuario> datos = new HashMap<>();
    private String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private String idRegex = "^.\\d+$";
    private String passwordRegex = "^.{8,}$";

    public Modelo() throws IOException {
        serverSocket = new ServerSocket(port);
        //datos de prueba
        for (int i = 0; i < 50; i++) {
            int ran = (int) (Math.random() * (Util.departamentos.length - 2) + 1);
            int ran2 = (int) (Math.random() * (Util.municipios[ran - 1].length - 2) + 1);
            Usuario u = new Usuario(
                    Util.idsPruebaDocs[i],
                    Util.nombresPruebaDoc[i],
                    "12345678",
                    "Doctor",
                    Util.CorreosPruebaDocs[i],
                    Util.fechasdeNacimientoDocs[i],
                    DateChooser.getToDay(),
                    Util.departamentos[ran],
                    Util.municipios[ran - 1][ran2],
                    Util.direccionesPrueba[i]
            );
            datos.put(u.getCedula(), u);
        }
        datos.get(986742189L).setTipo("Administrador");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Modelo m = new Modelo();
        while (true) {
            Socket s;
            System.out.println("Esperando Peticion");
            s = m.getServerSocket().accept();
            System.out.println("Peticion Aceptada");
            ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream salida = new ObjectOutputStream(s.getOutputStream());
            String comando = (String) entrada.readObject();
            String peticion = comando.split(":")[0];
            switch (peticion) {
                case "sesion" ->
                    m.sesion(Long.valueOf(comando.split(":")[1]), comando.split(":")[2], salida);
                case "exits" ->
                    m.exits(Long.valueOf(comando.split(":")[1]), salida);
                case "create" -> {
                    Usuario u = (Usuario) entrada.readObject();
                    if (u != null) {
                        m.getDatos().put(u.getCedula(), u);
                    }
                    salida.writeObject(u != null);
                }
                case "val" ->
                    m.validation(comando.split(":")[1], comando.split(":")[2], salida);
                case "get" ->
                    m.get(Long.parseLong(comando.split(":")[1]), salida);
                case "delete" ->
                    m.delete(Long.parseLong(comando.split(":")[1]), salida);
                case "list" -> {
                    Usuario u = (Usuario) entrada.readObject();
                    m.list(comando.split(":")[1], u, salida);
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

    public HashMap<Long, Usuario> getDatos() {
        return datos;
    }

    public void setDatos(HashMap<Long, Usuario> datos) {
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
            out.writeObject(datos.remove(key));
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

    public void list(String data, Usuario u, ObjectOutputStream out) throws IOException {
        ArrayList<String[]> lista = new ArrayList<>();
        Predicate<Long> predicate = key -> false;
        switch (data) {
            case "doctores con mas de 10 años de experiencia" ->
                predicate = key -> datos.get(key).getEdad() >= 33;
            case "doctores con menos de 10 años de experiencia" ->
                predicate = key -> datos.get(key).getEdad() < 33;
            case "doctores en el departamento" ->
                predicate = key -> datos.get(key).getDepartamento().equals(u.getDepartamento());
            case "doctores en el municipio" ->
                predicate = key -> datos.get(key).getMunicipio().equals(u.getMunicipio());
            case "mayores de 30 años" ->
                predicate = key -> datos.get(key).getEdad() > 30;
        }
        Set<Long> keys = datos.keySet();
        for (Long key : keys) {
            if (predicate.test(key)) {
                lista.add(datos.get(key).getData());
            }
        }
        out.writeObject(lista);
    }
}
