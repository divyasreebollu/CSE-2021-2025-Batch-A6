package com.feesBus.ui.users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.feesBus.R
import com.feesBus.databinding.UserMainActivityBinding
import com.feesBus.ui.MainActivity
import com.feesBus.ui.common.moveToTask
import com.feesBus.ui.users.adapters.PointerAdapter
import com.feesBus.ui.users.models.PointerPoint
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UserMainActivity : Fragment() {
    private var _bind: UserMainActivityBinding? = null
    private val bind get() = _bind
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bind = UserMainActivityBinding.inflate(layoutInflater)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind!!) {
            val nav = findNavController()
            moveToTask()
            textView4.text="Hi $shared 😊 !!"

            arrayListOf<PointerPoint>().let { pointerPoints ->
                pointerPoints.add(
                    PointerPoint(
                        image = R.drawable.gallery,
                        head = "Upload",
                        body = "Please upload your college fees receipts"
                    )
                )
                pointerPoints.add(
                    PointerPoint(
                        image = R.drawable.location, head = "Bus Details",
                        body = "Your Bus details Here"
                    )
                )
                pointerPoints.add(
                    PointerPoint(
                        image = R.drawable.logout, head = "Logout",
                        body = "You will leave this Profile"
                    )
                )


                cycle2.adapter = PointerAdapter(
                    context = requireContext(), array = pointerPoints,
                    selectedProduct = {

                        when (it) {
                            0 -> {
                                nav.navigate(R.id.action_userMainActivity_to_uploads)
                            }

                            1 -> {
                                nav.navigate(R.id.action_userMainActivity_to_busDetails)
                            }

                            2 -> {
                                dialog()
                            }

                            else -> {}
                        }

                    },
                )
            }

        }
    }
    private val shared by lazy {
        requireActivity().getSharedPreferences("user",Context.MODE_PRIVATE).getString("name","")
    }

    private fun dialog() {
        MaterialAlertDialogBuilder(requireActivity()).apply {
            setTitle("Do you want to Logout ?")
            setPositiveButton("Yes") { s, _ ->
                s.dismiss()
                requireActivity().let {
                    it.finishAffinity()
                    it.getSharedPreferences("user", Context.MODE_PRIVATE).edit().clear().apply()
                    it.startActivity(
                        Intent(requireActivity(),MainActivity::class.java)
                    )
                }


            }
            setNegativeButton("No") { v, _ ->
                v.dismiss()
            }
            show()
        }
    }
}