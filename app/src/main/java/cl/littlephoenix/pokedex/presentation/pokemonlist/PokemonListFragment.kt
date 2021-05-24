package cl.littlephoenix.pokedex.presentation.pokemonlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cl.littlephoenix.pokedex.data.entities.PokemonName
import cl.littlephoenix.pokedex.databinding.PokemonListFragmentBinding
import cl.littlephoenix.pokedex.helper.ResourceHelper
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
        /*lifecycleScope.launchWhenResumed {
            viewModel.getFirstGenPokemon()
        }*/
        viewModel.pokemonList.observe(viewLifecycleOwner, {
            when(it.status) {
                ResourceHelper.Status.LOADING -> showProgress()
                ResourceHelper.Status.ERROR -> {
                    hideProgress()
                    Log.e("Error", it.message!!)
                }
                ResourceHelper.Status.SUCCESS -> {
                    //pokemonList.addAll(it.data)
                    (binding.rvPokemon.adapter as PokemonListAdapter).notifyDataSetChanged()
                    hideProgress()
                }
            }
        })
        /*viewModel.pokemonList.observe(viewLifecycleOwner, {
            pokemonList.addAll(it.results)
            (binding.rvPokemon.adapter as PokemonListAdapter).notifyDataSetChanged()
            hideProgress()
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            hideProgress()
            Log.e("Error", it)
        })*/
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }
}