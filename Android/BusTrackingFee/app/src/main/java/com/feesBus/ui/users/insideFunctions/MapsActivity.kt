package com.feesBus.ui.users.insideFunctions

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.feesBus.R
import com.feesBus.dataLayer.RestApi
import com.feesBus.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val restApi = RestApi
        CoroutineScope(IO).async {
            async {
                try {
                    restApi.api2?.getDetailsThings()
                } catch (e: Exception) {
                    null
                }
            }.await().let {
                withContext(Main) {
                    it?.body()?.feeds?.let {
                        if (it.isEmpty()) {
                            return@withContext
                        }
                        val lat = it[0].field1?.toDoubleOrNull()
                        val lon = it[0].field2?.toDoubleOrNull()
                        if (lat == null || lon == null) {
                            Toast.makeText(
                                applicationContext,
                                "Lat Lon is incorrect",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val sydney = LatLng(lat, lon)
                            mMap.addMarker(MarkerOptions().position(sydney).title("Bus Location"))

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16f))

                        }

                    }


                }
            }
        }.start()

    }


}