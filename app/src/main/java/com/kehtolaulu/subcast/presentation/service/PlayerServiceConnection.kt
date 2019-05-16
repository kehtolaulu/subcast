package com.kehtolaulu.subcast.presentation.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.kehtolaulu.subcast.MyApplication
import kotlinx.coroutines.delay

class PlayerServiceConnection(private var app: MyApplication) : ServiceConnection {

    lateinit var serviceIntent: Intent
    private var bounded = false

    private lateinit var playerBinder: PlayerService.PlayerServiceBinder

    override fun onServiceConnected(className: ComponentName, serviceIBinder: IBinder) {
        playerBinder = serviceIBinder as PlayerService.PlayerServiceBinder
        bounded = true
    }

    override fun onServiceDisconnected(arg0: ComponentName) {
        bounded = false
    }

    suspend fun getPlayer(): PlayerService.PlayerServiceBinder {
        initServiceIfNeeded()
        return playerBinder
    }

    private suspend fun initServiceIfNeeded() {
        if (!bounded) {
            connectService()
            while (!bounded)
                delay(10)
        }
    }

    private fun connectService() {
        serviceIntent = Intent(app.applicationContext, PlayerService::class.java)
        app.bindService(serviceIntent, this, Context.BIND_AUTO_CREATE)
    }

}
