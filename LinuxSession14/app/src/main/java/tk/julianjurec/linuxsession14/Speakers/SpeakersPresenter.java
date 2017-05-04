package tk.julianjurec.linuxsession14.Speakers;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tk.julianjurec.linuxsession14.Base.MainActivity;
import tk.julianjurec.linuxsession14.Model.Speaker;
import tk.julianjurec.linuxsession14.Model.SpeakersResponse;
import tk.julianjurec.linuxsession14.Network.Api;

/**
 * Created by sp0rk on 22.03.17.
 */

public class SpeakersPresenter implements SpeakersContract.Presenter {
    private Api api;
    private SpeakersFragment view;

    @Inject
    public SpeakersPresenter(SpeakersFragment view){
        this.view = view;
        api = ((MainActivity) view.getActivity()).getRetrofit().create(Api.class);
    }

    @Override
    public void start() {
        fetchSpeakers();
    }

    private void fetchSpeakers() {
        api.getSpeakers().enqueue(new Callback<SpeakersResponse>() {
            @Override
            public void onResponse(Call<SpeakersResponse> call, Response<SpeakersResponse> response) {
                view.onSpeakersFetched(response.body().getSpeakers());
            }

            @Override
            public void onFailure(Call<SpeakersResponse> call, Throwable t) {
                view.onSpeakersFetchFailed(t);
            }
        });
    }

    @Override
    public void showSpeakerDialog(Speaker speaker) {

        SpeakersDialogFragment.newInstance(speaker).show(view.getFragmentManager(), "SpeakerDialog");
    }
}
