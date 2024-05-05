package com.lara.proyecto_0.Adaptador

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lara.proyecto_0.Formularios.MainActivity
import com.lara.proyecto_0.R
import com.lara.proyecto_0.entidades.Servicio

class AdaptadorPersonalizado: RecyclerView.Adapter<AdaptadorPersonalizado.MiViewHolder>() {
    private var listaServicios:ArrayList<Servicio> = ArrayList()
    private lateinit var context:Context

    private var onClickDeleteItem:((Servicio) -> Unit)?=null

    fun onClickDeleteItem(callback: (Servicio) -> Unit){
        this.onClickDeleteItem=callback
    }

    fun agregarDatos(items: ArrayList<Servicio>){
        this.listaServicios = items
    }

    fun contexto(context: Context){
        this.context=context
    }

    class MiViewHolder(var view: View):RecyclerView.ViewHolder(view) {
        private var precio=view.findViewById<TextView>(R.id.filaPrecio)
        private var nombreServicio=view.findViewById<TextView>(R.id.filaNombreServicios)
        var filaEditar=view.findViewById<ImageButton>(R.id.filaEditar)

        fun bindView(servicio: Servicio ){
            nombreServicio.text = servicio.nombreServicio.toString()
            precio.text=servicio.precio.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MiViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fila, parent, false)
    )

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val servicioItem=listaServicios[position]
        holder.bindView(servicioItem)

        /*
        holder.filaEditar.setOnClickListener{
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("var_idServicio",listaServicios[position].idServicio)
            intent.putExtra("var_nombreServicio",listaServicios[position].nombreServicio)
            intent.putExtra("var_precio",listaServicios[position].precio)
            context.startActivity(intent)
        }

        holder.filaEliminar.setOnClickListener{
            onClickDeleteItem?.invoke(personaItem)
        }
        */
    }

    override fun getItemCount(): Int {
        return listaServicios.size
    }

}