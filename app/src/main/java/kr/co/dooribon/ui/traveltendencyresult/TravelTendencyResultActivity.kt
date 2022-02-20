package kr.co.dooribon.ui.traveltendencyresult

import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityTravelTendencyResultBinding
import kr.co.dooribon.ui.traveltendencyresult.service.TravelTendencyResultService
import kr.co.dooribon.utils.constant.Constant
import kr.co.dooribon.utils.dpToPixel
import kr.co.dooribon.utils.getIntent
import kr.co.dooribon.utils.shortToast
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class TravelTendencyResultActivity : AppCompatActivity() {
    private val binding : ActivityTravelTendencyResultBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_travel_tendency_result)
    }

    private val viewModel by viewModels<TravelTendencyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding){
            lifecycleOwner = this@TravelTendencyResultActivity
            vm = viewModel
            activity = this@TravelTendencyResultActivity
        }

        observeTravelTendencyResult()

        intent.run {
            getStringExtra(Constant.TRAVEL_TENDENCY_RESULT_IMAGE_URL)?.let {
                viewModel.initializeTravelTendencyResultImageUrl(it)
            }
            getStringExtra(Constant.TRAVEL_TENDENCY_RESULT_IMAGE_NAME)?.let {
                viewModel.initializeTravelTendencyResultName(it)
            }
            getStringExtra(Constant.TRAVEL_TENDENCY_USER_NAME)?.let {
                viewModel.initializeTravelTendencyResultUserName(it)
            }
        }
    }

    private fun observeTravelTendencyResult() {
        viewModel.travelTendencyResultImageUrl.observe(this) {
            Glide.with(this)
                .asBitmap()
                .load(it)
                .skipMemoryCache(true)
                .override(this.resources.displayMetrics.widthPixels, this.dpToPixel(1531))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.ivTravelTendencyResult)
        }
    }

    // TODO : Service로 업무 분담
    fun downloadBitmapInGallery() {

        startService(getIntent<TravelTendencyResultService>().also { intent ->
            intent.putExtra("image", parseBitmapToByteArray())
        })
        /*val fileName = "${System.currentTimeMillis()}.jpg" // 사진 파일 이름
        val travelTendencyResultBitmap =
            binding.ivTravelTendencyResult.drawable.toBitmap() // 저장할 사진 bitmap
        var outputStream: OutputStream? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // getting the contentResolver
            this.contentResolver.also { resolver ->

                // Content resolver will process the contentValues
                val contentValues = ContentValues().apply {

                    // putting the file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                // Inserting the contentValues to contentResolver and getting the Uri
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                // Opening an outputStream with the Uri that we got
                outputStream = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            // These for devices running on android < Q
            // So I don't think an explanation in needed here
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, fileName)
            outputStream = FileOutputStream(image)
        }

        outputStream?.use {
            travelTendencyResultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            shortToast("저장 성공")
            finish()
        }*/
    }

    private fun parseBitmapToByteArray(): ByteArray {
        val stream = ByteArrayOutputStream()
        val bitmap = binding.ivTravelTendencyResult.drawable.toBitmap()
        val scale: Float = (bitmap.width / 1024).toFloat()
        val imageWidth: Int = (bitmap.width * scale).toInt()
        val imageHeight: Int = (bitmap.height * scale).toInt()
        val resizeBitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, true)
        resizeBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

        return stream.toByteArray()
    }
}