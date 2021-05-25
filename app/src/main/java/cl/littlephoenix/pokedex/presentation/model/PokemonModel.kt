package cl.littlephoenix.pokedex.presentation.model

import android.os.Parcel
import android.os.Parcelable
import cl.littlephoenix.pokedex.data.entities.PokemonEntity

data class PokemonModel (var id: Int,
                         var name: String,
                         var urlPhoto: String,
                         var type: List<String>): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(urlPhoto)
        parcel.writeStringList(type)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<PokemonModel> {
        override fun createFromParcel(parcel: Parcel): PokemonModel {
            return PokemonModel(parcel)
        }

        override fun newArray(size: Int): Array<PokemonModel?> {
            return arrayOfNulls(size)
        }
    }
}

fun PokemonEntity.toModel(): PokemonModel {
    return PokemonModel(id, name, photoUrl, listOf())
}