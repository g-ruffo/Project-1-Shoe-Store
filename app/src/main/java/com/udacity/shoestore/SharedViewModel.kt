package com.udacity.shoestore

import androidx.lifecycle.*
import com.udacity.shoestore.models.JetTag
import kotlinx.coroutines.flow.combine
import timber.log.Timber

class SharedViewModel : ViewModel() {

    private var _jetTagList = MutableLiveData<List<JetTag>>()
    val jetTagList: LiveData<List<JetTag>>
        get() = _jetTagList

    private var _loggedIn = MutableLiveData<Boolean>()
    val loggedIn: LiveData<Boolean>
        get() = _loggedIn

    private var _registerLogin = MutableLiveData<Boolean>()
    val registerLogin: LiveData<Boolean>
        get() = _registerLogin

    private var _beenWelcomed = MutableLiveData<Boolean>()
    val beenWelcomed: LiveData<Boolean>
        get() = _beenWelcomed

    private var storeList: MutableList<JetTag>

    val mediatorLiveData: MediatorLiveData<Pair<Boolean?, Boolean?>> = MediatorLiveData()

    init {
        _loggedIn.value = false
        _registerLogin.value = false
        _beenWelcomed.value = false
        Timber.i("hasLoggedIn = ${_loggedIn.value}")
        Timber.i("hasRegisteredLogin = ${_registerLogin.value}")

        storeList = mutableListOf(
            JetTag(
                "2JZ-GTE",
                10.5,
                "Roads Untraveled",
                "The 2JZ-GTE engine is from the Toyota Supra"
            ),
            JetTag(
                "2JZ-GTE",
                10.5,
                "Roads Untraveled",
                "The 2JZ-GTE engine is from the Toyota Supra"
            ),
            JetTag(
                "2JZ-GTE",
                10.5,
                "Roads Untraveled",
                "The 2JZ-GTE engine is from the Toyota Supra"
            ),
            JetTag(
                "2JZ-GTE",
                10.5,
                "Roads Untraveled",
                "The 2JZ-GTE engine is from the Toyota Supra"
            )
        )

        _jetTagList.value = storeList


        mediatorLiveData.addSource(_loggedIn) { mediatorLiveData.postValue(it to _registerLogin.value) }
        mediatorLiveData.addSource(_registerLogin) {
            mediatorLiveData.postValue(_loggedIn.value to it)

            Timber.i("MediatorLiveData = ${mediatorLiveData.value}")


        }
    }

        fun addItemToList(item: JetTag) {
            storeList.add(item)
            _jetTagList.value = storeList
            Timber.i(item.name)

        }


        fun hasLoggedIn() {
            _loggedIn.value = true
            Timber.i("hasLoggedIn = ${_loggedIn.value}")
        }

        fun hasRegisteredLogin() {
            _registerLogin.value = true
            Timber.i("hasRegisteredLogin = ${_registerLogin.value}")
        }


        fun hasLoggedOut() {
            _loggedIn.value = false
            _registerLogin.value = false
            _beenWelcomed.value = false
            Timber.i("Has Logged Out = ${_loggedIn.value}")
        }


    }





