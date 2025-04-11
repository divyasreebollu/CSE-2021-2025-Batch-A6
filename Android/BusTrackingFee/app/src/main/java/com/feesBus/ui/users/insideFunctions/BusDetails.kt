package com.feesBus.ui.users.insideFunctions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.feesBus.databinding.BusDetailsBinding
import com.feesBus.ui.PointViewModel
import com.feesBus.ui.users.adapters.BusBookingAdapter

class BusDetails : Fragment() {
    private var _bind: BusDetailsBinding? = null

    private val models by activityViewModels<PointViewModel>()

    private val bind get() = _bind
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bind = BusDetailsBinding.inflate(layoutInflater)
        return bind?.root
    }
    private val shared by lazy {
        requireActivity().getSharedPreferences("user",Context.MODE_PRIVATE).getString("id","")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind!!) {
            models.getBusDetails(shared?:"0"){
                cycle4.adapter=BusBookingAdapter(it)
            }

        }
    }

}