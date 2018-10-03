package mykyta.titov.codechallange.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mykyta.titov.codechallange.ui.features.reviews.ReviewsActivity

@Suppress("unused")
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): ReviewsActivity
}