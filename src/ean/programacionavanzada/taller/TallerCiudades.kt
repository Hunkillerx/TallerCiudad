/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad EAN (Bogotá - Colombia)
 * Departamento de Sistemas
 * Faculta de Ingeniería
 *
 * Proyecto EAN Grafo de Ciudades
 * Fecha: 16 de mayo de 2018
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package ean.programacionavanzada.taller

import ean.collections.ArrayList
import ean.collections.IGraph
import ean.collections.IList
import jdk.nashorn.internal.ir.annotations.Ignore

/**
 * Aquí vamos a desarrollar todos los métodos del taller en cuestión
 */

object TallerCiudades {
    /* Cuantas ciudades de colombia se fundaron en el siglo XVI? */
    fun contarCiudadesSigloXVI(g: IGraph<Int, Ciudad, Int>): Int {
        var c = 0
        for (ident in g.vertices) {
            val ciu: Ciudad = g[ident]
            if (ciu.pais.toUpperCase() == "COLOMBIA" && ciu.añoFundacion in 1500..1599) {
                c++
            }
        }
        return c
    }

    fun ciudadConMasVecinos(g: IGraph<Int, Ciudad, Int>): String {
        var nombreCiudadConMasVecinos = ""
        var numVecinosMayor = 0

        for (v in g.vertices) {
            val NV = g.degree(v)
            if (NV > numVecinosMayor) {
                numVecinosMayor = NV
                nombreCiudadConMasVecinos = g[v].nombre
            }
        }
        return nombreCiudadConMasVecinos
    }

    fun nombreCiudadMasCercana(g: IGraph<Int, Ciudad, Int>, nom: String): String {
        var bandera = 100000000
        var bandera2 = ""
        for (i in g.vertices) {
            var ciudad = g.getVertex(i)
            if (ciudad.nombre == nom) {
                var veci = g.neighbors(ciudad.identificador)
                for (i in veci) {
                    if (g.existsEdge(ciudad.identificador, i)) {
                        var x = g.getEdge(ciudad.identificador, i)
                        if (x < bandera) {
                            bandera = x
                            bandera2 = g.getVertex(i).nombre
                        }
                    }
                }
            }
        }
        return bandera2
    }

    fun listaFronterizas(g: IGraph<Int, Ciudad, Int>):IList<String>{
        var bandera:IList<String> = ArrayList()
        for (i in g.vertices){
            var ciudad = g.getVertex(i).nombre
            if (esFronteriza(g,ciudad)) {
                bandera.add(ciudad)
            }
        }
        return bandera
    }

    fun esFronteriza(g: IGraph<Int, Ciudad, Int>,nom: String): Boolean {
        var bandera = false
        for (i in g.vertices) {
            var ciudad = g.getVertex(i)
            if (ciudad.nombre == nom) {
                var veci = g.neighbors(ciudad.identificador)
                for (i in veci){
                    if (g.getVertex(i).pais != ciudad.pais){
                        bandera = true
                    }
                }
            }
        }
        return bandera
    }
}

/**
 * Aquí se almacena la información de una ciudad, que estará dentro de un grafo
 */
class Ciudad : Comparable<Ciudad> {
    //----------------------------------------------------------------------------------
    // Atributos de la Ciudad
    //----------------------------------------------------------------------------------
    var identificador: Int = 0
    var nombre: String = ""
    var poblacion: Int = 0
    var departamento: String = ""
    var pais: String = ""
    var añoFundacion: Int = 0

    //----------------------------------------------------------------------------------
    // Constructor
    //----------------------------------------------------------------------------------
    constructor(identificador: Int, nombre: String, poblacion: Int, departamento: String, pais: String, añoFundacion: Int) {
        this.identificador = identificador
        this.nombre = nombre
        this.poblacion = poblacion
        this.departamento = departamento
        this.pais = pais
        this.añoFundacion = añoFundacion
    }
    //----------------------------------------------------------------------------------
    // Métodos
    //----------------------------------------------------------------------------------

    /**
     * Permite saber si dos ciudades son iguales
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Ciudad) return false

        if (identificador != other.identificador) return false

        return true
    }

    override fun hashCode(): Int {
        return identificador
    }

    override fun toString(): String {
        return "Ciudad(identificador=$identificador, nombre='$nombre', poblacion=$poblacion, departamento='$departamento', pais='$pais', añoFundacion=$añoFundacion)"
    }

    override fun compareTo(other: Ciudad): Int =
            compareValues(this.identificador, other.identificador)
}