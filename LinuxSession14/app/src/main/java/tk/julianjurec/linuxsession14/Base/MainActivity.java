package tk.julianjurec.linuxsession14.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tk.julianjurec.linuxsession14.About.AboutFragment;
import tk.julianjurec.linuxsession14.Agenda.AgendaFragment;
import tk.julianjurec.linuxsession14.MiddleParty.MiddlePartyFragment;
import tk.julianjurec.linuxsession14.R;
import tk.julianjurec.linuxsession14.Speakers.SpeakersFragment;
import tk.julianjurec.linuxsession14.Sponsors.SponsorsFragment;


public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener {

    @BindView(R.id.container_layout) FrameLayout container;

    private ContextMenuDialogFragment contextMenu;
    private FragmentManager fragmentManager;
    private int selectedIndex = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        ButterKnife.bind(this);
        initToolbar();
        setupContextMenu();
        addFragment(AgendaFragment.newInstance(), false, R.id.container_layout);
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
        mToolBarTextView.setText("Account");
    }

    private void setupContextMenu() {
        MenuObject close = new MenuObject();
        close.setResource(R.drawable.ic_menu_send);
        MenuObject agenda = new MenuObject("Agenda");
        agenda.setResource(R.drawable.ic_menu_send);
        MenuObject speakers = new MenuObject("Speakers");
        speakers.setResource(R.drawable.ic_menu_send);
        MenuObject middleParty = new MenuObject("Middle Party");
        middleParty.setResource(R.drawable.ic_menu_send);
        MenuObject about = new MenuObject("About");
        about.setResource(R.drawable.ic_menu_send);
        MenuObject sponsors = new MenuObject("Sponsors");
        sponsors.setResource(R.drawable.ic_menu_send);

        List<MenuObject> menuObjects = new ArrayList<>();
        menuObjects.add(close);
        menuObjects.add(agenda);
        menuObjects.add(speakers);
        menuObjects.add(middleParty);
        menuObjects.add(about);
        menuObjects.add(sponsors);

        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.toolbar_height));
        menuParams.setMenuObjects(menuObjects);
        menuParams.setClosableOutside(true);

        contextMenu = ContextMenuDialogFragment.newInstance(menuParams);
        contextMenu.setItemClickListener(this);
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        if (position != selectedIndex)
            switch (position) {
                case 1: //Agenda
                    switchTo(AgendaFragment.newInstance(), "Agenda", 1);
                    break;
                case 2: //Speakers
                    switchTo(SpeakersFragment.newInstance(), "Speakers", 2);
                    break;
                case 3: //MiddleParty
                    switchTo(MiddlePartyFragment.newInstance(), "Middle Party", 3);
                    break;
                case 4: //About
                    switchTo(AboutFragment.newInstance(), "About", 4);
                    break;
                case 5: //Sponsors
                    switchTo(SponsorsFragment.newInstance(), "Sponsors", 5);
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
}
