package com.servy.servy

import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.TimePicker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_entrega.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

const val ZOOM_DEFAULT = 20f
const val EXTRA_TOTAL = "extra_total"
const val DEFAULT_TOTAL = 0f
const val DEFAULT_DELAY: Long = 30 * 60 * 1000

class EntregaActivity : AppCompatActivity(), OnMapReadyCallback {

    private val total: Float by lazy { intent.getFloatExtra(EXTRA_TOTAL, DEFAULT_TOTAL) }
    private val horaEntrega: Date by lazy { calcularHoraDeEntregaMinima() }
    private var map: GoogleMap? = null

    companion object {
        fun buildIntent(total: Float): Intent {
            val intent = Intent(ServyApplication.getAppContext(), EntregaActivity::class.java)
            intent.putExtra(EXTRA_TOTAL, total)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrega)

        labelTotal.text = String.format("$ %.2f", total)
        setLabelHoraEntrega(horaEntrega)
        buttonHoraEntrega.setOnClickListener { view -> mostrarDialogoFecha(calcularHoraDeEntregaMinima()) }
        buttonOrdenar.setOnClickListener { view -> mostrarDialogoConfirmacion(horaEntrega) }

        initMap()
    }

    private fun setLabelHoraEntrega(hora: Date) {
        labelHora.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(hora)
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

    fun calcularHoraDeEntregaMinima(): Date {
        val horaActual = Date()
        val horaMinima: Long = horaActual.time + DEFAULT_DELAY

        return Date(horaMinima)
    }

    private fun mostrarDialogoFecha(fechaInicial: Date = Date()) {

        val calendar = Calendar.getInstance()
        calendar.time = fechaInicial

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { view: TimePicker, hour: Int, min: Int -> updateHoraEntrega(fechaInicial, hour, min) }, hour, min, true)

        timePickerDialog.show()

    }

    private fun mostrarDialogoConfirmacion(horaEntrega: Date){
        val hora = SimpleDateFormat("HH:mm", Locale.getDefault()).format(horaEntrega)
        val labelHora = String.format(getString(R.string.label_orden_mensaje), hora)

        val dialog = AlertDialog.Builder(this)
                .setTitle(R.string.label_orden_aceptada)
                .setMessage(labelHora)
                .setPositiveButton(R.string.label_aceptar, null)
                .setOnDismissListener { finish() }

        dialog.show()
    }

    private fun updateHoraEntrega(fechaInicial: Date, hora: Int, min: Int){
        val  calendar = Calendar.getInstance()
        calendar.time = fechaInicial

        calendar.set(Calendar.HOUR_OF_DAY, hora)
        calendar.set(Calendar.MINUTE, min)

        this.horaEntrega.time = calendar.timeInMillis
        setLabelHoraEntrega(horaEntrega)
    }

}
