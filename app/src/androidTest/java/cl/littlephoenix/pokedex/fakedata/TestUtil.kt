package cl.littlephoenix.pokedex.fakedata

import cl.littlephoenix.pokedex.data.entities.PokemonEntity

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
    }
}