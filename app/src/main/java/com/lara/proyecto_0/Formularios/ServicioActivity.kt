package com.lara.proyecto_0.Formularios

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lara.proyecto_0.R
import com.lara.proyecto_0.entidades.Paciente
import com.lara.proyecto_0.entidades.Servicio
import com.lara.proyecto_0.modelo.PacienteDAO
import com.lara.proyecto_0.modelo.ServicioDAO

class ServicioActivity : AppCompatActivity() {
    private lateinit var txtServicio: EditText
    private lateinit var txtPrecio: EditText
    private lateinit var btnGuardarServicio: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_servicio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        asignarReferencias()
    }

    fun asignarReferencias()
    {
        txtServicio=findViewById(R.id.txtServicio)
        txtPrecio=findViewById(R.id.txtPrecio)
        btnGuardarServicio=findViewById(R.id.btnGuardarServicio)
        btnGuardarServicio.setOnClickListener{
            capturarDatos()
        }

    }

    fun capturarDatos()
    {
        val nombreServicio=txtServicio.text.toString()
        val precio=txtPrecio.text.toString().toDouble()

        val servicio= Servicio()
        servicio.nombreServicio = nombreServicio
        servicio.precio = precio

        registrar(servicio)
    }

    fun registrar(servicio: Servicio){
        val servicioDao = ServicioDAO(this)
        val mensaje = servicioDao.registrarServicio(servicio)

        mostrarmMensaje(mensaje)
    }

    fun mostrarmMensaje(mensaje:String){
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", { dialogInterface: DialogInterface, i:Int->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
        ventana.create().show()
    }
}