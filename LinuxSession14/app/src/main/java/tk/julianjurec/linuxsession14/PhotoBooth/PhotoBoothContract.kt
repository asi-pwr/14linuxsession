package tk.julianjurec.linuxsession14.PhotoBooth

import android.content.Intent
import android.support.v4.app.Fragment
import tk.julianjurec.linuxsession14.Base.BaseFragment
import tk.julianjurec.linuxsession14.Base.BasePresenter

/**
 * Created by sp0rk on 21.05.17.
 */

interface PhotoBoothContract {
    interface View : BaseFragment<PhotoBoothPresenter>

    interface Presenter : BasePresenter {
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
        fun addPhoto(fragment: Fragment)
    }
}
