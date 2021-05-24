package cl.littlephoenix.pokedex.helper

data class ResourceHelper<out T>(val status: Status, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS, LOADING, ERROR
    }
    companion object {
        fun <T> success(data: T): ResourceHelper<T> {
            return ResourceHelper(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): ResourceHelper<T> {
            return ResourceHelper(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): ResourceHelper<T> {
            return ResourceHelper(Status.LOADING, data, null)
        }
    }
}