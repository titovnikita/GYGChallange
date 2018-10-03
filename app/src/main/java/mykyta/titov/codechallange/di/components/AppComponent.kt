package mykyta.titov.codechallange.di.components

import mykyta.titov.codechallange.di.modules.BuildersModule
import mykyta.titov.codechallange.di.modules.AppModule
import mykyta.titov.codechallange.di.modules.NetworkModule
import mykyta.titov.codechallange.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import mykyta.titov.codechallange.App
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    BuildersModule::class,
    ViewModelModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}