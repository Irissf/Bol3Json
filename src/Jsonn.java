import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.json.*;
import javax.net.ssl.HttpsURLConnection;


public class Jsonn {
    public JsonValue leeJSON(String ruta) {
        JsonReader reader = null;
        JsonValue jsonV = null;
        try {
            if (ruta.toLowerCase().startsWith("http://")) {
                URL url = new URL(ruta);
                InputStream is = url.openStream();
                reader = Json.createReader(is);
            } else if (ruta.toLowerCase().startsWith("https://")) {
                URL url = new URL(ruta);
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                InputStream is = conn.getInputStream();
                reader = Json.createReader(is);
            } else {
                reader = Json.createReader(new FileReader(ruta));
            }
            jsonV = reader.read();
            /*
             * JsonStructure jsonSt = reader.read();
             * System.out.println(jsonSt.getValueType()); JsonObject jsonObj =
             * reader.readObject(); System.out.println(jsonObj.getValueType()); JsonArray
             * jsonArr = reader.readArray(); System.out.println(jsonArr.getValueType());
             */
        } catch (IOException e) {
            System.out.println("Error procesando documento Json " + e.getLocalizedMessage());
        }
        if (reader != null)
            reader.close();
        return jsonV;
    }

    public String getResultadosPiloto(String piloto, int anho) {
        JsonValue json = leeJSON("http://ergast.com/api/f1/" + anho + "/drivers/" + piloto + "/results.json");
        return json.toString();
    }

    public static void main(String[] args) {
        Jsonn j = new Jsonn();
        File f = new File("d:/paises.json");
        JsonValue json = j.leeJSON(f.getAbsolutePath());
        System.out.println(json.toString());
        System.out.println(j.getResultadosPiloto("alonso", 2017));
    }
}