package mykyta.titov.codechallange.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import mykyta.titov.codechallange.core.ViewModelFactory
import mykyta.titov.codechallange.core.ViewModelKey
import mykyta.titov.codechallange.ui.features.reviews.ReviewsViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {

    // Bind each ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(ReviewsViewModel::class)
    abstract fun bindMainActivityViewModel(reviewsViewModel: ReviewsViewModel): ViewModel

    // ViewModel factory binding
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}