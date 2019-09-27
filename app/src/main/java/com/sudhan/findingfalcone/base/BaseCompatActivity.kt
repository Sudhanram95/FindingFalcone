package com.sudhan.findingfalcone.base

import android.app.Dialog
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.sudhan.findingfalcone.R

abstract class BaseCompatActivity : AppCompatActivity() {
    var loadingDialog: Dialog? = null

    fun showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = Dialog(this, R.style.DialogTheme)
            loadingDialog?.setContentView(R.layout.dialog_loading)
            val imgLoading = loadingDialog?.findViewById<ImageView>(R.id.img_loading);
            val rotation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            rotation.setFillAfter(true)
            imgLoading?.startAnimation(rotation)
            loadingDialog?.show()
        }
    }

    fun hideLoadingDialog() {
        loadingDialog?.dismiss()
    }

    fun showErrorDialog() {
        val errorDialog = Dialog(this, R.style.DialogTheme)
        errorDialog.setContentView(R.layout.dialog_error)
        errorDialog.show()
    }
}