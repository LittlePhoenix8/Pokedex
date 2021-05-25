package cl.littlephoenix.pokedex.presentation.pokemondetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import cl.littlephoenix.pokedex.databinding.PokemonDetailsFragmentBinding
import cl.littlephoenix.pokedex.presentation.model.PokemonModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment() {
    private lateinit var binding: PokemonDetailsFragmentBinding
    private lateinit var pokemon: PokemonModel
    private val viewModel: PokemonDetailsViewModel by activityViewModels()
    private val args: PokemonDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PokemonDetailsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgress()
        pokemon = args.pokemon
        Log.d("PokeDetails", Gson().toJson(pokemon))

        binding.tvPokeName.text = pokemon.name
        binding.tvPokeNumber.text = pokemon.id.toString()
        binding.tvPokeType.text = pokemon.type.joinToString("/")

    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
        binding.clPokemonDetail.visibility = View.GONE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.GONE
        binding.clPokemonDetail.visibility = View.VISIBLE
    }
}