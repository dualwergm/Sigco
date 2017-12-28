package com.dg.sigco.main.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dg.sigco.R
import com.dg.sigco.common.Constants
import com.dg.sigco.db.helper.DBHelperManager
import com.dg.sigco.db.shema.contract.SigcoDBContract
import com.dg.sigco.login.repository.UserRepository
import com.dg.sigco.login.view.LoginActivity
import com.dg.sigco.parameter.repository.ParameterRespository
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //deleteDatabase(SigcoDBContract.DATABASE_NAME);
        DBHelperManager.getInstance(this)
        reviewInitialData()
        val timerThread = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    val logged = ParameterRespository.getInstance().getParamValue(Constants.KEY_USER_LOGGED_PARAMETER)
                    startActivity<LoginActivity>(Constants.USER_LOGGED to logged)
                    finish()
                }
            }
        }
        timerThread.start()
    }

    private fun reviewInitialData() {
        val userRepository = UserRepository()
        if (!userRepository.hasUsers()) {
            userRepository.saveAdmin()
        }
    }
}