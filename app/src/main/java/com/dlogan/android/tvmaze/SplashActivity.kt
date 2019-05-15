
package com.dlogan.android.tvmaze

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.work.*
import com.dlogan.android.tvmaze.ApplicationCrashHandler
import com.dlogan.android.tvmaze.R
import com.dlogan.android.tvmaze.data.epg.EpgDatabase
import com.dlogan.android.tvmaze.ui.ShowAdapter
import com.dlogan.android.tvmaze.ui.viewmodels.AllShowsViewModel
import com.dlogan.android.tvmaze.utilities.LogUtil
import com.dlogan.android.tvmaze.workers.ShowDatabaseLoaderWorker
import kotlinx.coroutines.Job

class SplashActivity : AppCompatActivity() {

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(AllShowsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        ApplicationCrashHandler.installHandler()

        subscribeUi()
        loadDatabase()

    }

    private fun subscribeUi() {
        viewModel.allShows.observe(this, Observer { allShows ->
            LogUtil.debug("SplashActivity", "show count: " + allShows.size)
        })
    }


    /**
     * Will only actually re-load the database if it has never ran or if less than x hours.
     */
    private fun loadDatabase() {
        //val job = Job()

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val request = OneTimeWorkRequestBuilder<ShowDatabaseLoaderWorker>()
                .setConstraints(constraints)
                .addTag("DB_SYNC")
                .build()
        WorkManager.getInstance(this).enqueue(request)


        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
                .observe(this, Observer { workInfo ->
                    if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                        startMainActivity()
                    } else if (workInfo != null && workInfo.state == WorkInfo.State.RUNNING){
                        //LogUtil.debug("SplashActivity", "show count: " +viewModel.allShows.value?.size)
                    } else if (workInfo != null && (workInfo.state == WorkInfo.State.FAILED ||workInfo.state == WorkInfo.State.CANCELLED ) ) {
                        Toast.makeText(this, "Error Loading Database", Toast.LENGTH_SHORT)
                        startMainActivity()
                    }
                })

    }


    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
