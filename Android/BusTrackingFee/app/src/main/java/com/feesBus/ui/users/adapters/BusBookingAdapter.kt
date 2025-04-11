package com.feesBus.ui.users.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.feesBus.dataLayer.response.MyDetails
import com.feesBus.databinding.ListCardBinding
import com.feesBus.ui.users.insideFunctions.MapsActivity
import java.text.SimpleDateFormat

class BusBookingAdapter(private val bookingList: List<MyDetails.Data>) :
    RecyclerView.Adapter<BusBookingAdapter.BusBookingViewHolder>() {

    class BusBookingViewHolder(val binding: ListCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusBookingViewHolder {
        val binding = ListCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        context = parent.context
        return BusBookingViewHolder(binding)
    }

    var formatter: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss") // Define format

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BusBookingViewHolder, position: Int) {
        val booking = bookingList[position]
        with(holder.binding) {
            val date = booking.datePoint?.toLongOrNull()
            tvBusName.text = "${booking.name} - ${booking.busNumber}"
            tvSeatNumber.text = "Seat No: ${booking.seatNumber} | Date: ${
                if (date == null) {
                    "Unknown Date"
                } else {
                    formatter.format(date)
                }
            }"
            tvRoute.text = "From: ${booking.fromPlace} → To: ${booking.toPlace}"
            tvStopsSeats.text = "Stops: ${booking.stops} | Seats: ${booking.seats}"
            tvUserInfo.text = "User ID: ${booking.userid}"


            booking.busId?.let {
                locationFor.setOnClickListener { _ ->

                        context?.let {
                            (it as Activity).let { view ->
                                view.startActivity(
                                    Intent(
                                        view, MapsActivity::class.java
                                    )
                                )
                            }
                        }

                }
            }
        }
    }

    override fun getItemCount(): Int = bookingList.size
}
