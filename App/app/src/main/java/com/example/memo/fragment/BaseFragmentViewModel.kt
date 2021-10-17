package com.example.memo.fragment

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

/**
 * FragmentのViewModelの基底クラス
 * このアプリで実装するFragmentのViewModelは全てこのBaseFragmentを継承させる
 */
abstract class BaseFragmentViewModel : ViewModel() {
    val navDirections: LiveData<NavDirections>

    private val _navDirections = MutableSharedFlow<NavDirections>()

    init {
        navDirections = _navDirections.asLiveData(viewModelScope.coroutineContext)
    }

    protected suspend fun emit(navDirections: NavDirections) {
        _navDirections.emit(navDirections)
    }

    /**
     * SavedStateHandleをMutableLiveDataではなくMutableStateFlowで取得する
     */
    protected fun <T : Parcelable> SavedStateHandle.getStateFlow(
        initialValue: T
    ): MutableStateFlow<T> {
        val liveData = getLiveData(KEY_STATE, initialValue)
        val stateFlow = MutableStateFlow(initialValue)

        val observer = Observer<T> { value ->
            if (value != stateFlow.value) {
                stateFlow.value = value
            }
        }
        liveData.observeForever(observer)

        stateFlow.onCompletion {
            withContext(Dispatchers.Main.immediate) {
                liveData.removeObserver(observer)
            }
        }.onEach { value ->
            withContext(Dispatchers.Main.immediate) {
                if (liveData.value != value) {
                    liveData.value = value
                }
            }
        }.launchIn(viewModelScope)

        return stateFlow
    }

    companion object {
        private const val KEY_STATE = "key_state"
    }
}
