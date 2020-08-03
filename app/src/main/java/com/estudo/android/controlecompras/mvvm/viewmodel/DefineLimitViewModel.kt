package com.estudo.android.controlecompras.mvvm.viewmodel

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estudo.android.controlecompras.mvp.base.App
import com.estudo.android.controlecompras.mvvm.view.ListItensActivity

class DefineLimitViewModel : ViewModel() {

    var limitValue: String  = ""
    var errorMessage = MutableLiveData<Boolean>()

    fun goList(){
        if (limitValue.trim().length > 0) {
            App.context?.let { safeContext ->
                val intent = Intent(safeContext, ListItensActivity::class.java)
                intent.putExtra("limit", limitValue.toDouble())
                safeContext.startActivity(intent)
            }
        } else {
            errorMessage.value = true
        }
    }

}