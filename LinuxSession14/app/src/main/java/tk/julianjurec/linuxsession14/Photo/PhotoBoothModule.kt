package tk.julianjurec.linuxsession14.Photo

import dagger.Module
import dagger.Provides
import tk.julianjurec.linuxsession14.Photo.PhotoBoothFragment

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

