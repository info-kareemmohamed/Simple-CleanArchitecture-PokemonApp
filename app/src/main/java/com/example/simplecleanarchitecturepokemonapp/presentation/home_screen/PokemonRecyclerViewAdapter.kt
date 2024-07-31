package com.example.simplecleanarchitecturepokemonapp.presentation.home_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecleanarchitecturepokemonapp.databinding.PokemonCardRecyclerBinding
import com.example.simplecleanarchitecturepokemonapp.domain.model.Pokemon

class PokemonRecyclerViewAdapter(private var pokemonList: List<Pokemon>, private val onClickCardListener: OnClickCardListener) :
    RecyclerView.Adapter<PokemonRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(private val binding: PokemonCardRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(pokemon: Pokemon,onClickCardListener: OnClickCardListener) {
            binding.pokemon = pokemon
            binding.cardView.setOnClickListener {
                onClickCardListener.onClick(pokemon)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PokemonCardRecyclerBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }

        }
    }


    fun updateList(newList: List<Pokemon>) {
        pokemonList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(pokemonList[position],onClickCardListener)


    }

interface  OnClickCardListener{
    fun onClick(pokemon: Pokemon)
}
