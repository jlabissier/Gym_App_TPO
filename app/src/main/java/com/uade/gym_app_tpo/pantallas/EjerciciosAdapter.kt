package com.uade.gym_app_tpo.pantallas

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.uade.gym_app_tpo.R
import com.uade.gym_app_tpo.objetos.Category
import com.uade.gym_app_tpo.objetos.Exercise
import com.uade.gym_app_tpo.objetos.Muscle

class EjerciciosAdapter(var ejercicios: MutableList<Exercise>,
                        var categorias: MutableList<Category>,
                        var musculos: MutableList<Muscle>,
                        context: Context): RecyclerView.Adapter<ItemEjercicio>() {

    var onItemClick : ( (Exercise,Muscle) -> Unit)? = null
    private val db = FirebaseFirestore.getInstance()
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemEjercicio {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_ejercicio,parent,false)
        return ItemEjercicio(view)
    }

    override fun onBindViewHolder(holder: ItemEjercicio, position: Int) {
        holder.name.text = ejercicios[position].name
        val catId = ejercicios[position].category


        // obtengo el nombre de la cat
        for(cat in categorias)
            if (cat.id == catId){
                holder.cat.text = cat.name
                break;
            }

        // obtengo el musculo.
        var musculo : Muscle? = null
        var muscleId = ejercicios[position].muscles?.get(0)!!

        for(musc in musculos)
            if(musc.id == muscleId){
                musculo = musc
            }



        if (position == (itemCount - 1)) {
            holder.separator.visibility = View.INVISIBLE
        } else {
            holder.separator.visibility = View.VISIBLE
        }

        // cambio a pantalla descripcion
        var ejercicio = ejercicios[position]
        holder.itemView.setOnClickListener{
            Log.d("PRUEBA","Prueba descripcion")
            onItemClick?.invoke(ejercicio,musculo!!)
        }

        var fav = holder.fav;

        fav.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Switch1:ON" else "Switch1:OFF"
            Log.d("PRUEBA",message);

            if(isChecked){
                firebaseAuth = FirebaseAuth.getInstance()
                val firebaseUser = firebaseAuth.currentUser
                val uid = firebaseUser!!.uid
                val email : String = firebaseUser.email!!
                Log.d("PRUEBA",email);

                db.collection("usuarios").document(email).collection("favoritos").document(ejercicio.id.toString()).set(
                    hashMapOf(
                        "id" to ejercicio.id,
                        "uid" to ejercicio.uuid,
                        "name" to ejercicio.name,
                        "description" to ejercicio.description,
                        "category" to ejercicio.category,
                        "muscles" to ejercicio.muscles,
                        "favorito" to ejercicio.favorito,
                    )
                )
            }
            else{

            }
        }
    }

    override fun getItemCount(): Int {
        //Log.d("debug","ejercicios.size: " + ejercicios.size)
        return ejercicios.size
    }

    fun update(new_ejercicios: MutableList<Exercise>,
               new_categorias: MutableList<Category>,
               new_musculos: MutableList<Muscle>){
        ejercicios = new_ejercicios
        categorias = new_categorias
        musculos = new_musculos
        this.notifyDataSetChanged()
    }

}