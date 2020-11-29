
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Ejercicios {

    public static Jsonn jsonn = new Jsonn();

    // Ejercicio 1
    public static JsonObject TiempoCiudad(String ciudad) {

        return (JsonObject) jsonn.leeJSON("http://api.openweathermap.org/data/2.5/weather?q=" + ciudad
                + ",es&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0&units=metric");
    }

    // Ejercicio 2
    public static JsonObject LogiLati(double longitud, double latitud) {
        return (JsonObject) jsonn.leeJSON("http://api.openweathermap.org/data/2.5/weather?lat=" + latitud + "&lon="
                + longitud + "&APPID=8f8dccaf02657071004202f05c1fdce0");
    }

    // Ejercicio 3
    public static JsonObject XLongLat(double longitud, double latitud, int numLocalidades) {
        return (JsonObject) jsonn.leeJSON("http://api.openweathermap.org/data/2.5/find?lat=" + latitud + "&lon="
                + longitud + "&cnt=" + numLocalidades + "&APPID=8f8dccaf02657071004202f05c1fdce0");
    }

    // ===============================================================================================

    /*
     * Ejercicio 5 Crea un método que devuelva el id de una ciudad en Open Weather
     * Map
     */
    public static int consultaId(String ciudad) {
        return TiempoCiudad(ciudad).getInt("id");
    }

    // ===============================================================================================

    /*
     * Ejercicio 6 Crea un método que devuelva el nombre de una ciudad en Open
     * Weather Map.
     */
    public static String nombreCiudad(double longitud, double latitud) {
        return LogiLati(longitud, latitud).getString("name");
    }

    // ===============================================================================================

    /*
     * Ejercicio 7 Crea un método que devuelva las coordenadas de una ciudad en Open
     * Weather Map.
     */
    public static Coordenadas coordenadasCiudad(String ciudad) {
        JsonObject jsonCoo = TiempoCiudad("Vigo").getJsonObject("coord");
        Coordenadas coo = new Coordenadas(jsonCoo.getJsonNumber("lon").doubleValue(),
                jsonCoo.getJsonNumber("lat").doubleValue());
        return coo;

    } // No saca los decimales

    // ===============================================================================================

    // Ejercicio 8
    /*
     * Crea un método que devuelva: fecha, temperatura, humedad, probabilidad de
     * cielo con nubes, velocidad del viento y pronóstico del tiempo.
     */
    public static Pronostico datosClimaCiudad(String ciudad) {

        long fecha = (long) TiempoCiudad(ciudad).getInt("dt");
        String fechaFormat = unixTimeToString(fecha);
        JsonObject tiempoMain = TiempoCiudad(ciudad).getJsonObject("main");
        JsonObject nubecitas = TiempoCiudad(ciudad).getJsonObject("clouds");
        JsonArray weatherDescipt = TiempoCiudad(ciudad).getJsonArray("weather");
        JsonObject weatherMain = weatherDescipt.get(0).asJsonObject();
        JsonObject viento = TiempoCiudad(ciudad).getJsonObject("wind");

        Pronostico pronostico = new Pronostico(ciudad, fechaFormat, tiempoMain.getJsonNumber("temp").doubleValue(),
                tiempoMain.getInt("humidity"), nubecitas.getInt("all"), viento.getInt("speed"),
                weatherMain.getString("description"));
        return pronostico;
    }

    // ===============================================================================================

    /*
     * Ejercicio 9 Crea un método que devuelva los datos anteriores para las X
     * ciudades cercanas a una dada (incluyendo esta)
     */
    public static ArrayList<Pronostico> datosClimasMuchasCiudades(double lat, double lon, int numero) {
        ArrayList<Pronostico> cordi = new ArrayList<>();
        JsonObject ciudadesObjeto = XLongLat(lon, lat, numero);
        JsonArray ciudadesArray = ciudadesObjeto.getJsonArray("list");
        Pronostico pro;
        String[] ciudades = new String[numero];
        for (int i = 0; i < ciudadesArray.size(); i++) {
            ciudades[i] = ciudadesArray.get(i).asJsonObject().getString("name");
            pro = datosClimaCiudad(ciudades[i]);
            cordi.add(pro);
        }
        return cordi;
    }

    // ===============================================================================================
    /*
     * Ejercicio 13 Usando open trivia database: https://opentdb.com/api_config.php
     * genera 20 preguntas de informática, de dificultad alta, y muestra la
     * preguntas y respuestas marcando las correctas con un *.
     */

    public static JsonObject Questions() {
        return (JsonObject) jsonn.leeJSON("https://opentdb.com/api.php?amount=20");
    }

    public static void readTrivialInfo() {
        String pregunta = "", respuesta = "";
        JsonObject questions = Questions();
        JsonArray results = questions.getJsonArray("results");
        for (int i = 0; i < results.size(); i++) {
            pregunta = results.get(i).asJsonObject().getString("question");
            respuesta = results.get(i).asJsonObject().getString("correct_answer");
            System.out.println("Pregunta " + i + " : " + pregunta);
            System.out.println("respuestas:");
            System.out.println("*" + respuesta);
            JsonArray res = results.getJsonObject(i).getJsonArray("incorrect_answers");
            for (int j = 0; j < res.size(); j++) {
                System.out.println(res.get(j).toString());
            }
            System.out.println("==========================================");
        }
    }

    // ===============================================================================================
    /*
     * Ejercicio 14 Crea un método que dado un una localidad, una cantidad de
     * kilómetros y una cantidad X, y devuelva los X eventos cercanos a esa
     * localidad en el radio especificado.
     */
    public static JsonObject eventsLocate(int km, String locate, int numberEvents) {
        return (JsonObject) jsonn.leeJSON("http://api.eventful.com/json/events/search?q=music&l=" + locate
                + "&units=km&within=" + km + "&page_size=" + numberEvents + "&app_key=c2tPtVFTrSk8xnQS");
    }

    public static ArrayList<EventsLocate> showEvents(int km, String locate, int numberEvents) {
        ArrayList<EventsLocate> eventsList = new ArrayList<>();
        JsonObject docuEvents = eventsLocate(km, locate, numberEvents);
        JsonObject events = docuEvents.getJsonObject("events");
        JsonArray event = events.getJsonArray("event");
        for (int i = 0; i < event.size(); i++) {
            eventsList.add(new EventsLocate(event.get(i).asJsonObject().getString("title"),
                    event.get(i).asJsonObject().getString("url"), event.get(i).asJsonObject().getString("city_name"),
                    event.get(i).asJsonObject().getString("venue_address"),event.get(i).asJsonObject().getString("start_time"),
                    event.get(i).asJsonObject().getString("description"),event.get(i).asJsonObject().getString("country_name")));
        }
        return eventsList;
    }

    // ===============================================================================================
    /*
     * Ejercicio 15 Crea dos métodos que, para cada evento anterior, muestre la
     * información detallada de cada lugar en el que se desarrolle y la información
     * detallada del evento.
     */
    public static void showEv(EventsLocate event){
        System.out.printf("El evento: %s ,\nEn la fecha: %s\nDescrpción: %s\nWeb:%s\n",event.getTitleEvent(),
        event.getTimeStart(),event.getDescription(),event.getUrlEvent());
        
    }
    public static void showLocat(EventsLocate event){
       System.out.printf("Se realizará en %s, ciudad:%s\nDirección: %s\n",
        event.getCountryName(),event.getCity_name(),event.getAdress());
    }

    // ===============================================================================================

    /*Ejercicio1 17
     * ¿Cuál es el tiempo actual de cada ciudad en donde se desarrollen los eventos encontrados?
     */

    public static void temperatureEventCity(String locate){
        try {
           Pronostico pro = datosClimaCiudad(locate);
           System.out.printf("La tempreatura de %s es de %.2f\n",locate,pro.getTemperatura());
        } catch(NullPointerException e){
            System.out.println("No se dispone del tiempo para esa ciudad");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void main(String[] args) {

        // System.out.printf("El id de Vigo es :%d\n", consultaId("Vigo"));
        // System.out.printf("La ciudad es :%s", nombreCiudad(-8.72, 42.23));
        // Coordenadas coo = coordenadasCiudad("Vigo");
        // System.out.println("Las coordenadas son " + coo.getLatitud() + "\ny:" +
        // coo.getLongitud());
        // System.out.println();
        // Pronostico pro = datosClimaCiudad("Vigo");
        // System.out.printf("Vigo a fecha de:%s temperatura:%.2fºC, humedad:%d%%, cielo
        // con nubes:%d%% ", pro.getFecha(),
        // pro.getTemperatura(), pro.getHumedad(), pro.getProbabilidadNubes());
        // System.out.printf("Velocidad viento:%d m/s, pronostico del tiempo:%s",
        // pro.getVelocidadViento(),
        // pro.getPronosticoTiempo());
        // datosClimasMuchasCiudades(42.23, -8.72, 3);

        // ejercicio 9=======================================

        // ArrayList<Pronostico> cordi = datosClimasMuchasCiudades(42.23, -8.72, 3);
        // System.out.println("\n=====================================================================================");
        // for (int i = 0; i < cordi.size(); i++) {
        // System.out.printf("%s a fecha de:%s temperatura:%.2fºC, humedad:%d%%, cielo
        // con nubes:%d%% \n",
        // cordi.get(i).getCiudad(), cordi.get(i).getFecha(),
        // cordi.get(i).getTemperatura(),
        // cordi.get(i).getHumedad(), cordi.get(i).getProbabilidadNubes());
        // System.out.printf("Velocidad viento:%d m/s, pronostico del tiempo:%s\n",
        // cordi.get(i).getVelocidadViento(),
        // cordi.get(i).getPronosticoTiempo());
        // System.out.println("=====================================================================================");
        // }

        // ejer 13==========================================

        readTrivialInfo();

        // ejer 14==========================================

        // ArrayList<EventsLocate> eventsList = showEvents(250, "Vigo", 4);
        // for (EventsLocate eventsLocate : eventsList) {
        //     showEv(eventsLocate);
        //     showLocat(eventsLocate);
        //     temperatureEventCity(eventsLocate.getCity_name());
        //     System.out.println("====================================================================================");
        // }

    }

    public static String unixTimeToString(long unixTime) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Instant.ofEpochSecond(unixTime).atZone(ZoneId.of("GMT+1")).format(formatter);
    }
}
