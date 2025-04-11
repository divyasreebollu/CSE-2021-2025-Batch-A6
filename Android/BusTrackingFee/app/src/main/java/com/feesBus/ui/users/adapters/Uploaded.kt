package com.feesBus.ui.users.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.feesBus.dataLayer.response.UploadedResponses
import com.feesBus.databinding.UploadedBinding
import com.feesBus.ui.common.dateConvertPoint
import com.feesBus.ui.common.spanned

class Uploaded(
    private val context: Context,
    private val array: ArrayList<UploadedResponses.Data>,
) : RecyclerView.Adapter<Uploaded.ViewPoint>() {
    class ViewPoint(val card: UploadedBinding) : RecyclerView.ViewHolder(card.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewPoint(
        UploadedBinding.inflate(LayoutInflater.from(context))
    )

    override fun getItemCount() = array.size

    override fun onBindViewHolder(holder: ViewPoint, position: Int) {
        with(holder.card) {
            array[position].let {
                imagePointer.load(it.image)
                dateConvertPoint(it.uploadDate.toString()) { date ->
                    contentPoint.text = spanned(
                        "${it.comments}<br>" +
                                "${it.status}<br>" +
                                "<small>${date}</small>"
                    )
                }
            }
        }
    }


}