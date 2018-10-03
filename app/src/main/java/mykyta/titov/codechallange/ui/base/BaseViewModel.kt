package mykyta.titov.codechallange.ui.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel() : ViewModel() {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
        get() = if (field.isDisposed) CompositeDisposable() else field

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}