package com.floward.posts.api.extensions

import android.content.Context
import com.google.gson.Gson

/**
 * Call this [getMockResponse] method to parse JSON file into JSON object
 *
 * @param path as String (json file path)
 * @param responseObj as Object (Json object to be parsed)
 * @return JSON object
 **/
fun <T> Context.getMockResponse(path: String, responseObj: Class<T>): T {
    val inputStream = assets.open(path)
    val strMockResponse = inputStream.bufferedReader().use { it.readText() }
    val mockResponse = Gson().fromJson(strMockResponse, responseObj)
    inputStream.close()
    return mockResponse
}