package com.sudhan.findingfalcone.findfalcone.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sudhan.findingfalcone.R
import com.sudhan.findingfalcone.base.BaseCompatActivity
import com.sudhan.findingfalcone.findfalcone.model.PlanetModel
import com.sudhan.findingfalcone.findfalcone.presenter.FindFalconePresenter
import com.sudhan.findingfalcone.findfalcone.presenter.IFindFalconePresenter
import com.sudhan.findingfalcone.findfalconeresult.view.FindFalconeResultActivity

class FindFalconeActivity : BaseCompatActivity(), IFindFalconeView {

    lateinit var iFindFalconePresenter: IFindFalconePresenter
    lateinit var rvSelection: RecyclerView
    lateinit var txtTimeTaken: TextView
    lateinit var btnFindFalcone: Button
    lateinit var selectedPlanetArray: Array<String>
    lateinit var selectedVehicleArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_falcone)

        iFindFalconePresenter = FindFalconePresenter(this)

        initializeView()
    }

    fun initializeView() {
        rvSelection = findViewById(R.id.rv_select_planet_vehicle)
        txtTimeTaken = findViewById(R.id.txt_time_taken)
        btnFindFalcone = findViewById(R.id.btn_find_falcone)

        showLoadingDialog()
        iFindFalconePresenter.onGetPlanetList()

        btnFindFalcone.setOnClickListener {
            val intent = Intent(this, FindFalconeResultActivity::class.java)
            intent.putExtra("time_taken", txtTimeTaken.text.toString())
            intent.putExtra("planet_names", selectedPlanetArray)
            intent.putExtra("vehicle_names", selectedVehicleArray)
            startActivity(intent)
        }
    }

    override fun onRenderSelectForm(planetList: ArrayList<PlanetModel>) {
        hideLoadingDialog()
        val selectionAdapter = SelectPlanetAndVehicleAdapter(this, planetList, iFindFalconePresenter)
        rvSelection.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvSelection.adapter = selectionAdapter
    }

    override fun onSetTimeTakenResult(timeTaken: Int) {
        txtTimeTaken.text = "Time Taken: $timeTaken"
    }

    override fun onFindFalconeEnabledResult(selectedPlanetArray: Array<String>, selectedVehicleArray: Array<String>) {
        btnFindFalcone.isEnabled = true
        this.selectedPlanetArray = selectedPlanetArray
        this.selectedVehicleArray = selectedVehicleArray
    }

    override fun onHandleApiError(errorMsg: String?) {
        showErrorDialog()
    }
}
