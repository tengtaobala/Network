package com.tt.network.example

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tt.network.example.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mViewModel:LoginViewModel =LoginViewModel()

    var progressDialog:ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_login.setOnClickListener {
            mViewModel.login(this@MainActivity,{
                tv_result.text= it.toString()
                progressDialog?.dismiss()
            },{
                progressDialog = ProgressDialog.show(this@MainActivity,"", "正在加载中",false)
            })
        }
    }
}
