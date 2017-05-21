package tk.julianjurec.linuxsession14.Photo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import javax.inject.Inject

import butterknife.ButterKnife
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
        val root = inflater!!.inflate(R.layout.fragment_photo_booth, container, false)
        ButterKnife.bind(this, root)
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

    companion object {

        fun newInstance(): PhotoBoothFragment {

            val args = Bundle()
            val fragment = PhotoBoothFragment()
            fragment.arguments = args
            return fragment
        }
    }
}//required empty public constructor

