package com.example.dawpfinal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dawpfinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btInisesion.setOnClickListener {
            if (binding.etCorreo.text.toString() == "Usuariotest" && binding.etCodigo.text.toString() == "contra") {
                val intent = Intent(this, actMainMenu::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Email o contraseña invalidos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btSalir.setOnClickListener(){
            finish()
        }
    }
}