package com.example.dogs.breeds.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class SplashViewModel: ViewModel() {
    private val _percentageLD = MutableLiveData<Int>()
    val percentageLD: LiveData<Int> = _percentageLD

    fun startCountDown(seconds: Long){
        if (seconds > 0) {
            viewModelScope.launch {
                var tmpSeconds = seconds

                val ticker = ticker(1000, 0, this.coroutineContext)
                ticker.consumeAsFlow()
                    .collect(object : FlowCollector<Unit>{
                        override suspend fun emit(value: Unit) {
                            tmpSeconds -= 1
                            Log.e("TAG","$seconds - $tmpSeconds")
                            if (tmpSeconds.toInt() == 0) {
                                ticker.cancel()
                                postPercentage(seconds, 0)
                            }else{
                                postPercentage(seconds, tmpSeconds)
                            }
                        }
                    })
            }
        }
    }

    fun postPercentage(totalSeconds: Long, remainSeconds: Long){
        val percentage = (totalSeconds - remainSeconds).toDouble()/totalSeconds * 100
        _percentageLD.postValue(percentage.toInt())

    }
}