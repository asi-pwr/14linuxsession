package tk.julianjurec.linuxsession14.Base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import tk.julianjurec.linuxsession14.About.AboutFragment;
import tk.julianjurec.linuxsession14.Agenda.AgendaFragment;
import tk.julianjurec.linuxsession14.Cosplay.CosplayFragment;
import tk.julianjurec.linuxsession14.Model.Speaker;
import tk.julianjurec.linuxsession14.R;
import tk.julianjurec.linuxsession14.Speakers.SpeakersFragment;
import tk.julianjurec.linuxsession14.Sponsors.SponsorsFragment;


public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener {

    @BindColor(R.color.session_light) int sessionLight;
    @BindColor(R.color.session_dark) int sessionDark;
    @BindColor(R.color.session_gold) int sessionGold;

    @BindView(R.id.container_layout) FrameLayout container;

    private ContextMenuDialogFragment contextMenu;
    private FragmentManager fragmentManager;
    private int selectedIndex = 1;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Speaker.listAll(Speaker.class).isEmpty()) {
            setContentView(R.layout.fullscreen_no_data_error);
            return;
        }
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        ButterKnife.bind(this);
        initToolbar();
        setupContextMenu();
        addFragment(AgendaFragment.newInstance(), false, R.id.container_layout);
        ((MainApplication)getApplication()).getNetworkComponent().inject(this);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.session_dark));

        Picasso p = new Picasso.Builder(getApplicationContext())
                .memoryCache(new LruCache(200 * 1024 * 1024))
                .build();
        Picasso.setSingletonInstance(p);

    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mToolBarTextView.setText(getString(R.string.screen_agenda));
    }

    private void setupContextMenu() {
        MenuObject close = new MenuObject();
        close.setResource(R.drawable.close);
        MenuObject agenda = new MenuObject(getString(R.string.screen_agenda));
        agenda.setResource(R.drawable.agenda);
        MenuObject speakers = new MenuObject(getString(R.string.screen_speakers));
        speakers.setResource(R.drawable.speaker);
        MenuObject cosplay = new MenuObject(getString(R.string.screen_cosplay));
        cosplay.setResource(R.drawable.cosplay);
        MenuObject coop = new MenuObject(getString(R.string.screen_coop));
        coop.setResource(R.drawable.heart_white);
        MenuObject patrons = new MenuObject(getString(R.string.screen_patrons));
        patrons.setResource(R.drawable.ic_menu_camera2);
        MenuObject sponsors = new MenuObject(getString(R.string.screen_sponsors));
        sponsors.setResource(R.drawable.sponsors);
        MenuObject www = new MenuObject(getString(R.string.screen_www));
        www.setResource(R.drawable.web2);
        MenuObject about = new MenuObject(getString(R.string.screen_about));
        about.setResource(R.drawable.about);

        List<MenuObject> menuObjects = new ArrayList<>();
        menuObjects.add(close);
        menuObjects.add(agenda);
        menuObjects.add(speakers);
        menuObjects.add(cosplay);
        menuObjects.add(coop);
        menuObjects.add(patrons);
        menuObjects.add(sponsors);
        menuObjects.add(www);
        menuObjects.add(about);
        for (MenuObject o : menuObjects){
            o.setBgColor(sessionLight);
            o.setDividerColor(R.color.session_dark);
        }

        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.toolbar_height));
        menuParams.setMenuObjects(menuObjects);
        menuParams.setClosableOutside(true);
        menuParams.setAnimationDuration(30);

        contextMenu = ContextMenuDialogFragment.newInstance(menuParams);
        contextMenu.setItemClickListener(this);
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        if (position != selectedIndex)
            switch (position) {
                case 1: //Agenda
                    switchTo(AgendaFragment.newInstance(), getString(R.string.screen_agenda), 1);
                    break;
                case 2: //Speakers
                    switchTo(SpeakersFragment.newInstance(), getString(R.string.screen_speakers), 2);
                    break;
                case 3: //cosplay
                    switchTo(CosplayFragment.newInstance(), getString(R.string.screen_cosplay), 3);
                    break;
                case 4: //co-op
                    switchTo(SponsorsFragment.newInstance(1), getString(R.string.screen_coop), 4);
                    break;
                case 5: //Patrons
                    switchTo(SponsorsFragment.newInstance(0), getString(R.string.screen_patrons), 5);
                    break;
                case 6: //Sponsors
                    switchTo(SponsorsFragment.newInstance(2), getString(R.string.screen_sponsors), 6);
                    break;
                case 7: //niucon
                    this.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://niucon.pl")));
                    break;
                case 8: //about
                    switchTo(AboutFragment.newInstance(), getString(R.string.screen_about), 7);
                    break;
            }
        selectedIndex = position;
    }

    private void addFragment(Fragment fragment, boolean pushBackStack, int containerId) {
        invalidateOptionsMenu();
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(containerId, fragment, backStackName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (pushBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }
    }

    public void switchTo(Fragment destination, String title, int position) {
        replaceFragment(destination, R.id.container_layout);
        setToolbarText(title);
        selectedIndex = position;
    }

    private void setToolbarText(String text) {
        TextView mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        mToolBarTextView.setText(text);
    }



    private void replaceFragment(Fragment fragment, int containerId){
        invalidateOptionsMenu();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }


        @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                contextMenu.show(fragmentManager, "ContextMenuDialogFragment");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

    @Override
    public void onBackPressed() {

    }
}
