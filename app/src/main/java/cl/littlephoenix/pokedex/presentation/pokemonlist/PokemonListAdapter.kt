package cl.littlephoenix.pokedex.presentation.pokemonlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.littlephoenix.pokedex.R
import cl.littlephoenix.pokedex.data.api.PokedexApiService.Companion.BASE_IMG_URL
import cl.littlephoenix.pokedex.data.model.PokemonName
import cl.littlephoenix.pokedex.databinding.PokemonListItemBinding
import cl.littlephoenix.pokedex.presentation.model.PokemonModel
import coil.load
import java.util.*
import kotlin.collections.ArrayList

class PokemonListAdapter(
    private val pokemonList: ArrayList<PokemonModel>): RecyclerView.Adapter<ViewHolder>() {
    var onItemClicked: ((PokemonModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PokemonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.binding.tvPokeName.text = pokemon.name
        holder.binding.tvPokeNumber.text = holder.binding.tvPokeNumber.context.getString(R.string.poke_number, pokemon.id.toString())
        holder.binding.ivPokemon.load(pokemon.urlPhoto)
        holder.binding.root.setOnClickListener {
            onItemClicked?.invoke(pokemon)
        }
    }

    override fun getItemCount(): Int = pokemonList.size
}

class ViewHolder(var binding: PokemonListItemBinding) : RecyclerView.ViewHolder(binding.root)
