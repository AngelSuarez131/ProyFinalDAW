package com.example.dawpfinal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dawpfinal.databinding.ActivityMainBinding
import com.example.dawpfinal.databinding.LayoutRegistrarBinding

class actRegistrar : AppCompatActivity() {
    lateinit var dbHelper: SQLHelper
    lateinit var binding: LayoutRegistrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_registrar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registrar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = LayoutRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = SQLHelper(this)

        binding.btRegistrar.setOnClickListener {
            if (binding.etCorreo.text.isNotBlank() && binding.etContra.text.isNotBlank()) {

                try {
                    dbHelper.agnadirDatos(
                        binding.etCorreo.text.toString(),
                        binding.etContra.text.toString()
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error en registro", Toast.LENGTH_SHORT).show()
                }

                binding.etCorreo.text.clear()
                binding.etContra.text.clear()

                Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btinSesion.setOnClickListener(){
            val intent  = Intent(this, MainActivity::class.java)
            startActivity(intent )
        }
    }
}