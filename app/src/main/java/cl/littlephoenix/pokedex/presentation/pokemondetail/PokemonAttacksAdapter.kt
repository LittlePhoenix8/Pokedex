package cl.littlephoenix.pokedex.presentation.pokemondetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.littlephoenix.pokedex.R
import cl.littlephoenix.pokedex.databinding.PokemonAttacksItemBinding

class PokemonAttacksAdapter(private val attacks: ArrayList<String>): RecyclerView.Adapter<AttackViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttackViewHolder {
        val binding = PokemonAttacksItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttackViewHolder, position: Int) {
        holder.binding.tvPokeAttack.text =
            holder.binding.tvPokeAttack.context.getString(R.string.attack, attacks[position])
    }

    override fun getItemCount(): Int = attacks.size
}

class AttackViewHolder(var binding: PokemonAttacksItemBinding): RecyclerView.ViewHolder(binding.root)