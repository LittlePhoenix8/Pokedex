package cl.littlephoenix.pokedex.presentation.pokemonlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import cl.littlephoenix.pokedex.R
import cl.littlephoenix.pokedex.databinding.PokemonListItemBinding
import cl.littlephoenix.pokedex.presentation.model.PokemonModel
import coil.load
import kotlin.collections.ArrayList

class PokemonListAdapter(
    private val pokemonList: ArrayList<PokemonModel>): RecyclerView.Adapter<ViewHolder>(), Filterable {
    var pokemonFilterList = ArrayList<PokemonModel>()
    var onItemClicked: ((PokemonModel) -> Unit)? = null
    var onEndOfListReached: (() -> Unit)? = null

    init {
        pokemonFilterList = pokemonList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PokemonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonFilterList[position]
        holder.binding.tvPokeName.text = pokemon.name
        holder.binding.tvPokeNumber.text = holder.binding.tvPokeNumber.context.getString(R.string.poke_number, pokemon.id.toString())
        holder.binding.ivPokemon.load(pokemon.urlPhoto)
        holder.binding.root.setOnClickListener {
            onItemClicked?.invoke(pokemon)
        }
        if (position == pokemonFilterList.size - 1) {
            onEndOfListReached?.invoke()
        }
    }

    override fun getItemCount(): Int = pokemonFilterList.size

    override fun getFilter(): Filter = object: Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val text = constraint.toString()
            if (text.isEmpty()) {
                pokemonFilterList = pokemonList
            } else {
                pokemonFilterList = ArrayList()
                pokemonFilterList.addAll(pokemonList.filter { it.name.contains(text, true) })
            }
            val filter = FilterResults()
            filter.values = pokemonFilterList
            return filter
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            pokemonFilterList = results?.values as ArrayList<PokemonModel>
            notifyDataSetChanged()
        }
    }
}

class ViewHolder(var binding: PokemonListItemBinding) : RecyclerView.ViewHolder(binding.root)
