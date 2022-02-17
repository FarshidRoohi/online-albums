package farshidroohi.github.io.onlinealbums.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import farshidroohi.github.io.onlinealbums.data.model.Photo
import farshidroohi.github.io.onlinealbums.databinding.PhotoDetailsFragmentBinding
import farshidroohi.github.io.onlinealbums.util.*


/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/17/22.
 */
@AndroidEntryPoint
class FullScreenPhotoFragment : Fragment() {

    private lateinit var binding: PhotoDetailsFragmentBinding

    private val viewModel by viewModels<DetailsViewModel>()

    companion object {
        fun newInstance(photoId: String): FullScreenPhotoFragment {
            val args = Bundle()
            args.putString(Keys.INTENT_PHOTO_ID, photoId)
            val fragment = FullScreenPhotoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = PhotoDetailsFragmentBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoId = requireArguments().get(Keys.INTENT_PHOTO_ID) as String

        initObservers()
        viewModel.getPhoto(photoId)
    }

    private fun initObservers() {
        viewModel.photoLiveData.observe(viewLifecycleOwner) { photo ->
            populateView(photo)
        }
        viewModel.dataError.observe(viewLifecycleOwner) { isVisible ->
            binding.txtError.isVisible = isVisible
        }

        viewModel.dataErrorMessage.observe(viewLifecycleOwner) { resId ->
            binding.txtError.setText(resId)
        }
        viewModel.dataLoading.observe(viewLifecycleOwner) { isVisible ->
            binding.progressBar.isVisible = isVisible
        }
    }

    override fun onResume() {
        super.onResume()
        hiddenActionBar()
    }

    override fun onStop() {
        super.onStop()
        showActionBar()
    }

    private fun populateView(photo: Photo) {

        Glide.with(requireContext())
            .load(photo.download_url)
            .progressBar(binding.progressBar)
            .into(binding.imgPhoto)

        binding.txtDate.text = photo.createdAt.toDisplayFormat()

    }
}