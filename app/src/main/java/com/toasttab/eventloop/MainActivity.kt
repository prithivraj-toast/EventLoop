package com.toasttab.eventloop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val presenter = Presenter()
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        compositeDisposable.add(
            presenter.stateStream.subscribe {
                checkBox.isChecked = it.isChecked
                editText.setText(it.text)
            }
        )
        bindViews()
    }

    private fun bindViews() {
        checkBox.setOnCheckedChangeListener { _, _ ->
            presenter.checkBoxClicked()
        }
        editText.addTextChangedListener {
            presenter.textEntered(it.toString())
        }
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }
}
