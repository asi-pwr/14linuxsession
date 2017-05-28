package tk.julianjurec.linuxsession14.PhotoBooth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import javax.inject.Inject
import android.os.Environment.DIRECTORY_PICTURES
import android.support.v4.content.FileProvider
import android.util.Log
import org.jetbrains.anko.support.v4.act
import java.io.File
import java.io.IOException
import java.net.URI


/**
 * Created by sp0rk on 21.05.17.
 */

class PhotoBoothPresenter @Inject
constructor(private val view: PhotoBoothFragment) : PhotoBoothContract.Presenter {
    val TAG = "PhotoBooth"
    var latestPhotoUri: String? = null

    override fun addPhoto(fragment: Fragment) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(fragment.activity.packageManager)?.let {

            val photoFile = try {
                createImageFile(fragment.context)
            } catch(e: Exception) {
                e.printStackTrace()
                failPhotoCapture()
                return
            }
            val photoUri = FileProvider.getUriForFile(fragment.context, "tk.julianjurec.linuxsession14.provider", photoFile)
            latestPhotoUri = photoFile?.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            fragment.startActivityForResult(intent, PhotoBoothFragment.REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            PhotoBoothFragment.REQUEST_IMAGE_CAPTURE ->
                if (resultCode == Activity.RESULT_OK) {
                    view.showPhotoDialog(
                            title = "Dodać zdjęcie?",
                            message = "Zdjęcie będzie dostępnie publiczne w galerii w ciągu kilku minut :)",
                            photo = getThumbnail(),
                            completion = this::onPhotoAdded)
                } else failPhotoCapture()
        }
    }

    fun failPhotoCapture() {
        view.showPhotoDialog(
                title = "Ups...",
                message = "Coś poszło nie tak. Spróbować ponownie?",
                completion = this::onPhotoFailed)
    }

    fun onPhotoAdded() {
        //sendToImgurAndAPI
        removeImageFile()
    }

    fun onPhotoFailed() {
        removeImageFile()
        view.addPhoto()
    }

    override fun start() {
    }

    @Throws(IOException::class)
    private fun createImageFile(context: Context): File? {
        val imageFileName = "LinuxSession"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(imageFileName, ".jpg", storageDir)
        return image
    }

    private fun removeImageFile() {
        val deletee = File(latestPhotoUri)
        if (deletee.exists())
            deletee.delete()
        latestPhotoUri = null
    }

    private fun getThumbnail(): Bitmap? {
        val uri = Uri.fromFile(File(latestPhotoUri))
        val photo = MediaStore.Images.Media.getBitmap(view.activity.contentResolver, uri)
        val (thumbH, thumbW) = if (photo.width > photo.height) Pair(480, 270) else Pair(270, 480)
        return ThumbnailUtils.extractThumbnail(photo, thumbH, thumbW)
    }
}