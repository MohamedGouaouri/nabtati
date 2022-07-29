package com.example.learn.nabtati.sockets

import android.util.Log
import com.example.learn.nabtati.common.Constants.WS_URL
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {

    private lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {
            mSocket = IO.socket(WS_URL)
        } catch (e: URISyntaxException) {
            Log.e("SOCKET ERROR", e.message.toString())
        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
}