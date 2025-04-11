package com.feesBus.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.feesBus.databinding.SignupLayoutBinding
import com.feesBus.ui.PointViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class Signup : Fragment() {
    private var _bind: SignupLayoutBinding? = null
    private val bind get() = _bind

    private val models by activityViewModels<PointViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bind = SignupLayoutBinding.inflate(layoutInflater)
        return bind?.root
    }

    lateinit var nav: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nav = findNavController()
        backPointer(nav)
        with(bind!!) {
            models.toast.onEach {
                if (it == "Success") {
                    nav.navigateUp()
                }
            }.launchIn(lifecycleScope)
            signUpBtn.setOnClickListener {
                val name = name.text.toString().trim()
                val mail = email.text.toString().trim()
                val password = password.text.toString().trim()
                val cPassword = cPassword.text.toString().trim()
                val rollNumber=rollNumber.text.toString().trim()
                if (name.isEmpty()) {
                    models.toast.value = "Please enter your name"
                } else if (mail.isEmpty()) {
                    models.toast.value = "Please enter your mail"
                } else if (!mail.contains("@srit.ac.in")) {
                    models.toast.value = "Please enter a valid mail"
                } else if (password.isEmpty()) {
                    models.toast.value = "Please enter your password"
                } else if (cPassword.isEmpty()) {
                    models.toast.value = "Please enter your Confirm Password"
                } else if (cPassword != password) {
                    models.toast.value = "Confirm password and Password Should be Matchable"
                } else if (rollNumber.isEmpty()) {
                    models.toast.value = "Please enter you number"
                } else {
                    models.signUpSubmit(
                        name = name,
                        mail = mail,
                        password = password,
                        type = "user",
                        rollNumber=rollNumber
                    )

                }
            }





            loginCreate.setOnClickListener {
                nav.navigateUp()
            }
        }
    }
}