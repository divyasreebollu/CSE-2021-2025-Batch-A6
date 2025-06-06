package com.feesBus.ui.users.insideFunctions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.feesBus.databinding.BusDetailsBinding
import com.feesBus.ui.PointViewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind!!) {

        }
    }

}