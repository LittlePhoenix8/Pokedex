package cl.littlephoenix.pokedex.utils

import androidx.core.text.isDigitsOnly
import cl.littlephoenix.pokedex.data.api.PokedexApiService.Companion.BASE_IMG_URL
import java.lang.StringBuilder
import java.util.*

fun String.getIdFromUrl(): Int {
    val split = this.split("/")
    val number = split[split.size - 2]
    return if (number.isDigitsOnly()) number.toInt() else 0
}

fun String.getPhotoUrl(): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append(BASE_IMG_URL)
    stringBuilder.append(getIdFromUrl())
    stringBuilder.append(".png")
    return stringBuilder.toString()
}

fun Int.getPhotoUrl(): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append(BASE_IMG_URL)
    stringBuilder.append(this.toString())
    stringBuilder.append(".png")
    return stringBuilder.toString()
}

fun String.getNameUppercase(): String {
    return this.replace("-", " ").replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}