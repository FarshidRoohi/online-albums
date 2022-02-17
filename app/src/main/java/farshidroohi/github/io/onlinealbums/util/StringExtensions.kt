package farshidroohi.github.io.onlinealbums.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/17/22.
 */

@SuppressLint("SimpleDateFormat")
fun String.toDisplayFormat(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm")
    return formatter.format(parser.parse(this))

}