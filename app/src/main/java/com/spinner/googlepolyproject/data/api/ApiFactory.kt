package com.spinner.googlepolyproject.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class ApiFactory {

    class ApiInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("key", API_KEY)
                .build()

            val requestBuilder= original.newBuilder()
                .url(url)

            val request= requestBuilder.build()
            return chain.proceed(request)
        }
    }

    companion object Factory {
        const val BASE_URL = "https://poly.googleapis.com/"
        const val API_KEY = "AIzaSyBSDDKwvZrgNy1-iQj80E07JM5E1GkMsU8"

        private fun createOkHttpClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
            client.addInterceptor(ApiInterceptor())
            return client.build()
        }

        private fun createRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createOkHttpClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        fun createApiService(): AssetsApi = createRetrofit().create(AssetsApi::class.java)
    }
}