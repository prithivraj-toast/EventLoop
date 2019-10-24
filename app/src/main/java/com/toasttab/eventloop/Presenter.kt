package com.toasttab.eventloop

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class Presenter {
    private val _stateStream =
        BehaviorSubject.create<ViewState>().apply { onNext(ViewState(false, null)) }
    val stateStream: Observable<ViewState> = _stateStream

    fun checkBoxClicked() {
        val toggledState = _stateStream.value!!.isChecked.not()
        _stateStream.onNext(_stateStream.value!!.copy(isChecked = toggledState))
    }

    fun textEntered(string: String) {
        _stateStream.onNext(_stateStream.value!!.copy(text = string))
    }
}