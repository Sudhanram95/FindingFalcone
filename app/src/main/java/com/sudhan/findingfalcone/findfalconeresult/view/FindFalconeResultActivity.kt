package com.sudhan.findingfalcone.findfalconeresult.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.sudhan.findingfalcone.R
import com.sudhan.findingfalcone.base.BaseCompatActivity
import com.sudhan.findingfalcone.findfalcone.view.FindFalconeActivity
import com.sudhan.findingfalcone.findfalconeresult.model.FindFalconeResponse
import com.sudhan.findingfalcone.findfalconeresult.presenter.FindFalconeResultPresenter
import com.sudhan.findingfalcone.findfalconeresult.presenter.IFindFalconeResultPresenter

class FindFalconeResultActivity : BaseCompatActivity(), IFindFalconeResultView {

    lateinit var txtFindStatus: TextView
    lateinit var txtTimeTaken: TextView
    lateinit var txtPlanetFound: TextView
    lateinit var btnStartAgain: Button
    lateinit var iFindFalconeResultPresenter: IFindFalconeResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_falcone_result)

        iFindFalconeResultPresenter = FindFalconeResultPresenter(this)

        initializeView()
    }

    private fun initializeView() {
        txtFindStatus = findViewById(R.id.txt_find_status)
        txtTimeTaken = findViewById(R.id.txt_time_taken)
        txtPlanetFound = findViewById(R.id.txt_planet_found)
        btnStartAgain = findViewById(R.id.btn_start_again)

        showLoadingDialog()
        iFindFalconeResultPresenter.onGetToken()

        btnStartAgain.setOnClickListener {
            val intent = Intent(this, FindFalconeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    override fun onGetTokenApiSuccessResult() {
        val planetArray = intent.getStringArrayExtra("planet_names")
        val vehicleArray = intent.getStringArrayExtra("vehicle_names")

        iFindFalconeResultPresenter.onGetFindFalconeResult(iFindFalconeResultPresenter.onCreateFindFalconeRequest(planetArray, vehicleArray))
    }

    override fun onFindFalconeResult(findFalconeResponse: FindFalconeResponse) {
        hideLoadingDialog()
        txtFindStatus.text = if(findFalconeResponse.status.equals("success")) {
                                txtPlanetFound.text = "Planet Found: ${findFalconeResponse.planetName}"
                                "Success! Congratulations on finding Falcone. King Shah is mighty pleased."
                            } else {
                                "Bad Luck, Could not find Falcone. Let's try once again."
                            }
        txtTimeTaken.text = intent.getStringExtra("time_taken")
    }

    override fun onHandleApiError(errorMsg: String?) {
        showErrorDialog()
    }
}
