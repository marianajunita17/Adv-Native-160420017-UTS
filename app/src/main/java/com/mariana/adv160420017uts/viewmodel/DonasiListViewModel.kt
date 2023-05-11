package com.mariana.adv160420017uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.model.MyDonation

class DonasiListViewModel(application: Application): AndroidViewModel(application) {
    val myDonationsLD = MutableLiveData<ArrayList<MyDonation>>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh() {
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://raw.githubusercontent.com/marianajunita17/json-anmp-uts/main/donasiSaya.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<MyDonation>>() { }.type
                val result = Gson().fromJson<ArrayList<MyDonation>>(it, sType)

                myDonationsLD.value = result
                loadingLD.value = false

                Log.d("involleymydonation", result.toString())
            },
            {
                Log.d("involleymydonation", it.toString())
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}