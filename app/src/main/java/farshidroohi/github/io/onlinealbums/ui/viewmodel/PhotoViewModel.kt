package farshidroohi.github.io.onlinealbums.ui.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import farshidroohi.github.io.onlinealbums.data.PhotosRepository
import farshidroohi.github.io.onlinealbums.data.Result
import farshidroohi.github.io.onlinealbums.data.model.ErrorEntity
import farshidroohi.github.io.onlinealbums.data.model.Photo
import farshidroohi.github.io.onlinealbums.util.toStringResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
@HiltViewModel
class PhotoViewModel
@Inject constructor(private val photosRepository: PhotosRepository) : ViewModel() {

    private val _photosMutableLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData = _photosMutableLiveData

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _dataError = MutableLiveData<Boolean>()
    val dataError: LiveData<Boolean> = _dataError

    @StringRes
    private val _dataErrorMessage = MutableLiveData<Int>()
    val dataErrorMessage: LiveData<Int> = _dataErrorMessage

    fun fetch(isForceRefresh: Boolean = false) = CoroutineScope(Dispatchers.IO).launch {

        _dataLoading.postValue(true)
        _dataError.postValue(false)
        _photosMutableLiveData.postValue(emptyList())

        val result = photosRepository.getPhotos(isForceRefresh)
        _dataLoading.postValue(false)

        when (result) {
            is Result.Success -> {
                _photosMutableLiveData.postValue(result.data!!)
            }
            is Result.Error -> {
                handleNetworkErrors(result.exception)
                _dataError.postValue(true)
            }
        }
    }

    private fun handleNetworkErrors(exception: ErrorEntity) {

        val stringRes = exception.toStringResource()

        if (stringRes == 0) {
            // We can create a new MutableLiveData and post the custom error message
            return
        }

        _dataErrorMessage.postValue(stringRes)

    }

    fun refresh() {
        fetch(true)
    }
}