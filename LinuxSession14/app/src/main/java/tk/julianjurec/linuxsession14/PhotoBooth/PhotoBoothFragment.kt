package tk.julianjurec.linuxsession14.PhotoBooth

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import javax.inject.Inject

import butterknife.ButterKnife
import butterknife.OnClick
import tk.julianjurec.linuxsession14.R

/**
 * Created by sp0rk on 21.05.17.
 */

class PhotoBoothFragment : Fragment(), PhotoBoothContract.View {

    @Inject
    lateinit var mPresenter: PhotoBoothPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerPhotoBoothComponent.builder().photoBoothModule(PhotoBoothModule(this)).build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater?.inflate(R.layout.fragment_photo_booth, container, false)
        root?.let{ButterKnife.bind(this, root)}
        return root
    }

    override fun setPresenter(presenter: PhotoBoothPresenter) {
        this.mPresenter = presenter
    }

    override fun onResume() {
        super.onResume()
        try {
            mPresenter.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @OnClick(R.id.pb_fab)
    fun addPhoto(){
        mPresenter.addPhoto(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mPresenter.onActivityResult(requestCode, resultCode, data)
    }

    fun showPhotoDialog(title: String, message: String? = null, photo: Bitmap? = null, completion: (()->Unit)? = null){
        val dialog = AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message?:"")
                .setPositiveButton("Tak", { _,_ -> completion?.invoke()})
                .setNegativeButton("Nie", null)
                .show()
    }

    companion object {
        val REQUEST_IMAGE_CAPTURE = 1
        fun newInstance(): PhotoBoothFragment {

            val args = Bundle()
            val fragment = PhotoBoothFragment()
            fragment.arguments = args
            return fragment
        }
    }
}//required empty public constructor

