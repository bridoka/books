package com.ciandt.book.seeker.di

import com.ciandt.book.seeker.network.ItunesBookService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providesItunesBookService(): ItunesBookService {
        return Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(getOkHttp())
            .build()
            .create(ItunesBookService::class.java);
    }

    private fun getMoshi(): Moshi = Moshi.Builder().build()

    private fun getOkHttp(): OkHttpClient = OkHttpClient.Builder().build()
}