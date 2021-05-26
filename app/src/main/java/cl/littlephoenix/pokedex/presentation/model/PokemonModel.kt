package cl.littlephoenix.pokedex.presentation.model

import android.os.Parcel
import android.os.Parcelable
import cl.littlephoenix.pokedex.data.entities.PokemonEntity

data class PokemonModel (var id: Int,
                         var name: String,
                         var urlPhoto: String,
                         var type: List<String>,
                         var attacks: List<String>,
                         var skills: List<String>): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(urlPhoto)
        parcel.writeStringList(type)
        parcel.writeStringList(attacks)
        parcel.writeStringList(skills)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonModel> {
        override fun createFromParcel(parcel: Parcel): PokemonModel {
            return PokemonModel(parcel)
        }

        override fun newArray(size: Int): Array<PokemonModel?> {
            return arrayOfNulls(size)
        }
    }
}

//TODO update
fun PokemonEntity.toModel(): PokemonModel {
    return PokemonModel(id, name, photoUrl, listOf(), listOf(), listOf())
}