package cl.littlephoenix.pokedex.data.model

data class PokemonEvolutionResponse(var chain: ChainEvolution)

data class ChainEvolution(var species: Species, var evolves_to: List<EvolutionInfo>)

data class EvolutionInfo(var species: Species, var evolves_to: List<EvolutionInfo>)
