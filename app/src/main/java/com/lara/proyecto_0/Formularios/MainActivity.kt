package com.lara.proyecto_0.Formularios

import android.content.Context
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
import com.lara.proyecto_0.modelo.PacienteDAO

class MainActivity : AppCompatActivity() {
    private lateinit var btnRegistrar: Button
    private lateinit var btnIniciarSesion: Button
    private lateinit var txtUsuario: EditText
    private lateinit var txtClave: EditText
    private lateinit var oPacienteDao: PacienteDAO
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        asignarReferencias()
    }

    fun asignarReferencias(){
        btnRegistrar=findViewById(R.id.btnSalir)
        btnIniciarSesion=findViewById(R.id.btnIniciarSesion)
        txtUsuario=findViewById(R.id.txtUsuario)
        txtClave=findViewById(R.id.txtClave)
        oPacienteDao = PacienteDAO(this)

        btnRegistrar.setOnClickListener{
            val intent= Intent(this, PacienteActivity::class.java)
            startActivity(intent)
        }

        btnIniciarSesion.setOnClickListener{
            Ingresar()
        }
    }

    fun Ingresar(){
        val dni=txtUsuario.text.toString()
        val clave=txtClave.text.toString()
        var valida = true

        if (dni.isEmpty()){
            valida =false
            txtUsuario.setError("El DNI del paciente es obligatorio")
        }
        if (dni.length != 8){
            valida =false
            txtUsuario.setError("El DNI debe ser 8 dígitos")
        }
        if (clave.isEmpty()){
            valida =false
            txtClave.setError("La clave es obligatorio")
        }

        if (valida)
        {
            var listaPaciente = oPacienteDao.iniciarSersion(dni,clave)

            if (listaPaciente.isNotEmpty()) {
                val intent = Intent(this, PrincipalActivity::class.java)
                intent.putExtra("var_nombrePaciente",listaPaciente[0].nombrePaciente)
                startActivity(intent)
            } else {
                mostrarMensaje("Usuario y/o contraseña incorrecto")
            }
        }
    }

    fun mostrarMensaje(mensaje:String){
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", null)
        ventana.create().show()
    }
}