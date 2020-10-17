package sheridan.muhammal.assignment2.ui.output

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import sheridan.muhammal.assignment2.database.Envelope
import sheridan.muhammal.assignment2.database.EnvelopeDao
import sheridan.muhammal.assignment2.database.EnvelopeDatabase
import kotlinx.coroutines.launch


class OutputViewModel(
    envelopeKey: Long, application: Application
) : AndroidViewModel(application) {

    private val envelopeDao: EnvelopeDao =
        EnvelopeDatabase.getInstance(application).envelopeDao

    val mailbox: LiveData<Envelope> = envelopeDao.get(envelopeKey)

    fun delete(){
        mailbox.value?.let{
            viewModelScope.launch {
                envelopeDao.delete(it)
            }
        }
    }

}