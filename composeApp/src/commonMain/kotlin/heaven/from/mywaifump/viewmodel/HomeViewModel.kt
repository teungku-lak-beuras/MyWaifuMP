package heaven.from.mywaifump.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import heaven.from.model.ApiState
import heaven.from.model.WaifuModelV1
import heaven.from.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _waifu = MutableStateFlow<ApiState<List<WaifuModelV1>>>(ApiState.Loading)
    val waifu = _waifu.asStateFlow()

    init {
        getWaifu()
    }

    fun getWaifu(amount: Int = 16) = viewModelScope.launch {
        repository.getNetworkWaifu(amount = amount).collect { value ->
            when (value) {
                is ApiState.Loading -> {
                    _waifu.value = value
                }
                is ApiState.Success -> {
                    _waifu.value = value
                }
                is ApiState.Error -> {
                    _waifu.value = value
                }
            }
        }
    }
}
