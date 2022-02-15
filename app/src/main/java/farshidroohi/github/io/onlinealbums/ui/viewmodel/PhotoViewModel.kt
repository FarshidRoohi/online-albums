package farshidroohi.github.io.onlinealbums.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import farshidroohi.github.io.onlinealbums.model.Photo

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
class PhotoViewModel : ViewModel() {

    private val _photosMutableLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData = _photosMutableLiveData

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _dataError = MutableLiveData<Boolean>()
    val dataError: LiveData<Boolean> = _dataError

    fun fetch() {

    }
    fun refresh(){

    }
}