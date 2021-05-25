package cl.littlephoenix.pokedex.presentation.pokemonlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import cl.littlephoenix.pokedex.data.model.PokemonName
import cl.littlephoenix.pokedex.databinding.PokemonListFragmentBinding
import cl.littlephoenix.pokedex.utils.Resource
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment() {
    private lateinit var binding: PokemonListFragmentBinding
    private val viewModel: PokemonListViewModel by activityViewModels()
    private val pokemonList = ArrayList<PokemonName>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PokemonListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PokemonListAdapter(pokemonList)
        binding.rvPokemon.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPokemon.adapter = adapter
        adapter.onItemClicked = { pokemon ->
            Log.d("Pokemon", Gson().toJson(pokemon))
            //TODO: go to pokemon details
        }
        viewModel.getFirstGenPokemon().observe(viewLifecycleOwner, {
            when(it.status) {
                Resource.Status.LOADING -> showProgress()
                Resource.Status.ERROR -> {
                    hideProgress()
                    Log.e("Error", it.message ?: "error")
                }
                Resource.Status.SUCCESS -> {
                    pokemonList.addAll(it.data?.results ?: ArrayList())
                    (binding.rvPokemon.adapter as PokemonListAdapter).notifyDataSetChanged()
                    hideProgress()
                }
            }
        })
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }
}