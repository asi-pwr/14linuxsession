package tk.julianjurec.linuxsession14.Photo

import tk.julianjurec.linuxsession14.Photo.PhotoBoothContract
import javax.inject.Inject

import tk.julianjurec.linuxsession14.Photo.PhotoBoothFragment

/**
 * Created by sp0rk on 21.05.17.
 */

class PhotoBoothPresenter @Inject
constructor(private val view: PhotoBoothFragment) : PhotoBoothContract.Presenter {

    override fun start() {
        System.out.println("photoboothpres")
    }
}