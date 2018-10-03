package mykyta.titov.codechallange.network.base

import retrofit2.Retrofit
import javax.inject.Inject

abstract class BaseRemoteDataSource {

    @Inject
    open lateinit var retrofit: Retrofit

    internal fun <S> createService(clazz: Class<S>) = retrofit.create(clazz)
}