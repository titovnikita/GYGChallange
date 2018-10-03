package mykyta.titov.codechallange.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import mykyta.titov.codechallange.App
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context = app.applicationContext
}