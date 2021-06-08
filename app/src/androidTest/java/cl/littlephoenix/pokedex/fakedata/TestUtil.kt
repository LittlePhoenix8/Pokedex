package cl.littlephoenix.pokedex.fakedata

import cl.littlephoenix.pokedex.data.entities.*

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

        fun getAttacks(): List<AttackEntity> {
            val list = ArrayList<AttackEntity>()
            list.add(AttackEntity(1, 1, "Razor Wind"))
            list.add(AttackEntity(2, 1, "Swords Dance"))
            return list
        }

        fun getSkill(): List<SkillEntity> {
            val list = ArrayList<SkillEntity>()
            list.add(SkillEntity(1, 1, "Overgrow"))
            list.add(SkillEntity(2, 1, "Chlorophyll"))
            return list
        }

        fun getEvolutions(): List<PokemonEvolutionEntity> {
            val list = ArrayList<PokemonEvolutionEntity>()
            list.add(PokemonEvolutionEntity(1, 1, "Bulbasaur", "https://pokeres.bastionbot.org/images/pokemon/1.png"))
            list.add(PokemonEvolutionEntity(2, 1, "Ivysaur", "https://pokeres.bastionbot.org/images/pokemon/2.png"))
            list.add(PokemonEvolutionEntity(3, 1, "Venusaur", "https://pokeres.bastionbot.org/images/pokemon/3.png"))
            return list
        }

        fun getPokemonTypeCrossRef(): List<PokemonTypeCrossRefEntity> {
            val list = ArrayList<PokemonTypeCrossRefEntity>()
            list.add(PokemonTypeCrossRefEntity(1, 12))
            list.add(PokemonTypeCrossRefEntity(1, 4))
            return list
        }

        fun getTypes(): List<TypeEntity> {
            val list = ArrayList<TypeEntity>()
            list.add(TypeEntity(12, "Grass"))
            list.add(TypeEntity(4, "Poison"))
            return list
        }
    }
}