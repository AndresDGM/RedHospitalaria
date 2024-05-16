/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.visual;

import java.awt.Color;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import redhospitalaria.modelo.Cita;
import redhospitalaria.modelo.Paciente;

public class Util {

    public static Color color = new Color(55, 55, 55);

    public static ImageIcon scaleImage(String source, int h) {
        ImageIcon imagen = new ImageIcon(source);
        float scale = (float) h / imagen.getIconHeight();
        return new ImageIcon(
                imagen.getImage().getScaledInstance((int) (imagen.getIconWidth() * scale),
                        (int) (imagen.getIconHeight() * scale), Image.SCALE_DEFAULT));
    }
    
    public static String[] epsItems = {"Emssanar", "Sanitas", "SOS", "salud total", "Sura"};
    
    public static String[] generos = {"Mujer", "Hombre", "Otro"};

    public static String[] nombresPruebaDoc = {
        "Juan Pérez",
        "María García",
        "Carlos López",
        "Ana Martínez",
        "Javier Rodríguez",
        "Laura Sánchez",
        "Pedro Gómez",
        "Andrea Fernández",
        "Luis González",
        "Marta Díaz",
        "David Ruiz",
        "Paula Hernández",
        "Sergio Martín",
        "Elena Jiménez",
        "Miguel Pérez",
        "Carmen Álvarez",
        "Alejandro Torres",
        "Isabel Ruiz",
        "Pablo Gutiérrez",
        "Natalia Moreno",
        "Fernando Vázquez",
        "Cristina Castro",
        "Daniel Santos",
        "Sonia Ortiz",
        "Jorge Velázquez",
        "Patricia Ramírez",
        "Óscar Medina",
        "Raquel Blanco",
        "Guillermo Reyes",
        "Adriana Molina",
        "Rafael Ramos",
        "Lorena López",
        "Francisco Suárez",
        "Ana Belén Romero",
        "Javier Medina",
        "Claudia Herrera",
        "Alberto Pérez",
        "Susana Fernández",
        "Lucas Martínez",
        "Verónica Sánchez",
        "Ignacio Rodríguez",
        "Beatriz González",
        "Antonio Moreno",
        "Eva Jiménez",
        "Diego Gómez",
        "Marina Torres",
        "Gabriel Ramos",
        "Rosa García",
        "Manuel Ruiz",
        "Elena Díaz"
    };

    public static String[] CorreosPruebaDocs = {
        "juan.perez@example.com",
        "maria.garcia@example.com",
        "carlos.lopez@example.com",
        "ana.martinez@example.com",
        "javier.rodriguez@example.com",
        "laura.sanchez@example.com",
        "pedro.gomez@example.com",
        "andrea.fernandez@example.com",
        "luis.gonzalez@example.com",
        "marta.diaz@example.com",
        "david.ruiz@example.com",
        "paula.hernandez@example.com",
        "sergio.martin@example.com",
        "elena.jimenez@example.com",
        "miguel.perez@example.com",
        "carmen.alvarez@example.com",
        "alejandro.torres@example.com",
        "isabel.ruiz@example.com",
        "pablo.gutierrez@example.com",
        "natalia.moreno@example.com",
        "fernando.vazquez@example.com",
        "cristina.castro@example.com",
        "daniel.santos@example.com",
        "sonia.ortiz@example.com",
        "jorge.velazquez@example.com",
        "patricia.ramirez@example.com",
        "oscar.medina@example.com",
        "raquel.blanco@example.com",
        "guillermo.reyes@example.com",
        "adriana.molina@example.com",
        "rafael.ramos@example.com",
        "lorena.lopez@example.com",
        "francisco.suarez@example.com",
        "ana.belen.romero@example.com",
        "javier.medina@example.com",
        "claudia.herrera@example.com",
        "alberto.perez@example.com",
        "susana.fernandez@example.com",
        "lucas.martinez@example.com",
        "veronica.sanchez@example.com",
        "ignacio.rodriguez@example.com",
        "beatriz.gonzalez@example.com",
        "antonio.moreno@example.com",
        "eva.jimenez@example.com",
        "diego.gomez@example.com",
        "marina.torres@example.com",
        "gabriel.ramos@example.com",
        "rosa.garcia@example.com",
        "manuel.ruiz@example.com",
        "elena.diaz@example.com"
    };

    public static int[] idsPruebaDocs = {
        18376549,
        298467515,
        478921654,
        687429831,
        124785632,
        986742189,
        367825149,
        874125368,
        235784691,
        927846351,
        548731926,
        136598247,
        874526193,
        569837421,
        985473216,
        485729631,
        298643175,
        785469312,
        234769851,
        756892143,
        326549781,
        489756312,
        985623471,
        298765431,
        764895231,
        435786912,
        387654129,
        578643912,
        456983127,
        598742361,
        743856129,
        586923147,
        457986321,
        329875416,
        982367541,
        764259831,
        879645321,
        569874312,
        287654913,
        549836721,
        875349621,
        486735192,
        783452961,
        197843256,
        348725196,
        579836421,
        746829315,
        259874631,
        847362915,
        1234673423
    };

    public static String[] fechasdeNacimientoDocs = {
        "15-06-2002",
        "23-09-1995",
        "07-03-1989",
        "12-11-2001",
        "30-04-1996",
        "18-08-1987",
        "25-12-1985",
        "09-05-1991",
        "03-10-1989",
        "14-07-1994",
        "26-01-1997",
        "20-09-1986",
        "11-02-1995",
        "05-11-1990",
        "29-06-1996",
        "22-04-1987",
        "08-12-1996",
        "17-03-1998",
        "01-07-1992",
        "24-10-1998",
        "13-05-1997",
        "19-08-1982",
        "02-09-1992",
        "06-06-1977",
        "28-11-1995",
        "21-01-1988",
        "04-04-1977",
        "10-08-1998",
        "27-03-1994",
        "16-12-1992",
        "05-10-1999",
        "18-07-1997",
        "23-09-1993",
        "12-06-1990",
        "30-01-1996",
        "08-05-1975",
        "19-04-1979",
        "26-02-1993",
        "02-11-1994",
        "14-08-1970",
        "27-07-1999",
        "09-09-1985",
        "21-03-1997",
        "01-12-1993",
        "16-10-1975",
        "04-07-1984",
        "22-02-1997",
        "10-06-1994",
        "25-05-1997",
        "07-01-1983"
    };

    public static String[] departamentos = {
        "Seleccionar",
        "Amazonas",
        "Antioquia",
        "Arauca",
        "Atlántico",
        "Bolívar",
        "Boyacá",
        "Caldas",
        "Caquetá",
        "Casanare",
        "Cauca",
        "Cesar",
        "Chocó",
        "Córdoba",
        "Cundinamarca",
        "Guainía",
        "Guaviare",
        "Huila",
        "La Guajira",
        "Magdalena",
        "Meta",
        "Nariño",
        "Norte de Santander",
        "Putumayo",
        "Quindío",
        "Risaralda",
        "San Andrés y Providencia",
        "Santander",
        "Sucre",
        "Tolima",
        "Valle del Cauca",
        "Vaupés",
        "Vichada"
    };

    public static String[][] municipios = {
        {"Seleccionar", "El Encanto", "La Chorrera", "La Pedrera", "La Victoria", "Leticia", "Miriti - Paraná", "Puerto Alegria", "Puerto Arica", "Puerto Nariño", "Puerto Santander", "Tarapacá"},
        {"Seleccionar", "Cáceres", "Caucasia", "El Bagre", "Nechí", "Tarazá", "Zaragoza", "Caracolí", "Maceo", "Puerto Berrio", "Puerto Nare", "Puerto Triunfo", "Yondó", "Amalfi", "Anorí", "Cisneros", "Remedios", "San Roque", "Santo Domingo", "Segovia", "Vegachí", "Yalí", "Yolombó", "Angostura", "Belmira", "Briceño", "Campamento", "Carolina", "Don Matias", "Entrerrios", "Gómez Plata", "Guadalupe", "Ituango", "San Andrés", "San José De La Montaña", "San Pedro", "Santa Rosa De Osos", "Toledo", "Valdivia", "Yarumal", "Abriaquí", "Anza", "Armenia", "Buriticá", "Cañasgordas", "Dabeiba", "Ebéjico", "Frontino", "Giraldo", "Heliconia", "Liborina", "Olaya", "Peque", "Sabanalarga", "San Jerónimo", "Santafé De Antioquia", "Sopetran", "Uramita", "Abejorral", "Alejandría", "Argelia", "Carmen De Viboral", "Cocorná", "Concepción", "Granada", "Guarne", "Guatape", "La Ceja", "La Unión", "Marinilla", "Nariño", "Peñol", "Retiro", "Rionegro", "San Carlos", "San Francisco", "San Luis", "San Rafael", "San Vicente", "Santuario", "Sonson", "Amaga", "Andes", "Angelopolis", "Betania", "Betulia", "Caicedo", "Caramanta", "Ciudad Bolívar", "Concordia", "Fredonia", "Hispania", "Jardín", "Jericó", "La Pintada", "Montebello", "Pueblorrico", "Salgar", "Santa Barbara", "Támesis", "Tarso", "Titiribí", "Urrao", "Valparaiso", "Venecia", "Apartadó", "Arboletes", "Carepa", "Chigorodó", "Murindó", "Mutata", "Necoclí", "San Juan De Uraba", "San Pedro De Uraba", "Turbo", "Vigía Del Fuerte", "Barbosa", "Bello", "Caldas", "Copacabana", "Envigado", "Girardota", "Itagui", "La Estrella", "Medellín", "Sabaneta"},
        {"Seleccionar", "Arauca", "Arauquita", "Cravo Norte", "Fortul", "Puerto Rondón", "Saravena", "Tame"},
        {"Seleccionar", "Providencia Y Santa Catalina", "San Andres"},
        {"Seleccionar", "Barranquilla", "Galapa", "Malambo", "Puerto Colombia", "Soledad", "Campo De La Cruz", "Candelaria", "Luruaco", "Manati", "Repelon", "Santa Lucia", "Suan", "Baranoa", "Palmar De Varela", "Polonuevo", "Ponedera", "Sabanagrande", "Sabanalarga", "Santo Tomas", "Juan De Acosta", "Piojó", "Tubara", "Usiacuri"},
        {"Seleccionar", "Cicuco", "Hatillo De Loba", "Margarita", "Mompós", "San Fernando", "Talaigua Nuevo", "Arjona", "Arroyohondo", "Calamar", "Cartagena", "Clemencia", "Mahates", "San Cristobal", "San Estanislao", "Santa Catalina", "Santa Rosa De Lima", "Soplaviento", "Turbaco", "Turbana", "Villanueva", "Altos Del Rosario", "Barranco De Loba", "El Peñon", "Regidor", "Río Viejo", "San Martin De Loba", "Arenal", "Cantagallo", "Morales", "San Pablo", "Santa Rosa Del Sur", "Simití", "Achí", "Magangué", "Montecristo", "Pinillos", "San Jacinto Del Cauca", "Tiquisio", "Carmen De Bolívar", "Córdoba", "El Guamo", "María La Baja", "San Jacinto", "San Juan Nepomuceno", "Zambrano"},
        {"Seleccionar", "Chíquiza", "Chivatá", "Cómbita", "Cucaita", "Motavita", "Oicatá", "Samacá", "Siachoque", "Sora", "Soracá", "Sotaquirá", "Toca", "Tunja", "Tuta", "Ventaquemada", "Chiscas", "Cubará", "El Cocuy", "El Espino", "Guacamayas", "Güicán", "Panqueba", "Labranzagrande", "Pajarito", "Paya", "Pisba", "Berbeo", "Campohermoso", "Miraflores", "Páez", "San Eduardo", "Zetaquira", "Boyacá", "Ciénega", "Jenesano", "Nuevo Colón", "Ramiriquí", "Rondón", "Tibaná", "Turmequé", "Umbita", "Viracachá", "Chinavita", "Garagoa", "Macanal", "Pachavita", "San Luis De Gaceno", "Santa María", "Boavita", "Covarachía", "La Uvita", "San Mateo", "Sativanorte", "Sativasur", "Soatá", "Susacón", "Tipacoque", "Briceño", "Buenavista", "Caldas", "Chiquinquirá", "Coper", "La Victoria", "Maripí", "Muzo", "Otanche", "Pauna", "Puerto Boyaca", "Quípama", "Saboyá", "San Miguel De Sema", "San Pablo Borbur", "Tununguá", "Almeida", "Chivor", "Guateque", "Guayatá", "La Capilla", "Somondoco", "Sutatenza", "Tenza", "Arcabuco", "Chitaraque", "Gachantivá", "Moniquirá", "Ráquira", "Sáchica", "San José De Pare", "Santa Sofía", "Santana", "Sutamarchán", "Tinjacá", "Togüí", "Villa De Leyva", "Aquitania", "Cuítiva", "Firavitoba", "Gameza", "Iza", "Mongua", "Monguí", "Nobsa", "Pesca", "Sogamoso", "Tibasosa", "Tópaga", "Tota", "Belén", "Busbanzá", "Cerinza", "Corrales", "Duitama", "Floresta", "Paipa", "San Rosa Viterbo", "Tutazá", "Betéitiva", "Chita", "Jericó", "Paz De Río", "Socha", "Socotá", "Tasco"},
        {"Seleccionar", "Filadelfia", "La Merced", "Marmato", "Riosucio", "Supía", "Manzanares", "Marquetalia", "Marulanda", "Pensilvania", "Anserma", "Belalcázar", "Risaralda", "San José", "Viterbo", "Chinchina", "Manizales", "Neira", "Palestina", "Villamaria", "Aguadas", "Aranzazu", "Pácora", "Salamina", "La Dorada", "Norcasia", "Samaná", "Victoria"},
        {"Seleccionar", "Albania", "Belén De Los Andaquies", "Cartagena Del Chairá", "Currillo", "El Doncello", "El Paujil", "Florencia", "La Montañita", "Milan", "Morelia", "Puerto Rico", "San Jose Del Fragua", "San Vicente Del Caguán", "Solano", "Solita", "Valparaiso"},
        {"Seleccionar", "Aguazul", "Chameza", "Hato Corozal", "La Salina", "Maní", "Monterrey", "Nunchía", "Orocué", "Paz De Ariporo", "Pore", "Recetor", "Sabanalarga", "Sácama", "San Luis De Palenque", "Támara", "Tauramena", "Trinidad", "Villanueva", "Yopal"},
        {"Seleccionar", "Cajibío", "El Tambo", "La Sierra", "Morales", "Piendamo", "Popayán", "Rosas", "Sotara", "Timbio", "Buenos Aires", "Caloto", "Corinto", "Miranda", "Padilla", "Puerto Tejada", "Santander De Quilichao", "Suarez", "Villa Rica", "Guapi", "Lopez", "Timbiqui", "Caldono", "Inzá", "Jambalo", "Paez", "Purace", "Silvia", "Toribio", "Totoro", "Almaguer", "Argelia", "Balboa", "Bolívar", "Florencia", "La Vega", "Mercaderes", "Patia", "Piamonte", "San Sebastian", "Santa Rosa", "Sucre"},
        {"Seleccionar", "Becerril", "Chimichagua", "Chiriguana", "Curumaní", "La Jagua De Ibirico", "Pailitas", "Tamalameque", "Astrea", "Bosconia", "El Copey", "El Paso", "Agustín Codazzi", "La Paz", "Manaure", "Pueblo Bello", "San Diego", "Valledupar", "Aguachica", "Gamarra", "González", "La Gloria", "Pelaya", "Río De Oro", "San Alberto", "San Martín"},
        {"Seleccionar", "Atrato", "Bagadó", "Bojaya", "El Carmen De Atrato", "Lloró", "Medio Atrato", "Quibdó", "Rio Quito", "Acandí", "Belén De Bajira", "Carmén Del Darién", "Riosucio", "Unguía", "Bahía Solano", "Juradó", "Nuquí", "Alto Baudó", "Bajo Baudó", "El Litoral Del San Juan", "Medio Baudó", "Canton De San Pablo", "Certegui", "Condoto", "Itsmina", "Medio San Juan", "Nóvita", "Río Frío", "San José Del Palmar", "Sipí", "Tadó", "Union Panamericana"},
        {"Seleccionar", "Tierralta", "Valencia", "Chimá", "Cotorra", "Lorica", "Momil", "Purísima", "Montería", "Canalete", "Los Córdobas", "Moñitos", "Puerto Escondido", "San Antero", "San Bernardo Del Viento", "Chinú", "Sahagún", "San Andrés Sotavento", "Ayapel", "Buenavista", "La Apartada", "Montelíbano", "Planeta Rica", "Pueblo Nuevo", "Puerto Libertador", "Cereté", "Ciénaga De Oro", "San Carlos", "San Pelayo"},
        {"Seleccionar", "Bogota D.C.", "Chocontá", "Macheta", "Manta", "Sesquilé", "Suesca", "Tibirita", "Villapinzón", "Agua De Dios", "Girardot", "Guataquí", "Jerusalén", "Nariño", "Nilo", "Ricaurte", "Tocaima", "Caparrapí", "Guaduas", "Puerto Salgar", "Albán", "La Peña", "La Vega", "Nimaima", "Nocaima", "Quebradanegra", "San Francisco", "Sasaima", "Supatá", "Útica", "Vergara", "Villeta", "Gachala", "Gacheta", "Gama", "Guasca", "Guatavita", "Junín", "La Calera", "Ubalá", "Beltrán", "Bituima", "Chaguaní", "Guayabal De Siquima", "Puli", "San Juan De Río Seco", "Vianí", "Medina", "Paratebueno", "Caqueza", "Chipaque", "Choachí", "Fomeque", "Fosca", "Guayabetal", "Gutiérrez", "Quetame", "Ubaque", "Une", "El Peñón", "La Palma", "Pacho", "Paime", "San Cayetano", "Topaipi", "Villagomez", "Yacopí", "Cajicá", "Chía", "Cogua", "Gachancipá", "Nemocon", "Sopó", "Tabio", "Tocancipá", "Zipaquirá", "Bojacá", "Cota", "El Rosal", "Facatativá", "Funza", "Madrid", "Mosquera", "Subachoque", "Tenjo", "Zipacon", "Sibaté", "Soacha", "Arbeláez", "Cabrera", "Fusagasugá", "Granada", "Pandi", "Pasca", "San Bernardo", "Silvania", "Tibacuy", "Venecia", "Anapoima", "Anolaima", "Apulo", "Cachipay", "El Colegio", "La Mesa", "Quipile", "San Antonio De Tequendama", "Tena", "Viotá", "Carmen De Carupa", "Cucunubá", "Fúquene", "Guachetá", "Lenguazaque", "Simijaca", "Susa", "Sutatausa", "Tausa", "Ubate"},
        {"Seleccionar", "Barranco Mina", "Cacahual", "Inírida", "La Guadalupe", "Mapiripan", "Morichal", "Pana Pana", "Puerto Colombia", "San Felipe"},
        {"Seleccionar", "Calamar", "El Retorno", "Miraflores", "San José Del Guaviare"},
        {"Seleccionar", "Agrado", "Altamira", "Garzón", "Gigante", "Guadalupe", "Pital", "Suaza", "Tarqui", "Aipe", "Algeciras", "Baraya", "Campoalegre", "Colombia", "Hobo", "Iquira", "Neiva", "Palermo", "Rivera", "Santa María", "Tello", "Teruel", "Villavieja", "Yaguará", "La Argentina", "La Plata", "Nátaga", "Paicol", "Tesalia", "Acevedo", "Elías", "Isnos", "Oporapa", "Palestina", "Pitalito", "Saladoblanco", "San Agustín", "Timaná"},
        {"Seleccionar", "Albania", "Dibulla", "Maicao", "Manaure", "Riohacha", "Uribia", "Barrancas", "Distraccion", "El Molino", "Fonseca", "Hatonuevo", "La Jagua Del Pilar", "San Juan Del Cesar", "Urumita", "Villanueva"},
        {"Seleccionar", "Ariguaní", "Chibolo", "Nueva Granada", "Plato", "Sabanas De San Angel", "Tenerife", "Algarrobo", "Aracataca", "Ciénaga", "El Reten", "Fundacion", "Pueblo Viejo", "Zona Bananera", "Cerro San Antonio", "Concordia", "El Piñon", "Pedraza", "Pivijay", "Remolino", "Salamina", "Sitionuevo", "Zapayan", "Santa Marta", "El Banco", "Guamal", "Pijiño Del Carmen", "San Sebastian De Buenavista", "San Zenon", "Santa Ana", "Santa Barbara De Pinto"},
        {"Seleccionar", "El Castillo", "El Dorado", "Fuente De Oro", "Granada", "La Macarena", "La Uribe", "Lejanías", "Mapiripan", "Mesetas", "Puerto Concordia", "Puerto Lleras", "Puerto Rico", "San Juan De Arama", "Vista Hermosa", "Villavicencio", "Acacias", "Barranca De Upia", "Castilla La Nueva", "Cumaral", "El Calvario", "Guamal", "Restrepo", "San Carlos Guaroa", "San Juanito", "San Luis De Cubarral", "San Martín", "Cabuyaro", "Puerto Gaitán", "Puerto Lopez"},
        {"Seleccionar", "Chachagui", "Consaca", "El Peñol", "El Tambo", "La Florida", "Nariño", "Pasto", "Sandoná", "Tangua", "Yacuanquer", "Ancuya", "Guaitarilla", "La Llanada", "Linares", "Los Andes", "Mallama", "Ospina", "Providencia", "Ricaurte", "Samaniego", "Santa Cruz", "Sapuyes", "Tuquerres", "Barbacoas", "El Charco", "Francisco Pizarro", "La Tola", "Magui", "Mosquera", "Olaya Herrera", "Roberto Payan", "Santa Barbara", "Tumaco", "Alban", "Arboleda", "Belen", "Buesaco", "Colon", "Cumbitara", "El Rosario", "El Tablon De Gomez", "La Cruz", "La Union", "Leiva", "Policarpa", "San Bernardo", "San Lorenzo", "San Pablo", "San Pedro De Cartago", "Taminango", "Aldana", "Contadero", "Córdoba", "Cuaspud", "Cumbal", "Funes", "Guachucal", "Gualmatan", "Iles", "Imues", "Ipiales", "Potosí", "Puerres", "Pupiales"},
        {"Seleccionar", "Arboledas", "Cucutilla", "Gramalote", "Lourdes", "Salazar", "Santiago", "Villa Caro", "Bucarasica", "El Tarra", "Sardinata", "Tibú", "Abrego", "Cachirá", "Convención", "El Carmen", "Hacarí", "La Esperanza", "La Playa", "Ocaña", "San Calixto", "Teorama", "Cúcuta", "El Zulia", "Los Patios", "Puerto Santander", "San Cayetano", "Villa Del Rosario", "Cácota", "Chitagá", "Mutiscua", "Pamplona", "Pamplonita", "Silos", "Bochalema", "Chinácota", "Durania", "Herrán", "Labateca", "Ragonvalia", "Toledo"},
        {"Seleccionar", "Colón", "Mocoa", "Orito", "Puerto Asis", "Puerto Caicedo", "Puerto Guzman", "Puerto Leguizamo", "San Francisco", "San Miguel", "Santiago", "Sibundoy", "Valle Del Guamuez", "Villa Garzon"},
        {"Seleccionar", "Armenia", "Buenavista", "Calarca", "Cordoba", "Genova", "Pijao", "Filandia", "Salento", "Circasia", "La Tebaida", "Montengro", "Quimbaya"},
        {"Seleccionar", "Dosquebradas", "La Virginia", "Marsella", "Pereira", "Santa Rosa De Cabal", "Apía", "Balboa", "Belén De Umbría", "Guática", "La Celia", "Quinchia", "Santuario", "Mistrató", "Pueblo Rico"},
        {"Seleccionar", "Chima", "Confines", "Contratación", "El Guacamayo", "Galán", "Gambita", "Guadalupe", "Guapotá", "Hato", "Oiba", "Palmar", "Palmas Del Socorro", "Santa Helena Del Opón", "Simacota", "Socorro", "Suaita", "Capitanejo", "Carcasí", "Cepitá", "Cerrito", "Concepción", "Enciso", "Guaca", "Macaravita", "Málaga", "Molagavita", "San Andrés", "San José De Miranda", "San Miguel", "Aratoca", "Barichara", "Cabrera", "Charalá", "Coromoro", "Curití", "Encino", "Jordán", "Mogotes", "Ocamonte", "Onzaga", "Páramo", "Pinchote", "San Gil", "San Joaquín", "Valle De San José", "Villanueva", "Barrancabermeja", "Betulia", "El Carmen De Chucurí", "Puerto Wilches", "Sabana De Torres", "San Vicente De Chucurí", "Zapatoca", "Bucaramanga", "California", "Charta", "El Playón", "Floridablanca", "Girón", "Lebríja", "Los Santos", "Matanza", "Piedecuesta", "Rionegro", "Santa Bárbara", "Surata", "Tona", "Vetas", "Aguada", "Albania", "Barbosa", "Bolívar", "Chipatá", "Cimitarra", "El Peñón", "Florián", "Guavatá", "Guepsa", "Jesús María", "La Belleza", "La Paz", "Landázuri", "Puente Nacional", "Puerto Parra", "San Benito", "Sucre", "Vélez"},
        {"Seleccionar", "Guaranda", "Majagual", "Sucre", "Chalán", "Coloso", "Morroa", "Ovejas", "Sincelejo", "Coveñas", "Palmito", "San Onofre", "Santiago De Tolú", "Tolú Viejo", "Buenavista", "Corozal", "El Roble", "Galeras", "Los Palmitos", "Sampués", "San Juan Betulia", "San Pedro", "Sincé", "Caimito", "La Unión", "San Benito Abad", "San Marcos"},
        {"Seleccionar", "Ambalema", "Armero", "Falan", "Fresno", "Honda", "Mariquita", "Palocabildo", "Carmen De Apicalá", "Cunday", "Icononzo", "Melgar", "Villarrica", "Ataco", "Chaparral", "Coyaima", "Natagaima", "Ortega", "Planadas", "Rioblanco", "Roncesvalles", "San Antonio", "Alvarado", "Anzoátegui", "Cajamarca", "Coello", "Espinal", "Flandes", "Ibague", "Piedras", "Rovira", "San Luis", "Valle De San Juan", "Alpujarra", "Dolores", "Guamo", "Prado", "Purificación", "Saldaña", "Suárez", "Casabianca", "Herveo", "Lerida", "Libano", "Murillo", "Santa Isabel", "Venadillo", "Villahermosa"},
        {"Seleccionar", "Andalucía", "Buga", "Bugalagrande", "Calima", "El Cerrito", "Ginebra", "Guacarí", "Restrepo", "Riofrio", "San Pedro", "Trujillo", "Tuluá", "Yotoco", "Alcala", "Ansermanuevo", "Argelia", "Bolívar", "Cartago", "El Águila", "El Cairo", "El Dovio", "La Unión", "La Victoria", "Obando", "Roldanillo", "Toro", "Ulloa", "Versalles", "Zarzal", "Buenaventura", "Caicedonia", "Sevilla", "Cali", "Candelaria", "Dagua", "Florida", "Jamundí", "La Cumbre", "Palmira", "Pradera", "Vijes", "Yumbo"},
        {"Seleccionar", "Caruru", "Mitú", "Pacoa", "Papunahua", "Taraira", "Yavaraté"},
        {"Seleccionar", "Cumaribo", "La Primavera", "Puerto Carreño", "Santa Rosalía",}
    };

    public static String[] direccionesPrueba = {
        "Carrera 10 #23-45",
        "Calle 45 #16-32",
        "Avenida 7 #5-10",
        "Diagonal 20 #12-30",
        "Transversal 15 #8-50",
        "Callejon 25 #18-22",
        "Calle 50 #24-36",
        "Avenida 3 #7-15",
        "Carrera 12 #32-19",
        "Transversal 8 #11-25",
        "Carrera 18 #14-28",
        "Calle 22 #6-40",
        "Avenida 5 #20-17",
        "Carrera 7 #9-13",
        "Diagonal 10 #25-29",
        "Transversal 14 #12-22",
        "Carrera 21 #8-16",
        "Calle 30 #17-33",
        "Avenida 9 #22-38",
        "Carrera 11 #15-20",
        "Diagonal 12 #10-25",
        "Transversal 6 #13-27",
        "Callejon 8 #19-35",
        "Carrera 14 #11-24",
        "Avenida 6 #28-31",
        "Calle 40 #9-14",
        "Carrera 17 #22-29",
        "Diagonal 8 #15-18",
        "Transversal 5 #12-20",
        "Carrera 13 #7-11",
        "Calle 25 #14-16",
        "Avenida 4 #19-25",
        "Diagonal 15 #8-12",
        "Transversal 10 #17-23",
        "Carrera 19 #21-30",
        "Calle 35 #11-19",
        "Avenida 8 #16-28",
        "Carrera 16 #13-27",
        "Diagonal 11 #22-36",
        "Transversal 7 #10-15",
        "Carrera 15 #18-24",
        "Calle 20 #7-10",
        "Avenida 10 #25-29",
        "Carrera 8 #12-17",
        "Diagonal 9 #14-22",
        "Transversal 12 #9-16",
        "Callejon 10 #20-26",
        "Carrera 20 #30-35",
        "Avenida 12 #17-20",
        "Calle 55 #21-28"
    };
    
    public static String[] urgencias = {
            "Resucitacion",
            "Emergencia",
            "Urgencia",
            "Urgencia menor",
            "Sin Urgencia"
        };
    
    public final static int TRIAGE_I = 0;
    public final static int TRIAGE_II = 1;
    public final static int TRIAGE_III = 2;
    public final static int TRIAGE_IV = 3;
    public final static int TRIAGE_V = 4;
    
    public static String[] areas = {
        "Cardiología",
        "Dermatología",
        "Neurología",
        "Oftalmología",
        "Ginecología",
        "Urologia",
        "Pediatría",
        "Cirugía General",
        "Psiquiatría",
        "Medicina Interna",
        "Ortopedia",
        "Traumatología",
        "Radiología",
        "Infectología",
        "Consultoria"
    };
    
    public static String[] especializaciones = {
        "Cardiólogo/a",
        "Dermatólogo/a",
        "Neurólogo/a",
        "Oftalmólogo/a",
        "Ginecólogo/a",
        "Urólogo/a",
        "Pediatra",
        "Cirujano/a",
        "Psiquiatra",
        "Internista",
        "Ortopedista",
        "Traumatólogo/a",
        "Radiólogo/a",
        "Infectólogo/a",
        "Medico General"
    };
    
    public static long dayBeetwen(String date1, String date2){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        long diff = 0;
        try {
            Date d1 = df.parse(date1);
            Date d2 = df.parse(date2);
            diff = d1.getTime() - d2.getTime();
            diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
        }
        return diff;
    }
    
    public static String getNextDate(String  curDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = format.parse(curDate);
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return format.format(calendar.getTime());
    }
    
    public static int calcularPrioridad(Paciente p, Cita c){
        int prioridadGenero = p.getGenero().equals("Mujer") ? 0 : 1;
        int prioridadEdad = p.getEdad() <= 12 ? 0 : p.getEdad() >= 60 ? 3 : 6;
        return c.getUrgencia() * 10 + prioridadEdad + prioridadGenero;
    }
}
