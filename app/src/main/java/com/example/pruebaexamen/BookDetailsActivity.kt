package com.example.pruebaexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pruebaexamen.databinding.ActivityBookDetailsBinding
import com.example.pruebaexamen.databinding.ActivityBookTitleBinding

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val db = DatabaseHelper(this)

        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.siguiente.setOnClickListener{
            if(binding.autor.text.isNotEmpty() && binding.anio.text.isNotEmpty()){
                val libro = intent.getSerializableExtra("libro") as Book
                libro.setAutor(binding.autor.text.toString())
                libro.setAnio(binding.anio.text.toString().toInt())

                val intent = Intent(this, BookDisplayActivity::class.java)
                intent.putExtra("libro",libro)
                startActivity(intent)
            }
            else{
                binding.aviso.text="Rellena los campos"
            }
        }

        binding.atras.setOnClickListener{
            val intent = Intent(this, BookTitleActivity::class.java)
            startActivity(intent)
        }


    }
}