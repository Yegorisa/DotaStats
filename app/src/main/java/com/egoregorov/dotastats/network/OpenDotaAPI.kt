package com.egoregorov.dotastats.network

import com.egoregorov.dotastats.models.DotaHeroSimple
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface OpenDotaAPI {
    /**
     * Get the list of the pots from the API
     */
    @GET("heroes")
    fun getHeroesList(): Observable<List<DotaHeroSimple>>
}