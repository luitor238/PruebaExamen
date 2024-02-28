package com.example.pruebaexamen

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "Libros.db"
        private const val TABLA_LIBROS = "libros"
        private const val COLUMN_TITULO = "titulo"
        private const val COLUMN_PAGINAS = "paginas"
        private const val COLUMN_AUTOR = "autor"
        private const val COLUMN_ANIO = "anio"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLA_LIBROS ($COLUMN_TITULO TEXT, $COLUMN_PAGINAS INTEGER, $COLUMN_AUTOR TEXT, $COLUMN_ANIO ITEGER)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        db.execSQL("DROP TABLE IF EXISTS $TABLA_LIBROS")
    }

    fun insertarLibro(libro: Book){
        val db = this.writableDatabase
        val values = ContentValues().apply{
            put(COLUMN_TITULO, libro.getTitulo())
            put(COLUMN_PAGINAS, libro.getPaginas())
            put(COLUMN_AUTOR, libro.getAutor())
            put(COLUMN_ANIO, libro.getAnio())
        }

        db.insert(TABLA_LIBROS, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getLibros(): ArrayList<Book>{
        val libros = ArrayList<Book>()
        val query = "SELECT * FROM $TABLA_LIBROS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        if(cursor.moveToFirst()){
            do{
                val titulo = cursor.getString(cursor.getColumnIndex(COLUMN_TITULO))
                val paginas = cursor.getInt(cursor.getColumnIndex(COLUMN_PAGINAS))
                val autor = cursor.getString(cursor.getColumnIndex(COLUMN_AUTOR))
                val anio = cursor.getInt(cursor.getColumnIndex(COLUMN_ANIO))
                val libro = Book(titulo, paginas)
                libro.setAutor(autor)
                libro.setAnio(anio)
                libros.add(libro)
            }while(cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return libros
    }
}

