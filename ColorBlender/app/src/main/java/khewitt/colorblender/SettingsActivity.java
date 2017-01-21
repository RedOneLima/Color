package khewitt.colorblender;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
/*//////////////////////////////////////////////////////////////////////////////////////////////////
* Kyle Hewitt  9/29/2015
*
* This will open up the settings window invoked from the action bar of MainActivity. This Activity
* will maintain fragments of the different settings options.
//////////////////////////////////////////////////////////////////////////////////////////////////*/
public class SettingsActivity extends Activity
        implements SettingsFragment.OnFragmentInteractionListener {

/*//////////////////////////////////////////////////////////////////////////////////////////////////
*onCreate sets content view and adds the 'UP' capability to the action bar. It then adds the main
* fragment
//////////////////////////////////////////////////////////////////////////////////////////////////*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        SettingsFragment settingsFragment = new SettingsFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.fragment, settingsFragment).commit();
    }

/*//////////////////////////////////////////////////////////////////////////////////////////////////
*No menu items
//////////////////////////////////////////////////////////////////////////////////////////////////*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fragments, menu);
        return true;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    public void onFragmentInteraction(int id) {


    }

    @Override
    public void onBackPressed() {

    }
}
