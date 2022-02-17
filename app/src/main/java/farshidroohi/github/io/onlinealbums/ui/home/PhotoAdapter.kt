package farshidroohi.github.io.onlinealbums.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import farshidroohi.github.io.onlinealbums.R
import farshidroohi.github.io.onlinealbums.databinding.ItemImageBinding
import farshidroohi.github.io.onlinealbums.data.model.Photo
import farshidroohi.github.io.onlinealbums.util.toMB

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
class PhotoAdapter(private val onClickPhoto: ((Photo) -> Unit)? = null) :
    ListAdapter<Photo, PhotoAdapter.PhotoViewHolder>(PhotoDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return PhotoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val binding = ItemImageBinding.bind(holder.itemView)
        val item = getItem(position)

        binding.txtSize.text = item.size.toMB()

        Glide.with(holder.itemView.context)
            .load(item.toThumbnailUrl())
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.ic_baseline_image_24)
            .centerCrop()
            .into(binding.imgPhoto)

        binding.imgPhoto.setOnClickListener {
            onClickPhoto?.let { onClick -> onClick(item) }
        }

    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}