package farshidroohi.github.io.onlinealbums.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import dagger.hilt.android.lifecycle.HiltViewModel
import farshidroohi.github.io.onlinealbums.data.PhotosRepository
import farshidroohi.github.io.onlinealbums.data.Result
import farshidroohi.github.io.onlinealbums.data.model.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
@HiltViewModel
class PhotoViewModel @Inject constructor(private val photosRepository: PhotosRepository) :
    ViewModel() {

    private val _photosMutableLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData = _photosMutableLiveData

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _dataError = MutableLiveData<Boolean>()
    val dataError: LiveData<Boolean> = _dataError

    fun fetch(isForceRefresh: Boolean = false) {
        _dataLoading.postValue(true)
        _dataError.postValue(false)
        CoroutineScope(Dispatchers.IO).launch {
            photosRepository.getPhotos(isForceRefresh).map {
                _dataLoading.postValue(false)
                if (it is Result.Success) {
                    _photosMutableLiveData.postValue(it.data!!)
                } else {
                    _dataError.postValue(true)
                }
            }
        }

    }

    fun refresh() {
        fetch(true)
    }
}