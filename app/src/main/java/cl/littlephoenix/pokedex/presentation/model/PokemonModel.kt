package cl.littlephoenix.pokedex.presentation.model

import android.os.Parcel
import android.os.Parcelable
import cl.littlephoenix.pokedex.data.entities.PokemonEntity

data class PokemonModel (var id: Int,
                         var name: String,
                         var urlPhoto: String,
                         var type: List<String>,
                         var attacks: List<String>,
                         var skills: List<String>,
                         var chainId: Int,
                         var evolutions: List<PokemonModel>,
                         var locations: List<String>): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.readInt(),
        parcel.createTypedArrayList(CREATOR)!!,
        parcel.createStringArrayList()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(urlPhoto)
        parcel.writeStringList(type)
        parcel.writeStringList(attacks)
        parcel.writeStringList(skills)
        parcel.writeInt(chainId)
        parcel.writeTypedList(evolutions)
        parcel.writeStringList(locations)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<PokemonModel> {
        override fun createFromParcel(parcel: Parcel): PokemonModel = PokemonModel(parcel)
        override fun newArray(size: Int): Array<PokemonModel?> = arrayOfNulls(size)
    }
}
fun PokemonEntity.toModel(): PokemonModel {
    return PokemonModel(id = id,
        name = name,
        urlPhoto = photoUrl,
        type = listOf(),
        attacks = listOf(),
        skills = listOf(),
        chainId = -1,
        evolutions = listOf(),
        locations = listOf())
}