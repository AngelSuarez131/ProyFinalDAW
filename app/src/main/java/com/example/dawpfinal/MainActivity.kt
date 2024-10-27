package com.example.dawpfinal

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dawpfinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var etCodigo: EditText
    private lateinit var ivTogglePassword: ImageView
    private var isPasswordVisible: Boolean = false

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

        val dbHelper = SQLHelper(this)

        binding.btInisesion.setOnClickListener {
            val correo = binding.etCorreo.text.toString()
            val contrasena = binding.etCodigo.text.toString()
            if (dbHelper.verificarUsuario(correo, contrasena)) {
                val intent = Intent(this, actMainMenu::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Email o contraseña invalidos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btSalir.setOnClickListener(){
            finish()
        }
        etCodigo = findViewById(R.id.etCodigo)
        ivTogglePassword = findViewById(R.id.ivTogglePassword)

        ivTogglePassword.setOnClickListener {
            if (isPasswordVisible) {
                etCodigo.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                ivTogglePassword.setImageResource(R.drawable.eye)
            } else {
                etCodigo.inputType = InputType.TYPE_CLASS_TEXT
                ivTogglePassword.setImageResource(R.drawable.hide)
            }
            etCodigo.setSelection(etCodigo.text.length)
            isPasswordVisible = !isPasswordVisible
        }
        binding.btRegistrar.setOnClickListener(){
            val intent  = Intent(this, actRegistrar::class.java)
            startActivity(intent )
        }
    }
}