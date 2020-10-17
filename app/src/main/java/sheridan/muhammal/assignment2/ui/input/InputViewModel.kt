package sheridan.muhammal.assignment2.ui.input

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import sheridan.muhammal.assignment2.database.Envelope
import sheridan.muhammal.assignment2.database.EnvelopeDao
import sheridan.muhammal.assignment2.database.EnvelopeDatabase
import kotlinx.coroutines.launch
import sheridan.muhammal.assignment2.ui.model.Die

class InputViewModel(application: Application) : AndroidViewModel(application) {

    val die: Die = Die()

    private val _envelopeId = MutableLiveData<Long?>().apply{
        value = null
    }

    val envelopeId: LiveData<Long?> = _envelopeId

    private val envelopeDao: EnvelopeDao =
        EnvelopeDatabase.getInstance(application).envelopeDao

    fun send(envelope: Envelope){
        viewModelScope.launch {
            _envelopeId.value = envelopeDao.insert(envelope)
        }
    }

    fun reset(){
        _envelopeId.value = null
    }
}