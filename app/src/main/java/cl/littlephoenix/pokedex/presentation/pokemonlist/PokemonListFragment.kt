package cl.littlephoenix.pokedex.presentation.pokemonlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import cl.littlephoenix.pokedex.databinding.PokemonListFragmentBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment() {
    private lateinit var binding: PokemonListFragmentBinding
    private val viewModel: PokemonListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PokemonListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            viewModel.getFirstGenPokemon()
        }
        viewModel.pokemonList.observe(viewLifecycleOwner, {
            Log.d("PokemonList", Gson().toJson(it.results))
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Log.e("Error", it)
        })
    }
}