package com.weather.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.checkSelfPermission
import androidx.navigation.compose.rememberNavController
import com.weather.ui.navigation.InitNavGraph
import com.weather.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermission by lazy {
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean -> if (isGranted) showContent() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        withPermissions {
            showContent()
        }
    }

    private fun showContent() {
        setContent {
            AppTheme {
                val navController = rememberNavController()
                InitNavGraph(navController = navController)
            }
        }
    }

    private fun withPermissions(block: () -> Unit) {
        if (checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) requestPermission.launch(Manifest.permission.ACCESS_COARSE_LOCATION) else block()
    }
}