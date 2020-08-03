package com.estudo.android.controlecompras.mvc.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.estudo.android.controlecompras.R
import kotlinx.android.synthetic.main.activity_main.*

class DefineLimitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGo.setOnClickListener {

            val intent = Intent(
                this@DefineLimitActivity,
                ListItensActivity::class.java
            )

            if (edtValue.text.toString().trim().length > 0) {
                intent.putExtra("limit", edtValue.text.toString().toDouble())
                startActivity(intent)
            } else {
                edtValue.setError("Insira um valor válido para seguir!")
            }
        }
    }

}