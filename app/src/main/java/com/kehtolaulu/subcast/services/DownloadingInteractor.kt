package com.kehtolaulu.subcast.services

import android.util.Log
import com.kehtolaulu.subcast.MyApplication
import com.kehtolaulu.subcast.constants.BYTE_ARRAY_SIZE
import com.kehtolaulu.subcast.entities.Episode
import com.kehtolaulu.subcast.api.DownloadApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class DownloadingInteractor {

    @Inject
    lateinit var api: DownloadApi

    fun download(episode: Episode) {
        episode.url?.let {
            api.downloadFileByUrl(it).enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        val writtenToDisk = writeResponseBodyToDisk(response.body())
                        Log.d("TAG", "file download success? $writtenToDisk")
                    } else {
                        Log.d("TAG", "server contact failed")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    //Downloading files into /storage/emulated/0/Android/data/com.kehtolaulu.subcast/files
    private fun writeResponseBodyToDisk(body: ResponseBody?): Any {
        val myFile =
            File(
                MyApplication.appComponent.provideContext().getExternalFilesDir(null)
                ?.absolutePath + File.separator + "hi.mp3")
        val inputStream: InputStream? = body?.byteStream()
        var outputStream: OutputStream?
        val fileReader = ByteArray(BYTE_ARRAY_SIZE)
        val fileSize = body?.contentLength()
        var fileSizeDownloaded = 0
        outputStream = FileOutputStream(myFile)
        while (true) {
            val read = inputStream?.read(fileReader)
            if (read == -1) {
                break
            }
            if (read != null) {
                outputStream.write(fileReader, 0, read)
                fileSizeDownloaded += read
            }
            Log.d(
                "TAG", "file download: $fileSizeDownloaded of $fileSize"
            )
        }
        outputStream.flush()
        return true
    }
}
