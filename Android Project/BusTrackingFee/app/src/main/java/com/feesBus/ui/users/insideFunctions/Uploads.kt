package com.feesBus.ui.users.insideFunctions

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.feesBus.databinding.UploadDialogBinding
import com.feesBus.databinding.UploadsBinding
import com.feesBus.ui.PointViewModel
import com.feesBus.ui.common.backPointer
import com.feesBus.ui.users.adapters.Uploaded
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class Uploads : Fragment() {
    private var _bind: UploadsBinding? = null
    private val dialogBind by lazy {
        UploadDialogBinding.inflate(layoutInflater)
    }
    private val dialog by lazy {
        Dialog(requireActivity()).apply {
            setContentView(dialogBind.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    private val models by activityViewModels<PointViewModel>()

    private val bind get() = _bind
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bind = UploadsBinding.inflate(layoutInflater)
        return bind?.root
    }

    var uri: Uri? = null

    @SuppressLint("Recycle")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        models.getViewData(id = shared.toString())
        val nav = findNavController()
        backPointer(nav)
        with(bind!!) {
            addBtn.setOnClickListener {
                dialog.show()
            }
            models.uploadDetails.onEach {
                cycle3.adapter = Uploaded(requireContext(), it)
            }.launchIn(lifecycleScope)


        }

        with(dialogBind) {
            models.toast.onEach {
                if (it != null) {
                    dialog.dismiss()
                    models.getViewData(id = shared.toString())
                    comments.setText("")
                }
            }.launchIn(lifecycleScope)


            val launcher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { activityResult ->
                activityResult.data?.data?.let {
                    uri = it
                    imagePoint2.setImageURI(it)
                }

            }
            imagePoint2.setOnClickListener {
                launcher.launch(Intent(Intent.ACTION_GET_CONTENT).setType("image/*"))
            }
            sendBtn.setOnClickListener {
                val desc = comments.text.toString().trim()
                if (desc.isEmpty()) {
                    models.toast.value = "Please enter your Comments"
                } else if (uri == null) {
                    models.toast.value = "Please select a image from gallery"
                } else {
                    val image =
                        requireActivity().contentResolver.openInputStream(uri!!)?.readBytes()
                    if (image == null) {
                        models.toast.value = "Image Error"
                    } else {
                        models.addPrescriptions(
                            userid = shared.toString(),
                            uploadDate = "${System.currentTimeMillis()}",
                            image = Base64.encodeToString(image, Base64.DEFAULT),
                            desc = desc,
                            status = "Pending"
                        )
                    }
                }
            }
        }
    }

    private val shared by lazy {
        requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("id", "")
    }
}