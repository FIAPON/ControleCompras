package com.estudo.android.controlecompras.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.estudo.android.controlecompras.R
import com.estudo.android.controlecompras.databinding.ActivityMainMvvmBinding
import com.estudo.android.controlecompras.mvvm.viewmodel.DefineLimitViewModel
import kotlinx.android.synthetic.main.activity_main_mvvm.*

class DefineLimitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: DefineLimitViewModel  = ViewModelProvider(this).get(DefineLimitViewModel::class.java)

        val binding: ActivityMainMvvmBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_mvvm)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.errorMessage.observe(this, Observer {
            if (it){
                txtValue.error = "Insira um valor válido para seguir!"
            } else {
                txtValue.error = null
            }
        })
    }

}