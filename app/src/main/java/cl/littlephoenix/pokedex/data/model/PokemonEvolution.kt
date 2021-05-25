package cl.littlephoenix.pokedex.data.entities

data class PokemonEvolution(var chain: ChainEvol)

data class ChainEvol(var evolves_to: List<EvolutionInfo>)

data class EvolutionInfo(var is_baby: Boolean, var evolves_to: List<EvolTo>)

data class EvolTo(var species: Species, var evolves_to: List<EvolutionInfo>)