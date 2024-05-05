package com.lara.proyecto_0.Formularios

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lara.proyecto_0.Adaptador.AdaptadorPersonalizado
import com.lara.proyecto_0.R
import com.lara.proyecto_0.modelo.ServicioDAO
import org.w3c.dom.Text

class PrincipalActivity : AppCompatActivity() {
    private lateinit var btnSalir: Button
    private lateinit var lblPaciente: TextView
    private lateinit var rvServicios: RecyclerView
    private lateinit var oServicioDao: ServicioDAO
    private var adaptador:AdaptadorPersonalizado=AdaptadorPersonalizado()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        asignarReferencias()
        mostrarServicios()
        recuperarDatos()
    }

    fun asignarReferencias(){
        btnSalir=findViewById(R.id.btnSalir)
        lblPaciente=findViewById(R.id.lblPaciente)
        btnSalir.setOnClickListener{
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        oServicioDao = ServicioDAO(this)
        rvServicios=findViewById(R.id.rvServicios)
        rvServicios.layoutManager= LinearLayoutManager(this)
        rvServicios.adapter = adaptador
    }

    fun mostrarMensaje(mensaje:String){
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", { dialogInterface: DialogInterface, i:Int->
            val intent =Intent(this,PrincipalActivity::class.java)
            startActivity(intent)
        })
        ventana.create().show()
    }

    fun mostrarServicios(){
        var listaServicios = oServicioDao.cargarServicios()
        adaptador.agregarDatos(listaServicios)
        adaptador.contexto(this)
    }

    fun recuperarDatos()
    {
        if (intent.hasExtra("var_nombrePaciente"))
        {
            lblPaciente.setText(intent.getStringExtra("var_nombrePaciente"))
        }
    }

}