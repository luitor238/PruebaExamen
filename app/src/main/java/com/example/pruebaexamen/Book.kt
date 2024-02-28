package com.example.pruebaexamen

import java.io.Serializable

class Book (private var titulo: String, private var paginas: Int): Serializable {
    private var autor = ""
    private var anio = 0

    fun getTitulo() :String{
        return titulo
    }
    fun getPaginas():Int{
        return paginas
    }
    fun getAutor():String{
        return autor
    }
    fun getAnio():Int{
        return anio
    }

    fun setAutor(newAutor: String){
        this.autor=newAutor
    }
    fun setAnio(newAnio: Int){
        this.anio=newAnio
    }

    override fun toString(): String {
        return "titulo='$titulo', paginas=$paginas, autor='$autor', anio=$anio)"
    }


}