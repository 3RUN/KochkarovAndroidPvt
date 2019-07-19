package by.itacademy.pvt.dz11MVVM

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.itacademy.pvt.R
import by.itacademy.pvt.dz9.entity.CoordParams
import by.itacademy.pvt.dz9.entity.Coordinate
import by.itacademy.pvt.dz9.entity.Poi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_dz9.view.*

private const val ZOOM = 16f
private const val PADDING = 200

class Dz11MVVMActivity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private var isMapLoaded: Boolean = false

    private lateinit var iconTaxiBitmap: Bitmap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<CoordinatorLayout>
    private lateinit var viewModel: Dz11ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.googleMapId) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel = ViewModelProviders.of(this).get(Dz11ViewModel::class.java)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.containerId, Dz11Fragment())
            transaction.commit()
        }

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.containerId))

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheet.swipeDownId.visibility = View.INVISIBLE
                    bottomSheet.swipeUpId.visibility = View.VISIBLE
                    bottomSheet.titleTextViewId.text = resources.getString(R.string.dz9ButtonSheetName)
                } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheet.swipeDownId.visibility = View.VISIBLE
                    bottomSheet.swipeUpId.visibility = View.INVISIBLE
                    bottomSheet.titleTextViewId.text = resources.getString(R.string.dz9ButtonSheetClose)
                }
            }
        })
        iconTaxiBitmap = AppCompatResources.getDrawable(this, R.drawable.taxi_onmap_icon)!!.toBitmap()

        viewModel.state.observe(this, Observer {
            if (it == Dz11State.Ready) {
                loadLastSelected()
            }
        })

        viewModel.loadCarsList(
            CoordParams(
                Coordinate(0.0, 0.0),
                Coordinate(0.0, 0.0)
            )
        )
    }

    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        isMapLoaded = true
        loadLastSelected()
    }

    override fun onDestroy() {
        isMapLoaded = false
        super.onDestroy()
    }

    private fun loadLastSelected() {
        if (viewModel.isNotEmpty() && isMapLoaded) {
            loadAllCarsOnMap(viewModel.takeCars())

            viewModel.lastSelectedCar.observe(this, Observer { selectedPoi ->
                val carPosition = LatLng(selectedPoi.coordinate!!.latitude, selectedPoi.coordinate.longitude)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(carPosition, ZOOM))
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            })
        }
    }

    private fun loadAllCarsOnMap(carsList: List<Poi>) {
        val builder = LatLngBounds.builder()

        val iconTaxiSize = 128
        val bitmap: Bitmap = iconTaxiBitmap
        val smallMarker: Bitmap = Bitmap.createScaledBitmap(bitmap, iconTaxiSize, iconTaxiSize, false)

        carsList.forEach {
            val coordinates = LatLng(it.coordinate!!.latitude, it.coordinate.longitude)
            builder.include(coordinates)

            googleMap.addMarker(
                MarkerOptions()
                    .position(coordinates)
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                    .rotation(it.heading!!.toFloat())
            )
        }
        val bounds = builder.build()
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, PADDING))
    }
}