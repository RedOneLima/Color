package khewitt.colorblender;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/*//////////////////////////////////////////////////////////////////////////////////////////////////
*Kyle Hewitt    9/29/2015
*
* This is the main fragment for the SettingsActivity. This fragment will display the settings
* options and handle the user's choice either through an intent or a fragment transaction.
*
* All images from iconfinder.com
//////////////////////////////////////////////////////////////////////////////////////////////////*/
public class SettingsFragment extends Fragment {
    ImageView mTSize, mTColor, mCompColor;//clickable icons for settings options
    int mCompRed, mCompGreen, mCompBlue;//app's component color values
    Intent intent;//global intent
    Bundle myBundle;//global bundle
    OnFragmentInteractionListener mListener;

//////////Empty constructor/////////////////////////////////////////////////////////////////////////

    public SettingsFragment() {
    }
//////////Interface//////////////////////////////////////////////////////////////////////////

     interface OnFragmentInteractionListener {
        public void onFragmentInteraction(int d);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(Bundle savedInstanceState) {
//Initialization//
        super.onCreate(savedInstanceState);
        mTSize = (ImageView) getActivity().findViewById(R.id.textsize_button);
        mTColor = (ImageView) getActivity().findViewById(R.id.textcolor_button);
        mCompColor = (ImageView) getActivity().findViewById(R.id.app_color_button);
        intent = getActivity().getIntent();
        myBundle = intent.getExtras();

//Options selected
    //Text Color (ReqCode 8)
        mTColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "I see your touch " +
                        "and raise you a Toast", Toast.LENGTH_LONG).show();
                Intent intent = getActivity().getApplicationContext().getPackageManager()
                        .getLaunchIntentForPackage("khewitt.colorselect");
                Bundle bundle = new Bundle();
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.putExtras(bundle);
                intent.setFlags(0);
                startActivityForResult(intent, 8);
            }//onClick()
        });//OnClickListener()
    //Text size/Font (ReqCode --)
        mTSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO make code for changing text size/font
                Toast.makeText(getActivity().getApplicationContext(), "Coming Soon...",
                        Toast.LENGTH_LONG).show();
                mListener.onFragmentInteraction(R.id.textsize_button);
            }//onClick()
        });//OnClickListener()
    //Application component color (ReqCode 4)
        mCompColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "I see your touch " +
                        "and raise you a Toast", Toast.LENGTH_LONG).show();
                Intent intent = getActivity().getApplicationContext().getPackageManager()
                        .getLaunchIntentForPackage("khewitt.colorselect");
                Bundle bundle = new Bundle();
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.putExtras(bundle);
                intent.setFlags(0);
                startActivityForResult(intent, 4);
            }//onClick()
        });//OnClickListener()
    }//onCreate()
///////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {//making sure the parent Activity implemented the interface of this fragment
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }//onAttach()
///////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        mCompRed = bundle.getInt("Red", -1);
        mCompGreen = bundle.getInt("Green", -1);
        mCompBlue = bundle.getInt("Blue", -1);

        switch (requestCode) {
            //if component color was selected
            case 4:
                bundle.putInt("Red", mCompRed);
                bundle.putInt("Green", mCompGreen);
                bundle.putInt("Blue", mCompBlue);
                intent.putExtras(bundle);
                getActivity().setResult(getActivity().RESULT_OK, intent);
                getActivity().finish();
            //if text color was selected
            case 8:
                bundle.putInt("TextRed", mCompRed);
                bundle.putInt("TextGreen", mCompGreen);
                bundle.putInt("TextBlue", mCompBlue);
                intent.putExtras(bundle);
                getActivity().setResult(getActivity().RESULT_OK, intent);
                getActivity().finish();
        }//switch
    }//onActivityResult()
}//SettingsFragment{}