package khewitt.colorblender;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
/*/////////////////////////////////////////////////////////////////////////////////////////////////
*Kyle Hewitt    9/29/2015
*
* This is the MainActivity for the ColorBlender app. This Activity handles UI events and Intents to
* other internal and external package Activities. This app allows the user to select two colors from
* a color picking Activity and then allows the user to blend the colors together using a seekBar.
* This app also allows the user to select and open a settings Activity where the text color,
* component color, and text size/font can be customized.
*
* All images from iconfinder.com
/////////////////////////////////////////////////////////////////////////////////////////////////*/
public class MainActivity extends Activity {


    public String COLOR_SELECT = "khewitt.colorselect";//package path to color picker
    private SurfaceView mColor1View, mColor2View, mBlendedColorView, mBackground;//displays color
    private TextView mTSText, mColor1Text, mColor2Text;//displays text for user
    private SeekBar mSeek;//allows colors to be blended
    private int mRed1, mRed2, mGreen1,mGreen2,mBlue1,mBlue2;//values for each color the user selects
    private int mProgress;//progress from seekBar
    private int redResult,greenResult,blueResult;//blended color values
    private final float TO_PERCENT = 0.01f;//To change the progress(0-100) into a percent(0-1)
///////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Initialization
        mColor1View = (SurfaceView) findViewById(R.id.color1_view);
        mColor1View.setClickable(true);//makes the entire color view clickable
        mColor2View = (SurfaceView) findViewById(R.id.color2_view);
        mColor2View.setClickable(true);//makes the entire color view clickable
        mBackground = (SurfaceView) findViewById(R.id.background_color);//settings component color
        mBlendedColorView = (SurfaceView) findViewById(R.id.blended_color2);//blended result view
        mTSText = (TextView) findViewById(R.id.text);
        mColor1Text = (TextView) findViewById(R.id.textView);
        mColor2Text = (TextView) findViewById(R.id.textView2);
        mSeek = (SeekBar) findViewById(R.id.seekBar);
        mSeek.setMax(100);

//Restoring values if app is resuming or rotated
        if (savedInstanceState != null) {
            mRed1 = savedInstanceState.getInt("Red1");
            mRed2 = savedInstanceState.getInt("Red2");
            mGreen1 = savedInstanceState.getInt("Green1");
            mGreen2 = savedInstanceState.getInt("Greem2");
            mBlue1 = savedInstanceState.getInt("Blue1");
            mBlue2 = savedInstanceState.getInt("Blue2");
            mColor1View.setBackgroundColor(Color.rgb(mRed1, mGreen1, mBlue1));
            mColor2View.setBackgroundColor(Color.rgb(mRed2, mGreen2, mBlue2));
            mBlendedColorView.setBackgroundColor(Color.rgb(mRed2, mGreen2, mBlue2));
            mColor1Text.setText("Nice Color!");
            mColor2Text.setText("Another Winning Choice!");
        }//if
        buttonFunctions();
    }//onCreate
////////////////////////////////////////////////////////////////////////////////////////////////////

    public void buttonFunctions(){
        //what happens when the seekbar is changed
        mSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mProgress = progress;//keep track of progress globally
                //to find the blended value of each color, the percentage is multiplied by the
                //differance of the two values and then the subtrahend is added back in.
                Double toInt;
                        toInt = Double.valueOf(mRed1+((mProgress * TO_PERCENT) * (mRed2 - mRed1)));
                        redResult = toInt.intValue();

                        toInt = Double.valueOf(mGreen1+((mProgress * TO_PERCENT) * (mGreen2 - mGreen1)));
                        greenResult = toInt.intValue();

                        toInt = Double.valueOf(mBlue1+((mProgress * TO_PERCENT) * (mBlue2 - mBlue1)));
                        blueResult = toInt.intValue();
                    //set the view to the blended color
                    mBlendedColorView.setBackgroundColor(Color.rgb(redResult, greenResult, blueResult));

            }//OnSeekBarChangeListener()

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //not used
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //not used
            }
        });//onProgressChanged

    ////////////when the first color is selected////////////////////
        mColor1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bundle bundle = new Bundle();
                Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage(COLOR_SELECT);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                bundle.putInt("setRed", mRed1);
                bundle.putInt("setGreen",mGreen1);
                bundle.putInt("setBlue",mBlue1);
                intent.putExtras(bundle);
                intent.setFlags(0);
                startActivityForResult(intent, 3);
            }
        });//Color1View onClickListener()

    ///////////When the second color is selected////////////////////
        mColor2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bundle bundle = new Bundle();
                Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage(COLOR_SELECT);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                bundle.putInt("setRed", mRed2);
                bundle.putInt("setGreen",mGreen2);
                bundle.putInt("setBlue",mBlue2);
                intent.putExtras(bundle);
                intent.setFlags(0);
                startActivityForResult(intent, 5);
            }
        });//color2View onClickListener()

    ////////When the blended color is long pressed it sends the value back to the color picker//////
        mBlendedColorView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(getApplicationContext(),"Can't Touch This!!",Toast.LENGTH_LONG).show();
                final Bundle bundle = new Bundle();
                Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage(COLOR_SELECT);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                bundle.putInt("setRed", redResult);
                bundle.putInt("setGreen", greenResult);
                bundle.putInt("setBlue", blueResult);
                intent.putExtras(bundle);
                intent.setFlags(0);
                startActivityForResult(intent, 9);
                return true;
            }
        });//blendedColorView onLongClickListener()
    }//buttonFunctions()
///////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //get the bundle out of the intent and get the values if theyre in the bundle otherwise -1
            Bundle setBundle = data.getExtras();
            int red = setBundle.getInt("Red", -1);
            int blue = setBundle.getInt("Blue", -1);
            int green = setBundle.getInt("Green", -1);
            int tRed = setBundle.getInt("TextRed", -1);
            int tBlue = setBundle.getInt("TextBlue", -1);
            int tGreen = setBundle.getInt("TextGreen", -1);
            switch(requestCode) {
            // Settings Activity
                case 1:
                    //There are multiple possiblities coming back from the Settings intent
                    if(tRed == -1 && tGreen == -1 && tBlue == -1) {
                        mBackground.setBackgroundColor(Color.rgb(red, green, blue));
                        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(red, green, blue)));
                        break;
                    }else{
                        mTSText.setTextColor(Color.rgb(tRed,tGreen,tBlue));
                        mColor1Text.setTextColor(Color.rgb(tRed,tGreen,tBlue));
                        mColor2Text.setTextColor(Color.rgb(tRed, tGreen, tBlue));
                        break;
                    }//else
        //color 1 selection
        case 3:
        mColor1View.setBackgroundColor(Color.rgb(red, green, blue));
        mColor1Text.setText("Nice Color!");
        mColor1Text.setTextColor(Color.BLACK);
        mRed1 = red;
        mGreen1 = green;
        mBlue1 = blue;
        mBlendedColorView.setBackgroundColor(Color.rgb(red, green, blue));
        break;
        //color 2 selection
        case 5:
        mColor2View.setBackgroundColor(Color.rgb(red, green, blue));
        mColor2Text.setText("Another Winning Choice!");
        mColor2Text.setTextColor(Color.BLACK);
        mRed2 = red;
        mGreen2 = green;
        mBlue2 = blue;
        break;
    }//switch
    }//onActivityResult()
///////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){//if the settings button is pressed
            case R.id.action_settings:
                Intent intent = new Intent(this,SettingsActivity.class);
                intent.setFlags(0);
                startActivityForResult(intent, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Red1", mRed1);
        outState.putInt("Red2", mRed2);
        outState.putInt("Green1", mGreen1);
        outState.putInt("Green2", mGreen2);
        outState.putInt("Blue1", mBlue1);
        outState.putInt("Blue2", mBlue2);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onBackPressed() {

    }
}//MainActivity{}
