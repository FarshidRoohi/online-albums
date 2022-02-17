package farshidroohi.github.io.onlinealbums.util

import android.annotation.SuppressLint
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/17/22.
 */
@SuppressLint("CheckResult")
fun <T> RequestBuilder<T>.progressBar(
    progressBar: ProgressBar,
    onLoadFailed: (() -> Unit)? = null
): RequestBuilder<T> {
    progressBar.isVisible = true
    this.listener(
        object : RequestListener<T> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<T>?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadFailed?.let { it() }
                progressBar.isVisible = false
                return false
            }

            override fun onResourceReady(
                resource: T,
                model: Any?,
                target: Target<T>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.isVisible = false
                return false
            }
        })
    return this
}