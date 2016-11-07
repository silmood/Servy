package com.servy.servy

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_entrega.*

const val ZOOM_DEFAULT = 20f
const val EXTRA_TOTAL = "extra_total"
const val DEFAULT_TOTAL = 0f

class EntregaActivty : AppCompatActivity(), OnMapReadyCallback {

    private val total : Float by lazy { intent.getFloatExtra(EXTRA_TOTAL, DEFAULT_TOTAL) }
    private var map: GoogleMap? = null

    companion object {
        fun buildIntent(total: Float) : Intent{
            val intent = Intent(ServyApplication.getAppContext(), EntregaActivty::class.java)
            intent.putExtra(EXTRA_TOTAL, total)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrega)

        labelTotal.text = String.format("$ %.2f", total)

        initMap()
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map?.isMyLocationEnabled = true

        val location = getMyLocation()

        if (location != null)
            moveMapCameraTo(LatLng(location.latitude, location.longitude), googleMap)
    }

    fun getMyLocation(): Location? {
        var location: Location? = null

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val criteria = Criteria()
            val provider = locationManager.getBestProvider(criteria, true)

            location = locationManager.getLastKnownLocation(provider)
        }

        return location
    }

    fun moveMapCameraTo(position: LatLng, map: GoogleMap) {
        map.animateCamera(CameraUpdateFactory
                .newLatLngZoom(position, ZOOM_DEFAULT))
    }


}
