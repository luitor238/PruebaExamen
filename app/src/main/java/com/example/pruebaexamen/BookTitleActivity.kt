package com.example.pruebaexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pruebaexamen.databinding.ActivityBookTitleBinding

class BookTitleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookTitleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_title)

        binding = ActivityBookTitleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.siguiente.setOnClickListener{
            if(binding.titulo.text.isNotEmpty() && binding.paginas.text.isNotEmpty()){
                val libro = Book(binding.titulo.text.toString(), binding.paginas.text.toString().toInt())
                val intent = Intent(this, BookDetailsActivity::class.java)
                intent.putExtra("libro",libro)
                startActivity(intent)
            }
            else{
                binding.aviso.text="Rellena los campos"
            }
        }
    }
}