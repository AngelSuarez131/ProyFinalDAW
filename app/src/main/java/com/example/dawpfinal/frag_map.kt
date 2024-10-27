package com.example.dawpfinal

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class frag_map : Fragment() {
    private val COD_PERMISO_SEGUNDO_PLANO = 100
    private var isPermisos = false

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    private lateinit var btMapa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verificarPermisos()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_map, container, false)

        btMapa = view.findViewById(R.id.bt_Mapa)
        btMapa.setOnClickListener {
            val intent = Intent(requireContext(), MapActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun verificarPermisos() {
        val permisos = arrayListOf(
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION"
        )
        if (tienePermisos(permisos)) {
            isPermisos = true
            permisosConcedidos()
        } else {
            solicitarPermisos(permisos)
        }
    }

    private fun permisosConcedidos() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        try {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                if (it != null) {
                    obtenerUbicacion(it)
                } else {
                    Toast.makeText(requireContext(), "No se puede obtener ubicacion", Toast.LENGTH_SHORT).show()
                }
            }
            val locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                30000
            ).apply {
                setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                setWaitForAccurateLocation(true)
            }.build()

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    for (location in p0.locations) {
                        obtenerUbicacion(location)
                    }
                }
            }

            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun obtenerUbicacion(it: Location) {
        Log.i("mensaje", "lat: ${it.latitude} lng: ${it.longitude}")
    }

    private fun solicitarPermisos(permisosArray: ArrayList<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requestPermissions(
                permisosArray.toTypedArray(),
                COD_PERMISO_SEGUNDO_PLANO
            )
        } else {
            requestPermissions(
                permisosArray.toTypedArray(),
                COD_PERMISO_SEGUNDO_PLANO
            )
        }
    }

    private fun tienePermisos(permisosArray: ArrayList<String>): Boolean {
        return permisosArray.toTypedArray().all { item ->
            ContextCompat.checkSelfPermission(
                requireContext(),
                item
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == COD_PERMISO_SEGUNDO_PLANO) {
            val allPermisosConcedidos = grantResults.all {
                it == PackageManager.PERMISSION_GRANTED
            }
            if (grantResults.isNotEmpty() && allPermisosConcedidos) {
                permisosConcedidos()
            }
        }
    }
}
