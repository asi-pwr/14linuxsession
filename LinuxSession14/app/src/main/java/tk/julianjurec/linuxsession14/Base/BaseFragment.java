package tk.julianjurec.linuxsession14.Base;

import android.support.v4.app.Fragment;

/**
 * Created by sp0rk on 22.03.17.
 */

public interface BaseFragment<T> {
    void setPresenter(T presenter);
}
