package sheridan.muhammal.assignment2.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import sheridan.muhammal.assignment2.database.Envelope
import sheridan.muhammal.assignment2.database.EnvelopeDao
import sheridan.muhammal.assignment2.database.EnvelopeDatabase
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val envelopeDao: EnvelopeDao =
        EnvelopeDatabase.getInstance(application).envelopeDao

    val history: LiveData<List<Envelope>> = envelopeDao.getAll()

    fun clear(){
        viewModelScope.launch {
            envelopeDao.deleteAll()
        }
    }

}