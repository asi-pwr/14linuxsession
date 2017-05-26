package tk.julianjurec.linuxsession14.PhotoBooth

import dagger.Component

/**
 * Created by sp0rk on 21.05.17.
 */

@Component(modules = arrayOf(PhotoBoothModule::class))
interface PhotoBoothComponent {
    fun inject(PhotoBoothFragment: PhotoBoothFragment)
}
