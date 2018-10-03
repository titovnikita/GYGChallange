package mykyta.titov.codechallange.ui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import mykyta.titov.codechallange.core.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity<out T : BaseViewModel> : AppCompatActivity(), HasSupportFragmentInjector {

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    protected val viewModel: T by lazy { injectViewModel() }

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViews()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingFragmentInjector

    protected abstract fun injectViewModel(): T

    protected abstract fun initViews()
}