package cl.littlephoenix.pokedex.fakedata

import cl.littlephoenix.pokedex.data.entities.*
import cl.littlephoenix.pokedex.data.model.*

class TestUtil {
    companion object {
        fun getPokemon(): List<PokemonEntity> {
            val list = ArrayList<PokemonEntity>()
            list.add(PokemonEntity(1, "Bulbasaur", "https://pokeres.bastionbot.org/images/pokemon/1.png", 1))
            list.add(PokemonEntity(2, "Ivysaur", "https://pokeres.bastionbot.org/images/pokemon/2.png", 1))
            list.add(PokemonEntity(3, "Venusaur", "https://pokeres.bastionbot.org/images/pokemon/3.png", 1))
            list.add(PokemonEntity(4, "Charmander", "https://pokeres.bastionbot.org/images/pokemon/4.png", 2))
            list.add(PokemonEntity(5, "Charmeleon", "https://pokeres.bastionbot.org/images/pokemon/5.png", 2))
            list.add(PokemonEntity(6, "Charizard", "https://pokeres.bastionbot.org/images/pokemon/6.png", 2))
            return list
        }

        fun getPokemonEmpty(): List<PokemonEntity> {
            return ArrayList()
        }

        fun getPokemonWithoutChainId(): List<PokemonEntity> {
            val list = ArrayList<PokemonEntity>()
            list.add(PokemonEntity(1, "Bulbasaur", "https://pokeres.bastionbot.org/images/pokemon/1.png", -1))
            list.add(PokemonEntity(2, "Ivysaur", "https://pokeres.bastionbot.org/images/pokemon/2.png", -1))
            list.add(PokemonEntity(3, "Venusaur", "https://pokeres.bastionbot.org/images/pokemon/3.png", -1))
            list.add(PokemonEntity(4, "Charmander", "https://pokeres.bastionbot.org/images/pokemon/4.png", -1))
            list.add(PokemonEntity(5, "Charmeleon", "https://pokeres.bastionbot.org/images/pokemon/5.png", -1))
            list.add(PokemonEntity(6, "Charizard", "https://pokeres.bastionbot.org/images/pokemon/6.png", -1))
            return list
        }

        fun getLocations(): List<LocationEntity> {
            val list = ArrayList<LocationEntity>()
            list.add(LocationEntity(1, 1, "Cerulean City Area"))
            list.add(LocationEntity(2, 1, "Pallet Town Area"))
            return list
        }

        fun getLocationsEmpty(): List<LocationEntity> {
            return ArrayList()
        }

        fun getAttacks(): List<AttackEntity> {
            val list = ArrayList<AttackEntity>()
            list.add(AttackEntity(1, 1, "Razor Wind"))
            list.add(AttackEntity(2, 1, "Swords Dance"))
            return list
        }

        fun getAttacksEmpty(): List<AttackEntity> {
            return ArrayList()
        }

        fun getSkill(): List<SkillEntity> {
            val list = ArrayList<SkillEntity>()
            list.add(SkillEntity(1, 1, "Overgrow"))
            list.add(SkillEntity(2, 1, "Chlorophyll"))
            return list
        }

        fun getSkillEmpty(): List<SkillEntity> {
            return ArrayList()
        }

        fun getEvolutions(): List<PokemonEvolutionEntity> {
            val list = ArrayList<PokemonEvolutionEntity>()
            list.add(PokemonEvolutionEntity(1, 1, "Bulbasaur", "https://pokeres.bastionbot.org/images/pokemon/1.png"))
            list.add(PokemonEvolutionEntity(2, 1, "Ivysaur", "https://pokeres.bastionbot.org/images/pokemon/2.png"))
            list.add(PokemonEvolutionEntity(3, 1, "Venusaur", "https://pokeres.bastionbot.org/images/pokemon/3.png"))
            return list
        }

        fun getEvolutionsEmpty(): List<PokemonEvolutionEntity> {
            return ArrayList()
        }

        fun getPokemonTypeCrossRef(): List<PokemonTypeCrossRefEntity> {
            val list = ArrayList<PokemonTypeCrossRefEntity>()
            list.add(PokemonTypeCrossRefEntity(1, 12))
            list.add(PokemonTypeCrossRefEntity(1, 4))
            return list
        }

        fun getPokemonTypeCrossRefEmpty(): List<PokemonTypeCrossRefEntity> {
            return ArrayList()
        }

        fun getTypes(): List<TypeEntity> {
            val list = ArrayList<TypeEntity>()
            list.add(TypeEntity(12, "Grass"))
            list.add(TypeEntity(4, "Poison"))
            return list
        }

        fun getTypesEmpty(): List<TypeEntity> {
            return ArrayList()
        }

        private fun getPokemonNameList(): List<PokemonName> {
            val list = ArrayList<PokemonName>()
            list.add(PokemonName("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"))
            list.add(PokemonName("ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"))
            list.add(PokemonName("venusaur", "https://pokeapi.co/api/v2/pokemon/3/"))
            return list
        }

        fun getPokemonResponse(): PokemonResponse {
            return PokemonResponse(getPokemonNameList())
        }

        fun getPokemonInfoResponse(): PokemonInfoResponse {
            val types = ArrayList<PokemonTypes>()
            types.add(PokemonTypes(1, Type("grass", "https://pokeapi.co/api/v2/type/12/")))
            types.add(PokemonTypes(2, Type("poison", "https://pokeapi.co/api/v2/type/4/")))
            val species = Species("bulbasaur", "https://pokeapi.co/api/v2/pokemon-species/1/")
            val moves = ArrayList<Moves>()
            moves.add(Moves(Move("razor-wind")))
            moves.add(Moves(Move("swords-dance")))
            val abilities = ArrayList<Abilities>()
            abilities.add(Abilities(Ability("overgrow")))
            abilities.add(Abilities(Ability("chlorophyll")))
            return PokemonInfoResponse(1, "bulbasaur", types, species, moves, abilities,
                "https://pokeapi.co/api/v2/pokemon/1/encounters")
        }

        fun getPokemonSpecieResponse(): PokemonSpecieResponse {
            return PokemonSpecieResponse(EvolutionChain("https://pokeapi.co/api/v2/evolution-chain/1/"))
        }

        fun getPokemonEvolutionResponse(): PokemonEvolutionResponse {
            val species = Species("bulbasaur", "https://pokeapi.co/api/v2/pokemon-species/1/")
            val list = ArrayList<EvolutionInfo>()
            list.add(EvolutionInfo(Species("ivysaur", "https://pokeapi.co/api/v2/pokemon-species/2/"),
                listOf(EvolutionInfo(Species("venusaur", "https://pokeapi.co/api/v2/pokemon-species/3/"), listOf()))))
            return PokemonEvolutionResponse(ChainEvolution(species, list))
        }

        fun getArrayPokemonLocationsResponse(): Array<PokemonLocationsResponse> {
            val list = ArrayList<PokemonLocationsResponse>()
            list.add(PokemonLocationsResponse(LocationArea("cerulean-city-area")))
            list.add(PokemonLocationsResponse(LocationArea("pallet-town-area")))
            return list.toTypedArray()
        }

        fun getTypeResponse(): TypeResponse {
            val list = ArrayList<Type>()
            list.add(Type("grass", "https://pokeapi.co/api/v2/type/12/"))
            list.add(Type("poison", "https://pokeapi.co/api/v2/type/4/"))
            return TypeResponse(list)
        }
    }
}