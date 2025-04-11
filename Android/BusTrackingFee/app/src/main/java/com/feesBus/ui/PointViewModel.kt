package com.feesBus.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feesBus.dataLayer.RestApi
import com.feesBus.dataLayer.response.LoginResponse
import com.feesBus.dataLayer.response.UploadedResponses
import com.feesBus.dataLayer.response.MyDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

class PointViewModel : ViewModel() {
    private val _toast = MutableStateFlow<String?>(null)
    val toast get() = _toast
    private val _dialog = MutableStateFlow(false)
    val dialog get() = _dialog
    private val _user = MutableStateFlow<LoginResponse.Data?>(null)
    val user get() = _user
    private val _uploadDetails = MutableStateFlow<ArrayList<UploadedResponses.Data>>(arrayListOf())
    val uploadDetails get() = _uploadDetails


    fun setStop() {
        viewModelScope.async {
            delay(500)
            _toast.value = null
        }.start()
    }

    private val retrofit = RestApi.api


    fun signUpSubmit(name: String, mail: String, password: String, type: String,rollNumber:String) {
        _dialog.value = true
        viewModelScope.async {
            async {
                try {
                    retrofit?.users(name = name, mail = mail, password = password, type = type,rollNumber=rollNumber)
                } catch (e: Exception) {
                    null
                }
            }.await().let { response ->
                response?.body()?.message?.let {
                    toast.value = it
                }
                _dialog.value = false
            }
        }.start()

    }

    fun loginBtn(userName: String, password: String) {
        dialog.value = true
        viewModelScope.async {
            async {
                retrofit?.login(name = userName, password = password)
            }.await().let { response ->
                dialog.value = false
                response?.body()?.data?.let { dataArrayList ->
                    dataArrayList.getOrNull(0)?.let {
                        _user.value = it
                        toast.value = "Success"
                        return@async

                    }
                    toast.value = "Invalid user"
                    return@async
                }
            }
        }.start()
    }

    fun addPrescriptions(
        userid: String,
        uploadDate: String,
        image: String,
        desc: String,
        status: String,
    ) {
        dialog.value = true
        viewModelScope.async {
            async {
                try {
                    retrofit?.addPrescriptions(
                        userid = userid,
                        uploadDate = uploadDate,
                        image = image,
                        comments = desc,
                        status = status
                    )
                } catch (e: Exception) {
                    null
                }

            }.await().let { response ->
                response?.body()?.message?.let {
                    _toast.value = it
                }
                dialog.value = false
            }
        }.start()
    }


    fun getViewData(id: String) {
        _dialog.value = true
        viewModelScope.async {
            async {
                try {
                    retrofit?.getData(id = id)
                } catch (e: Exception) {
                    null
                }
            }.await().let { response ->
                response?.body()?.data?.let {
                    uploadDetails.value = it
                }
                _dialog.value = false
            }
        }.start()


    }

    fun getBusDetails(id: String,array:(ArrayList<MyDetails.Data>)->Unit) {

        _dialog.value = true
        viewModelScope.async {
            async {
                try {
                    retrofit?.getMyRules(id = id)
                } catch (e: Exception) {
                    null
                }
            }.await().let { response ->
                response?.body()?.data?.let {
                    array(it)
                }
                _dialog.value = false
            }
        }.start()

    }


}