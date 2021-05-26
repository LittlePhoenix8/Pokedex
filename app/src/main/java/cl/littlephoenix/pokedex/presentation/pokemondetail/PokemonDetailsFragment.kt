package cl.littlephoenix.pokedex.presentation.pokemondetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import cl.littlephoenix.pokedex.databinding.PokemonDetailsFragmentBinding
import cl.littlephoenix.pokedex.presentation.model.PokemonModel
import cl.littlephoenix.pokedex.presentation.pokemonlist.PokemonListAdapter
import cl.littlephoenix.pokedex.utils.Resource
import coil.load
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
        setInfo()
        viewModel.getPokemonDetails(pokemon.id).observe(viewLifecycleOwner, {
            when(it.status) {
                Resource.Status.LOADING -> showProgress()
                Resource.Status.ERROR -> {
                    hideProgress()
                    Log.e("Error", it.message ?: "error")
                }
                Resource.Status.SUCCESS -> {
                    Log.d("PokeD", Gson().toJson(it.data))
                    if (it.data != null) {
                        pokemon = it.data
                        setInfo()
                    }
                    hideProgress()
                }
            }
        })
        viewModel.getPokemonSpecies(pokemon.id).observe(viewLifecycleOwner, {
            when(it.status) {
                Resource.Status.LOADING -> showProgress()
                Resource.Status.ERROR -> {
                    hideProgress()
                    Log.e("Error", it.message ?: "error")
                }
                Resource.Status.SUCCESS -> {
                    pokemon.evolutions = ArrayList(it.data)
                    pokemon.evolutions.firstOrNull()?.let { firstPoke ->
                        pokemon.chainId = firstPoke.chainId
                    }
                    setEvolutions()
                }
            }
        })
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
        binding.clPokemonDetail.visibility = View.GONE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.GONE
        binding.clPokemonDetail.visibility = View.VISIBLE
    }

    private fun setInfo() {
        binding.ivPokemon.load(pokemon.urlPhoto)
        binding.tvPokeName.text = pokemon.name
        binding.tvPokeNumber.text = pokemon.id.toString()
        binding.tvPokeType.text = pokemon.type.joinToString("/")
        binding.rvPokeAttacks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPokeAttacks.adapter = PokemonAttacksAdapter(ArrayList(pokemon.attacks))
        binding.rvPokeSkills.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPokeSkills.adapter = PokemonAttacksAdapter(ArrayList(pokemon.skills))
        Log.d("Skills", Gson().toJson(pokemon.chainId))
    }

    private fun setEvolutions() {
        Log.e("Evolves", Gson().toJson(pokemon.evolutions))
        //TODO evolutions adapter
    }
}