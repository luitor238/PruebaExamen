package com.example.pruebaexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pruebaexamen.databinding.ActivityBookDisplayBinding
import com.example.pruebaexamen.databinding.ActivityBookTitleBinding

class BookDisplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_display)

        val db = DatabaseHelper(this)

        binding = ActivityBookDisplayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val libro = intent.getSerializableExtra("libro") as Book

        binding.libro.text= libro.toString()

        binding.guardar.setOnClickListener{
            db.insertarLibro(libro)
            val libros = db.getLibros()
            binding.guardar.visibility = View.GONE

            binding.lista.text = libros.joinToString("\n")
        }

        val libros = db.getLibros()

        binding.lista.text = libros.joinToString("\n")

        binding.otro.setOnClickListener{
            val intent = Intent(this,BookTitleActivity::class.java)
            startActivity(intent)
        }
    }
}