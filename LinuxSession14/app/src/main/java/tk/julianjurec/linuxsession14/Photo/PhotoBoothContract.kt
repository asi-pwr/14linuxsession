package tk.julianjurec.linuxsession14.Photo

import tk.julianjurec.linuxsession14.About.AboutPresenter
import tk.julianjurec.linuxsession14.Base.BaseFragment
import tk.julianjurec.linuxsession14.Base.BasePresenter

/**
 * Created by sp0rk on 21.05.17.
 */

interface PhotoBoothContract {
    interface View : BaseFragment<PhotoBoothPresenter>

    interface Presenter : BasePresenter
}
