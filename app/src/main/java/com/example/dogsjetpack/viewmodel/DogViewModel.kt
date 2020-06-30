package com.example.dogsjetpack.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.dogsjetpack.database.DogDatabase
import com.example.dogsjetpack.model.DogModel
import kotlinx.coroutines.launch

class DogViewModel(application: Application) : BaseViewModel(application) {
    var dog = MutableLiveData<DogModel>()

    // Set data to dog what is live data
    fun fetch(uuid: Int) {
        launch {
            var dogItem = DogDatabase(getApplication()).dao().getDog(uuid)
            dogItem?.let {
                dog.value = dogItem
            }
        }
    }
}