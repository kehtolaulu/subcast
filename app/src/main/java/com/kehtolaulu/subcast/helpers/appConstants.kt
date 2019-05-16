package com.kehtolaulu.subcast.helpers

import android.os.Build

const val BYTE_ARRAY_SIZE = 4096
const val API_KEY_PARAM = "token"
const val ITUNES_URL = "https://itunes.apple.com/"
const val SUBCAST_URL = "http://subcast.herokuapp.com/"
const val ARG_PODCAST_ID = "arg_podcast_id"

const val APP_PREFS_TOKEN = "token"
const val APP_PREFS = "prefs"

const val ACTION_NEXT = "ACTION_NEXT"
const val ACTION_PREVIOUS = "ACTION_PREVIOUS"
const val ACTION_PLAY_PAUSE = "ACTION_PLAY_PAUSE"

const val FILE_NOT_FOUND = "File not found"
fun isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
