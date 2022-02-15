package farshidroohi.github.io.onlinealbums.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ViewModelComponent
import farshidroohi.github.io.onlinealbums.R
import farshidroohi.github.io.onlinealbums.databinding.ActivityMainBinding
import farshidroohi.github.io.onlinealbums.ui.adapter.PhotoAdapter
import farshidroohi.github.io.onlinealbums.ui.viewmodel.PhotoViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val photoViewModel: PhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = PhotoAdapter()
        binding.layoutContent.recyclerviewImages.adapter = adapter

        photoViewModel.photosLiveData.observe(this) { items ->
            adapter.submitList(items)
        }

        photoViewModel.dataLoading.observe(this) { isVisible ->
            binding.layoutContent.progressView.isVisible = isVisible
        }

        photoViewModel.dataError.observe(this) { isVisible ->
            binding.layoutError.root.isVisible = isVisible
        }

        binding.layoutError.btnTryAgain.setOnClickListener {
            photoViewModel.refresh()
        }

        photoViewModel.fetch()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.refresh) {
            photoViewModel.refresh()
        }
        return super.onOptionsItemSelected(item)
    }

}

@Module
@InstallIn(ViewModelComponent::class) // this is new
object RepositoryModule {

}