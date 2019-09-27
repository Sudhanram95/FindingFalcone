package com.sudhan.findingfalcone.findfalcone.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.sudhan.findingfalcone.findfalcone.model.PlanetModel
import com.sudhan.findingfalcone.findfalcone.presenter.IFindFalconePresenter
import android.widget.AdapterView.OnItemSelectedListener
import com.sudhan.findingfalcone.R
import com.sudhan.findingfalcone.base.BaseSpinnerAdapter
import com.sudhan.findingfalcone.findfalcone.model.VehiclesModel

class SelectPlanetAndVehicleAdapter(var context: Context, var planetList: ArrayList<PlanetModel>, var iFindFalconePresenter: IFindFalconePresenter): RecyclerView.Adapter<SelectPlanetAndVehicleAdapter.Companion.MyViewHolder>() {

    private val TOTAL_PLANETS_TO_SELECT = 4
    val selectedPlanetMap = HashMap<Int, PlanetModel>()
    val selectedVehicleMap = HashMap<Int, VehiclesModel>()

    companion object {
        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var planetSpinner: Spinner
            var vehicleRadioGroup: RadioGroup
            var txtDestination: TextView
            var vehicleList = ArrayList<VehiclesModel>()
            var previousSelectedVehicle: VehiclesModel? = null
            init {
                planetSpinner = view.findViewById(R.id.spinner_planet)
                vehicleRadioGroup = view.findViewById(R.id.rg_vehicle)
                txtDestination = view.findViewById(R.id.txt_destination)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_item, parent, false)
        val myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return TOTAL_PLANETS_TO_SELECT
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.txtDestination.text = "Destination ${position + 1}"

        val spinnerAdapter =
            BaseSpinnerAdapter(context, android.R.layout.simple_spinner_item, planetList)
        holder.planetSpinner.adapter = spinnerAdapter
        val index = if(selectedPlanetMap.get(position) != null && selectedPlanetMap.get(position)!!.isSelected)
                        planetList.indexOf(selectedPlanetMap.get(position)!!)
                    else
                        0

        holder.planetSpinner.setSelection(index)

        if (index != 0) {
            holder.vehicleList = iFindFalconePresenter.onGetFilteredVehicleList(selectedPlanetMap.get(position)!!.distance, selectedVehicleMap.get(position))
            showRadioButtons(holder)
        }

        holder.planetSpinner.setOnItemSelectedListener(object : OnItemSelectedListener {

            override fun onItemSelected(adapterView: AdapterView<*>, view: View, itemPosition: Int, id: Long) {
                if (itemPosition > 0 && itemPosition != index) {
                    selectedPlanetMap.put(position, planetList.get(itemPosition))
                    planetList = iFindFalconePresenter.onGetFilteredPlanetList(selectedPlanetMap)

                    holder.vehicleList = iFindFalconePresenter.onGetFilteredVehicleList(planetList.get(itemPosition).distance, selectedVehicleMap.get(position))
                    showRadioButtons(holder)

                    notifyDataSetChanged()
                }
            }

            override fun onNothingSelected(adapter: AdapterView<*>) {}
        })

        holder.vehicleRadioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                if (!holder.vehicleList.get(checkedId).equals(selectedVehicleMap.get(position))) {
                    selectedVehicleMap.put(position, holder.vehicleList.get(checkedId))

                    val timeTaken = iFindFalconePresenter.onCalculateTimeTaken(selectedPlanetMap, selectedVehicleMap)
                    (context as IFindFalconeView).onSetTimeTakenResult(timeTaken)

                    iFindFalconePresenter.onEnableFindFalcone(selectedPlanetMap, selectedVehicleMap)

                    iFindFalconePresenter.onUpdateVehicleCount(holder.previousSelectedVehicle, holder.vehicleList.get(checkedId))

                    holder.previousSelectedVehicle = holder.vehicleList.get(checkedId)
                    (context as FindFalconeActivity).rvSelection.post {
                        notifyDataSetChanged()
                    }
                }
            })

    }

    private fun showRadioButtons(holder: MyViewHolder) {
        holder.vehicleRadioGroup.removeAllViews()
        for (vehicle in holder.vehicleList) {
            val radioButton = RadioButton(context)
            radioButton.text = "${vehicle.name} (${vehicle.totalNumber})"
            radioButton.id = holder.vehicleList.indexOf(vehicle)
            if (selectedVehicleMap.get(holder.adapterPosition) != null)
                radioButton.isChecked = selectedVehicleMap.get(holder.adapterPosition)!!.equals(vehicle)
            holder.vehicleRadioGroup.addView(radioButton)
        }
    }
}