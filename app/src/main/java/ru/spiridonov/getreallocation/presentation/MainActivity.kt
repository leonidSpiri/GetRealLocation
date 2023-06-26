package ru.spiridonov.getreallocation.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import ru.spiridonov.getreallocation.R
import ru.spiridonov.getreallocation.domain.entity.CellLocation
import ru.spiridonov.getreallocation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: MainViewModel

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        requestPermission()
        observeViewModel()
        clickHandler()
        binding.mapView.settings.javaScriptEnabled = true
    }

    private fun clickHandler() {
        binding.buttonFind.setOnClickListener {
            val popupMenu = PopupMenu(this, binding.buttonFind)

            val allCellInfo = viewModel.getCurrentCellInfo.invoke()
            allCellInfo.forEachIndexed { index, cellInfo ->
                popupMenu.menu.add(0, index, 0, "${cellInfo.radio}")
            }

            popupMenu.setOnMenuItemClickListener {
                viewModel.fetchLocation(allCellInfo[it.itemId])
                true
            }

            popupMenu.show()
        }
    }


    private fun observeViewModel() =
        viewModel.locationLiveData.observe(this) { state ->
            when (state) {
                is MainActivityState.Loading -> {
                    binding.progressBar.show()
                    binding.mapView.hide()
                }

                is MainActivityState.Error -> {
                    binding.progressBar.hide()
                    binding.mapView.hide()
                    showToast("Error: ${state.message}")
                }

                is MainActivityState.Success -> {
                    binding.progressBar.hide()
                    binding.mapView.show()
                    showLocationInfo(state.response)
                }
            }
        }


    private fun showLocationInfo(cellLocation: CellLocation) {

        binding.textLocation.text = getString(
            R.string.text_location_format,
            cellLocation.latitude,
            cellLocation.longitude
        )
        binding.textAddress.text = cellLocation.address
        binding.mapView.loadUrl(
            "https://www.google.com/maps/place/${cellLocation.latitude},${cellLocation.longitude}"
        )
    }


    private fun requestPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                2000
            )
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}

private fun View.show() {
    visibility = View.VISIBLE
}

private fun View.hide() {
    visibility = View.GONE
}