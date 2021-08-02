package com.vel.koloreddit

import java.util.*

/**
 * Created by Velmurugan on 8/1/2021.
 */
class ExtractXML(private val xml: String, private val tag: String) {
    fun start(): List<String> {
        val result: MutableList<String> = ArrayList()
        val splitXML = xml.split((tag + "\"").toRegex()).toTypedArray()
        val count = splitXML.size
        for (i in 1 until count) {
            var temp = splitXML[i]
            val index = temp.indexOf("\"")
            //Log.d(TAG, "start: index: " + index);
            //Log.d(TAG, "start: extracted: " + temp);
            temp = temp.substring(0, index)
            //Log.d(TAG, "start: snipped: " + temp);
            result.add(temp)
        }
        return result
    }

    companion object {
        private const val TAG = "ExtractXML"
    }


}