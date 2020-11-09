package com.example.opeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class TreinoAdapter(
    val treinos: List<Treino>,
    val onClick: (Treino) -> Unit
): RecyclerView.Adapter<TreinoAdapter.TreinoViewHolder>() {

    class TreinoViewHolder(view: View): RecyclerView.ViewHolder(view){
        val cardNome: TextView
        val cardImg: ImageView
        val cardProgress: ProgressBar

        init {
            cardNome = view.findViewById(R.id.cardNome)
            cardImg = view.findViewById(R.id.cardImg)
            cardProgress = view.findViewById(R.id.cardProgress)
        }
    }

    override fun getItemCount() = this.treinos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreinoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_treino, parent, false)

        val holder = TreinoViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: TreinoViewHolder, posicao: Int) {
        val treino = this.treinos[posicao]

        holder.cardNome.text = treino.nome
        holder.cardProgress.visibility = View.VISIBLE

        Picasso.with(holder.itemView.context).load(treino.foto).fit().into(holder.cardImg,
            object: com.squareup.picasso.Callback{
                override fun onSuccess() {
                     holder.cardProgress.visibility = View.GONE
                }

                override fun onError() {
                    holder.cardProgress.visibility = View.GONE
                }
            })

        holder.itemView.setOnClickListener{onClick(treino)}


    }
}