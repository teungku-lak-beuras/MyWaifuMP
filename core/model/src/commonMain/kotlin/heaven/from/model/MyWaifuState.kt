package heaven.from.model

sealed class MyWaifuState<out R> {
    data class Success<out T>(val data: T) : MyWaifuState<T>()
    data class Error(val message: String) : MyWaifuState<Nothing>()
    object Loading : MyWaifuState<Nothing>()
}
