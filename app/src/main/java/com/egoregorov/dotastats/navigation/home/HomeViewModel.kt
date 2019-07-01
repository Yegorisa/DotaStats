package com.egoregorov.dotastats.navigation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.egoregorov.dotastats.models.DotaHeroSimple
import com.egoregorov.dotastats.network.OpenDotaAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class HomeViewModel constructor(val dotaAPI: OpenDotaAPI) : ViewModel() {

    private lateinit var getHeroesDisposable: Disposable

    val heroesList = MutableLiveData<List<DotaHeroSimple>>()


    init {
        loadData()
    }

    fun loadData() {
        getHeroesDisposable = dotaAPI.getHeroesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    onHeroesReceived(it)
                },
                onError = {
                    onHeroesRetrieveFailed()
                })
    }

    fun onHeroesReceived(heroes: List<DotaHeroSimple>) {
        heroesList.postValue(heroes)
    }

    fun onHeroesRetrieveFailed() {

    }

    override fun onCleared() {
        super.onCleared()
        getHeroesDisposable.dispose()
    }
}
