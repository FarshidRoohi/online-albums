package farshidroohi.github.io.onlinealbums.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import farshidroohi.github.io.onlinealbums.R
import farshidroohi.github.io.onlinealbums.databinding.ActivityMainBinding
import farshidroohi.github.io.onlinealbums.ui.adapter.PhotoAdapter
import farshidroohi.github.io.onlinealbums.ui.viewmodel.PhotoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var photoViewModel: PhotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        photoViewModel = ViewModelProvider(this)[PhotoViewModel::class.java]
        photoViewModel.fetch()

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