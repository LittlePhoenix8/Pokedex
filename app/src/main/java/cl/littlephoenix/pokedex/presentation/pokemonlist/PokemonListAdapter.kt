package cl.littlephoenix.pokedex.presentation.pokemonlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.littlephoenix.pokedex.R
import cl.littlephoenix.pokedex.data.api.PokedexApiService.Companion.BASE_IMG_URL
import cl.littlephoenix.pokedex.data.model.PokemonName
import cl.littlephoenix.pokedex.databinding.PokemonListItemBinding
import coil.load
import java.util.*
import kotlin.collections.ArrayList

class PokemonListAdapter(
    private val pokemonList: ArrayList<PokemonName>): RecyclerView.Adapter<ViewHolder>() {
    var onItemClicked: ((PokemonName) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PokemonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        val pokeName = pokemon.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
        val pokeNumber = pokemon.url.split("/")
        holder.binding.tvPokeName.text = pokeName
        holder.binding.tvPokeNumber.text = holder.binding.tvPokeNumber.context.getString(R.string.poke_number, pokeNumber[pokeNumber.size - 2])
        holder.binding.ivPokemon.load("${BASE_IMG_URL}${pokeNumber[pokeNumber.size - 2]}.png")
        holder.binding.root.setOnClickListener {
            onItemClicked?.invoke(pokemon)
        }
    }

    override fun getItemCount(): Int = pokemonList.size
}

class ViewHolder(var binding: PokemonListItemBinding) : RecyclerView.ViewHolder(binding.root)
