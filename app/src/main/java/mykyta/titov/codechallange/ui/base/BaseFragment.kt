package mykyta.titov.codechallange.ui.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import mykyta.titov.codechallange.core.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(getLayoutId(), container, false)

    override fun onAttach(context: Context) {
        resolveDependencies()
        super.onAttach(context)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }

    protected abstract fun injectViewModel(): BaseViewModel

    open fun resolveDependencies() {
        AndroidSupportInjection.inject(this)
    }
}