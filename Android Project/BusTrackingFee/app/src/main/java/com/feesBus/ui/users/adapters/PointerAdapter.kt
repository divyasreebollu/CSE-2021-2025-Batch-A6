package com.feesBus.ui.users.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.feesBus.databinding.DesignCardBinding
import com.feesBus.ui.users.models.PointerPoint

class PointerAdapter(
    private val context: Context,
    private val array: ArrayList<PointerPoint>,
    private val selectedProduct: (Int) -> Unit,
) : RecyclerView.Adapter<PointerAdapter.ViewPoint>() {
    class ViewPoint(val card: DesignCardBinding) : RecyclerView.ViewHolder(card.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewPoint(
        DesignCardBinding.inflate(LayoutInflater.from(context))
    )

    override fun getItemCount() = array.size

    override fun onBindViewHolder(holder: ViewPoint, position: Int) {
        with(holder.card) {
            array[position].let {
                imagePoint.load(it.image)
                plump.text = it.body
                head.text = it.head
                root.setOnClickListener { _ ->

                    selectedProduct.invoke(position)

                }
            }
        }
    }


}