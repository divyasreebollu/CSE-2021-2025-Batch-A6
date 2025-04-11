package com.feesBus.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.feesBus.R
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {
    private val model by viewModels<PointViewModel>()
    private val dialog by lazy {
        Dialog(this).apply {
            setContentView(R.layout.card)
            setCancelable(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model.toast.onEach {
            it?.let {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                model.setStop()
            }
        }.launchIn(lifecycleScope)

        model.dialog.onEach {

            if (it) dialog.show() else dialog.dismiss()
        }.launchIn(lifecycleScope)
    }


}