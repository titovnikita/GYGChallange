package mykyta.titov.codechallange.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import mykyta.titov.codechallange.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
            okHttpClient: OkHttpClient,
            rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
            gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .baseUrl("https://www.getyourguide.com/")
                .build()
    }

    @Provides
    fun provideRetrofitBuilder(
            okHttpClient: OkHttpClient,
            rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
            gsonConverterFactory: GsonConverterFactory
    ): Retrofit.Builder {

        return Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
    }

    @Provides
    @Singleton
    fun providesOkHttpClientBuilder(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            @Named(KEY_CONNECT_TIMEOUT) connectTimeout: Long,
            @Named(KEY_READ_TIMEOUT) readTimeout: Long
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            connectTimeout(connectTimeout, TimeUnit.SECONDS)
            readTimeout(readTimeout, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                addInterceptor(httpLoggingInterceptor)
            }
        }
    }

    @Provides
    @Singleton
    fun provideRxJavaAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder): OkHttpClient =
            okHttpClientBuilder.build()

    @Provides
    @Named(KEY_CONNECT_TIMEOUT)
    fun provideConnectTimeout(): Long = 10

    @Provides
    @Named(KEY_READ_TIMEOUT)
    fun provideReadTimeout(): Long = 10
}

const val KEY_CONNECT_TIMEOUT = "CONNECT_TIMEOUT"
const val KEY_READ_TIMEOUT = "READ_TIMEOUT"