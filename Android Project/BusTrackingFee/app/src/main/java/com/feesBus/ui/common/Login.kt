package com.feesBus.ui.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.feesBus.R
import com.feesBus.databinding.LoginLayoutBinding
import com.feesBus.ui.PointViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class Login : Fragment() {
    private var _bind: LoginLayoutBinding? = null
    private val bind get() = _bind
    private val model by activityViewModels<PointViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bind = LoginLayoutBinding.inflate(layoutInflater)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveToTask()
        val nav = findNavController()
        model.user.onEach {
            if (it != null) {
                requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).edit().apply {
                    putString("name", it.name)
                    putString("id", it.id)
                    putString("type", it.type)
                    putString("mail", it.mail)
                }.apply()

                nav.navigate(R.id.action_login_to_userMainActivity)
            }
        }.launchIn(lifecycleScope)


        with(bind!!) {
            val nav = findNavController()
            loginBtn.setOnClickListener {
                val name = name2.text.toString().trim()
                val password = password2.text.toString().trim()
                if (name.isEmpty()) {
                    model.toast.value = "Please enter your Name"
                } else if (password.isEmpty()) {
                    model.toast.value = "Please enter your Password"
                } else {
                    model.loginBtn(userName = name, password = password)
                }
            }
            createAc.setOnClickListener {
                nav.navigate(R.id.action_login_to_signup)
            }
        }
    }

}