package farshidroohi.github.io.onlinealbums.ui.details

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
 * OnlineAlbums | Copyrights 2/17/22.
 */
@HiltViewModel
class DetailsViewModel @Inject constructor(private val photosRepository: PhotosRepository) :
    ViewModel() {

    private val _photoMutableLiveData = MutableLiveData<Photo>()
    val photoLiveData = _photoMutableLiveData

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _dataError = MutableLiveData<Boolean>()
    val dataError: LiveData<Boolean> = _dataError

    private val _dataErrorMessage = MutableLiveData<Int>()
    val dataErrorMessage: LiveData<Int> = _dataErrorMessage

    fun getPhoto(id: String) = CoroutineScope(Dispatchers.IO).launch {

        _dataLoading.postValue(true)
        _dataError.postValue(false)

        val result = photosRepository.getPhoto(id)
        _dataLoading.postValue(false)

        when (result) {
            is Result.Success -> {
                _photoMutableLiveData.postValue(result.data!!)
            }
            is Result.Error -> {
                handleNetworkErrors(result.exception)
                _dataError.postValue(true)
            }
        }
    }

    private fun handleNetworkErrors(exception: ErrorEntity) {
        val stringRes = exception.toStringResource()
        _dataErrorMessage.postValue(stringRes)
    }
}