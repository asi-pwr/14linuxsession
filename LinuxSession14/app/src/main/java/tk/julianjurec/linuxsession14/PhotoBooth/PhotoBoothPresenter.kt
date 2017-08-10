package tk.julianjurec.linuxsession14.PhotoBooth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import javax.inject.Inject
import android.os.Environment.DIRECTORY_PICTURES
import android.support.v4.content.FileProvider
import android.util.Log
import java.io.File
import java.io.IOException
import java.net.URI


/**
 * Created by sp0rk on 21.05.17.
 */

class PhotoBoothPresenter @Inject
constructor(private val view: PhotoBoothFragment) : PhotoBoothContract.Presenter {
    var latestPhotoUri: Uri? = null

    override fun addPhoto(fragment: Fragment) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(fragment.activity.packageManager)?.let {

            val photoFile =  try {
                createImageFile(fragment.context)
            } catch(e: Exception) {
                e.printStackTrace()
                failPhotoCapture()
                return
            }
            val photoUri = FileProvider.getUriForFile(fragment.context, "tk.julianjurec.linuxsession14.provider", photoFile)
            latestPhotoUri = photoUri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            fragment.startActivityForResult(intent, PhotoBoothFragment.REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            PhotoBoothFragment.REQUEST_IMAGE_CAPTURE ->
                if (resultCode == Activity.RESULT_OK)
                    view.showPhotoDialog(
                            title = "Dodać zdjęcie?",
                            photo = data?.extras?.get("data") as? Bitmap,
                            completion = this::onPhotoAdded)
                else
                    failPhotoCapture()
        }
    }

    fun failPhotoCapture(){
        view.showPhotoDialog(
                title = "Ups...",
                message = "Coś poszło nie tak. Spróbować ponownie?",
                completion = this::onPhotoFailed)
    }

    fun onPhotoAdded(){
        println(latestPhotoUri.toString())
        val deletee = File(latestPhotoUri?.path)
        if (deletee.exists())
            if (deletee.delete())
                print("deleted")
            else
                print("notdeleted")
        else
            print(latestPhotoUri?.path + "doesnt exist")
        latestPhotoUri = null
    }

    fun onPhotoFailed(){
        println("Faild")
    }


    override fun start() {
        System.out.println("photoboothpres")
    }

    @Throws(IOException::class)
    private fun createImageFile(context: Context): File? {
        val timeStamp = System.currentTimeMillis()
        val imageFileName = "LS[$timeStamp]@temp"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(imageFileName, ".jpg", storageDir)
        return image
    }
}