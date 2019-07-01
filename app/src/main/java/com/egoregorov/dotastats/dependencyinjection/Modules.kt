package com.egoregorov.dotastats.dependencyinjection

import com.egoregorov.dotastats.navigation.NavigationViewModel
import com.egoregorov.dotastats.navigation.home.HomeFragment
import com.egoregorov.dotastats.navigation.home.HomeViewModel
import com.egoregorov.dotastats.network.OpenDotaAPI
import com.egoregorov.dotastats.utils.OPEN_API_URL
import com.squareup.moshi.Moshi
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val viewModelsModule = module {
    // MyViewModel ViewModel
    viewModel { NavigationViewModel() }
    viewModel { HomeViewModel(get()) }
    factory { HomeFragment() }
}
val networkModules = module {
    factory <OpenDotaAPI>{provideRetrofitInterface().create(OpenDotaAPI::class.java) }
}

fun provideRetrofitInterface() : Retrofit{
    return Retrofit.Builder()
        .baseUrl(OPEN_API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
}