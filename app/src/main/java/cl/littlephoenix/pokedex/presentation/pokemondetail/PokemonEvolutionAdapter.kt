package cl.littlephoenix.pokedex.presentation.pokemondetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.littlephoenix.pokedex.databinding.PokemonEvolutionItemBinding
import cl.littlephoenix.pokedex.presentation.model.PokemonModel
import coil.load

class PokemonEvolutionAdapter(private val evolutions: ArrayList<PokemonModel>): RecyclerView.Adapter<EvolutionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvolutionViewHolder {
        val binding = PokemonEvolutionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EvolutionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EvolutionViewHolder, position: Int) {
        //TODO mark current pokemon
        //TODO pokemon with no evols
        val pokemon = evolutions[position]
        holder.binding.ivPokemon.load(pokemon.urlPhoto)
        holder.binding.tvPokemon.text = pokemon.name
        if (position == evolutions.size - 1) {
            holder.binding.ivNextPokemon.visibility = View.GONE
        } else {
            holder.binding.ivNextPokemon.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int = evolutions.size
}

class EvolutionViewHolder(var binding: PokemonEvolutionItemBinding): RecyclerView.ViewHolder(binding.root)