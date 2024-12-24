//package com.example.magic_demo
//
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.util.Base64
//import android.util.Log
//import okhttp3.Call
//import okhttp3.Callback
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import okhttp3.RequestBody.Companion.toRequestBody
//import okhttp3.Response
//import org.json.JSONObject
//import java.io.ByteArrayOutputStream
//import java.io.IOException
//
//
//
//
//////////////////////////////////////////////////////////////////////////////////////////////////////
//fun bitmapToBase64(bitmap: Bitmap): String {
//
//    val byteArrayOutputStream = ByteArrayOutputStream()
//    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
//    val byteArray = byteArrayOutputStream.toByteArray()
//    return Base64.encodeToString(byteArray, Base64.DEFAULT)
//}
//////////////////////////////////////////////////////////////////////////////////////////////////////
//
//fun sendSecondApiCall(image: String,) {
//    var client = OkHttpClient()
//    val jsonData = JSONObject()
//    jsonData.put("image", image)
//    val requestBody = jsonData.toString().toRequestBody("application/json".toMediaTypeOrNull())
//    val request = Request.Builder()
//        .url("https://619d-39-58-167-99.ngrok-free.app/removebg")
//        .post(requestBody)
//        .build()
//
//    client.newCall(request).enqueue(object : Callback {
//        override fun onFailure(call: Call, e: IOException) {
//        }
//
//        override fun onResponse(call: Call, response: Response) {
//            val responseBody = response.body?.string()
//            var newobj = JSONObject(responseBody)
//            var image = newobj.getString("bg_image")
//            Log.d("my_custom_message", "################################## onSuccess:Api $image")
//            response.close()
//            val imageBytes = Base64.decode(image, Base64.DEFAULT)
//            val bitmap_image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//            globalBitmat = bitmap_image
//            is_true = true
//
//
//        }
//    })
//
//}