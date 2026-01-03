package heaven.from.mywaifump.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import heaven.from.model.MyWaifuState
import heaven.from.model.WaifuModelV1
import heaven.from.repository.MyWaifuRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val myWaifuRepository: MyWaifuRepository
) : ViewModel() {
    private val _waifu = MutableStateFlow<MyWaifuState<List<WaifuModelV1>>>(MyWaifuState.Loading)
    val waifu = _waifu.asStateFlow()

    init {
        getWaifu()
    }

    fun getWaifu(amount: Int = 16) = viewModelScope.launch {
        myWaifuRepository.getNetworkWaifu(amount = amount).collect { value ->
            when (value) {
                is MyWaifuState.Loading -> {
                    _waifu.value = value
                }
                is MyWaifuState.Success -> {
                    _waifu.value = value
                }
                is MyWaifuState.Error -> {
                    _waifu.value = value
                }
            }
        }
    }
}
