package cl.littlephoenix.pokedex.presentation.pokemonlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cl.littlephoenix.pokedex.databinding.PokemonListFragmentBinding
import cl.littlephoenix.pokedex.presentation.model.PokemonModel
import cl.littlephoenix.pokedex.utils.Resource
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment() {
    private lateinit var binding: PokemonListFragmentBinding
    private val viewModel: PokemonListViewModel by activityViewModels()
    private val pokemonList = ArrayList<PokemonModel>()

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
        binding.bSearch.setOnClickListener {
            val text = binding.etSearch.text.toString()
            adapter.filter.filter(text)
        }
        binding.etSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.rvPokemon.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPokemon.adapter = adapter
        adapter.onItemClicked = { pokemon ->
            Log.d("Pokemon", Gson().toJson(pokemon))
            findNavController()
                .navigate(PokemonListFragmentDirections.goToPokemonDetails(pokemon = pokemon))
        }
        viewModel.getFirstGenPokemon().observe(viewLifecycleOwner, {
            when(it.status) {
                Resource.Status.LOADING -> showProgress()
                Resource.Status.ERROR -> {
                    hideProgress()
                    Log.e("Error", it.message ?: "error")
                }
                Resource.Status.SUCCESS -> {
                    pokemonList.clear()
                    pokemonList.addAll(it.data?: ArrayList())
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