package com.example.batmanproject.di

import com.example.batmanproject.api.BatmanApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =  Retrofit.Builder()
        .baseUrl(BatmanApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideBatmanApi(retrofit: Retrofit) : BatmanApi =
        retrofit.create(BatmanApi::class.java)
}