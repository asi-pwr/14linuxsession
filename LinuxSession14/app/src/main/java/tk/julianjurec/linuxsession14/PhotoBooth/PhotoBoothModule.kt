package tk.julianjurec.linuxsession14.PhotoBooth

import dagger.Module
import dagger.Provides

/**
 * Created by sp0rk on 21.05.17.
 */

@Module
class PhotoBoothModule(private val view: PhotoBoothFragment) {

    @Provides
    internal fun providePhotoBoothView(): PhotoBoothFragment {
        return view
    }
}

