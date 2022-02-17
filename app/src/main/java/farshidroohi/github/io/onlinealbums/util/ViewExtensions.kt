package farshidroohi.github.io.onlinealbums.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/17/22.
 */
fun Fragment.hiddenActionBar(){
    (requireActivity() as AppCompatActivity).supportActionBar?.hide()
}
fun Fragment.showActionBar(){
    (requireActivity() as AppCompatActivity).supportActionBar?.show()
}