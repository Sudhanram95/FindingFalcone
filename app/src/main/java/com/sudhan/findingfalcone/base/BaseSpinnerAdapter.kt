package com.sudhan.findingfalcone.base

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.sudhan.findingfalcone.findfalcone.model.PlanetModel

class BaseSpinnerAdapter(var contxt: Context, var spinnerRes: Int, var planetList: List<PlanetModel?>):
    ArrayAdapter<PlanetModel>(contxt, spinnerRes, planetList) {

    override fun getCount(): Int {
        return planetList.size
    }

    override fun getItem(position: Int): PlanetModel? {
        return planetList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun isEnabled(position: Int): Boolean {
        if(planetList.get(position)!!.isSelected || position == 0) {
            return false
        }
        return true
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)
        label.text = if (planetList.get(position)!!.name.isEmpty()) "Select Planet"
            else planetList.get(position)!!.name
        return label
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getView(position, convertView, parent) as TextView
        if(planetList.get(position)!!.isSelected || position == 0) {
            label.setTextColor(Color.GRAY)
        } else {
            label.setTextColor(Color.BLACK)
        }
        label.text = if (planetList.get(position)!!.name.isEmpty()) "Select Planet"
        else planetList.get(position)!!.name
        return label
    }
}