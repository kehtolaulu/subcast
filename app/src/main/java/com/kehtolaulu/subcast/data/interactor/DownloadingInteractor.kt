package com.kehtolaulu.subcast.data.interactor

import android.content.Context
import android.util.Log
import com.kehtolaulu.subcast.R
import com.kehtolaulu.subcast.data.network.DownloadApi
import com.kehtolaulu.subcast.domain.feature.details.Episode
import com.kehtolaulu.subcast.helpers.BYTE_ARRAY_SIZE
import com.kehtolaulu.subcast.presentation.extensions.showToast
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import kotlin.random.Random

class DownloadingInteractor(private val api: DownloadApi, private val context: Context) {

    fun download(episode: Episode): String? {
        var path: String?
        var fileName = context.filesDir?.absolutePath + File.separator + Random.nextInt(0, 1024102410) + ".mp3"
        episode.url?.let {
            api.downloadFileByUrl(it).enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        path = writeResponseBodyToDisk(response.body(), fileName)
                        context.showToast(context.getString(R.string.success_download))
                        Log.d("TAG", "file download success? $path")
                    } else {
                        Log.d("TAG", "server contact failed")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
        return fileName
    }

    //Downloading files into /storage/emulated/0/Android/data/com.kehtolaulu.subcast/files
    private fun writeResponseBodyToDisk(body: ResponseBody?, fileName: String): String {
        val myFile = File(fileName)
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
        inputStream?.close()
        outputStream.flush()
        outputStream.close()
        return fileName
    }
}
