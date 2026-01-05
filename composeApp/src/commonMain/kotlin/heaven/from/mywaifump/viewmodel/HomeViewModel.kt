package heaven.from.mywaifump.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    private val amount: Int = 16
    private val _waifu = MutableStateFlow<MyWaifuState<List<WaifuModelV1>>>(MyWaifuState.Loading)
    val waifu = _waifu.asStateFlow()
    var isLoadingMore by mutableStateOf (false); private set

    init {
        getWaifu()
    }

    fun getWaifu(amount: Int = this.amount) = viewModelScope.launch {
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

    fun getMoreWaifu(amount: Int = this.amount) = viewModelScope.launch {
        myWaifuRepository.getNetworkWaifu(amount = amount).collect { value ->
            isLoadingMore = true

            when (value) {
                is MyWaifuState.Loading -> {}
                is MyWaifuState.Success -> {
                    val oldData = (_waifu.value as MyWaifuState.Success).data
                    val newData = oldData + value.data
                    _waifu.value = MyWaifuState.Success(data = newData)
                    isLoadingMore = false
                }
                is MyWaifuState.Error -> {
                    // Should we implement error message when fail to fetch more waifu?
                    isLoadingMore = false
                }
            }
        }
    }
}
