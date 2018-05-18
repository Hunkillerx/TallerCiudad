package ean.programacionavanzada.taller

import ean.collections.Graph
import ean.collections.IGraph
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class TallerCiudadTest {
    val grafo: IGraph<Int, Ciudad, Int> = Graph()

    @BeforeSuite
    fun setUp() {
        Utils.crearGrafoCiudades(grafo)
    }

    @Test
    fun pruebaInicial(): Unit {
        println("Numero de ciudades: " + grafo.order)
        for (vertice in grafo) {
            println("${vertice.first} - ${vertice.second}")
            println("    Vecinos: ${grafo.neighbors(vertice.first)}")
        }
    }

    @Test
    fun pruebaContador(){
        assertEquals(4,TallerCiudades.contarCiudadesSigloXVI(grafo))
    }

    @Test
    fun pruebaNombreVecinoMasCorto(){
        assertEquals("Cali",TallerCiudades.nombreCiudadMasCercana(grafo,"Quito"))
    }

    @Test
    fun pruebaEsFronteriza(){
        assertTrue { TallerCiudades.esFronteriza(grafo,"Quito") }
    }

    @Test
    fun pruebaCiudadesFronterizas(){
        println(TallerCiudades.listaFronterizas(grafo))
    }
}