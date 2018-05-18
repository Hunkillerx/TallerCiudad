/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad EAN (Bogotá - Colombia)
 * Departamento de Sistemas
 * Faculta de Ingeniería
 *
 * Proyecto EAN Grafo de Ciudades
 * Fecha: Mayo 30 de 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package ean.programacionavanzada.taller;

import ean.collections.IGraph;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Funciones de utilidad para el taller de grafo de ciudades
 */
public class Utils {
    /**
     * Obtiene un grafo de ciudades a partir del archivo "ciudades.yaml"
     */
    public static void crearGrafoCiudades(IGraph<Integer, Ciudad, Integer> grafo) {
       try {
            InputStream in = new FileInputStream(new File("data/ciudades.yml"));
            Yaml yaml = new  Yaml();

            Map<String, Object> data = (Map<String, Object>) yaml.load(in);

            List<Map<String, Object>> ciudades = (List<Map<String, Object>>) data.get("ciudades");

            for (Map<String, Object> ciudad : ciudades) {
                int identificador = (Integer) ciudad.get("identificador");
                String nombre = (String) ciudad.get("nombre");
                int poblacion = (Integer) ciudad.get("poblacion");
                String departamento = (String) ciudad.get("departamento");
                String pais = (String) ciudad.get("pais");
                int añoFundacion = (Integer) ciudad.get("fundada");
                Ciudad city = new Ciudad(identificador, nombre, poblacion, departamento, pais, añoFundacion);
                grafo.addVertex(city.getIdentificador(), city);
            }

            List<Map<String, Object>> arcos = (List<Map<String, Object>>) data.get("arcos");
            for (Map<String, Object> arco : arcos) {
                int origen = (Integer) arco.get("origen");
                int destino = (Integer) arco.get("destino");
                int distancia = (Integer) arco.get("distancia");

                grafo.addEdge(origen, destino, distancia);
                grafo.addEdge(destino, origen, distancia);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
