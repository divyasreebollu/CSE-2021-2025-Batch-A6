package com.feesBus.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.feesBus.R
import com.feesBus.databinding.StartScreenBinding

class StartScreen : Fragment() {
    private val bind by lazy {
        StartScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind) {
            statPoint.alpha = 0f
            val nav = findNavController()
            val type = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
                .getString("type", "")
            statPoint.animate().alpha(1f).withEndAction {
                when (type) {
                    "user" -> {
                        nav.navigate(R.id.action_startScreen_to_userMainActivity)
                    }
                    else -> {
                        nav.navigate(R.id.action_startScreen_to_login)
                    }
                }

            }.start()
        }
    }
}