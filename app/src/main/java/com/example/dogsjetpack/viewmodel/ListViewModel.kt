package com.example.dogsjetpack.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dogsjetpack.database.DogDatabase
import com.example.dogsjetpack.database.MyPreferences
import com.example.dogsjetpack.model.DogModel
import com.example.dogsjetpack.service.DogApiAdapter
import com.example.dogsjetpack.util.NotificationHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class ListViewModel(application: Application): BaseViewModel(application) {

    // 5 minutes as nanotime
    private var updateTime: Long = 5 * 60 * 1000 * 1000 * 1000L

    var dogList = MutableLiveData<List<DogModel>>()

    private var disposable = CompositeDisposable()
    private var dogApiAdapter = DogApiAdapter()
    private var myPreferences = MyPreferences(getApplication())

    private var notificationHelper = NotificationHelper(getApplication())

    fun setList() {
        cacheTime()
        Log.e("Error", "" + updateTime)
        var savedTime = myPreferences.getTime()
        if (savedTime != null && savedTime >= 0L && System.nanoTime() - savedTime < updateTime) {
            fetchFromRoomDatabase()
            Log.e("Error", "From room")
        }else {
            fetchFromRemote()
        }
    }

    private fun cacheTime() {
        var cachedTime = myPreferences.getCacheTime()
        try {
            var cachePreferenceInt = cachedTime?.toInt() ?: 5 * 60
            updateTime = cachePreferenceInt.times( 1000 * 1000 * 1000L)
        }catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }

    // Get data from api using retrofit
    private fun fetchFromRemote() {
        disposable.add(
            dogApiAdapter.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogModel>>() {
                    override fun onSuccess(t: List<DogModel>) {
                        storeAllDataToRoomDatabase(t)
                        notificationHelper.sendNotification()
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Error", e.message)
                    }
                })
        )
    }

    // Update dogList what is live data
    private fun updateList(t: List<DogModel>) {
        dogList.value = t
    }

    // Store datas to room database
    private fun storeAllDataToRoomDatabase(t: List<DogModel>) {
        launch {
            var dao = DogDatabase(getApplication()).dao()
            dao.deleteAllDogs()
            var list = dao.insertDogs(*t.toTypedArray())
            var i = 0
            while (i < t.size) {
                t[i].uuid = list[i].toInt()
                i++
            }
            updateList(t)
            myPreferences.saveTime(System.nanoTime())
        }
    }

    // Get datas from room database
    private fun fetchFromRoomDatabase() {
        launch {
            var dao = DogDatabase(getApplication()).dao()
            dogList.value = dao.getAllDogs()
        }
    }
}