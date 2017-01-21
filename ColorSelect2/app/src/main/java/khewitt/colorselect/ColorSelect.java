package khewitt.colorselect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Random;
/*/////////////////////////////////////////////////////////////////////////////////////////////
*Kyle Hewitt    09/02/2015
*
* This app allows the user to enter color values from rotating a color picker for each of the three
* rgb values, enter them manually using the virtual keyboard, or randomly selecting by pressing
* the random button. This app has an okay button that appears only if the app is called with an
* intent that passes in a bundle.
*
* All images from iconfinder.com
////////////////////////////////////////////////////////////////////////////////////////////*/
public class ColorSelect extends Activity {

    NumberPicker pickRed, pickGreen, pickBlue;
    // number pickers that let the user select/display rgb values of the displayed color
    EditText displayColor;//used to display the currently selected color
    Button select, randomButton,save;//UI buttons
    int red, green, blue;//Global values of what the values of rgb being displayed
    Random rand;//to generate a random value between 0 and 255
//////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Initalization
        pickRed = (NumberPicker) findViewById(R.id.pickRed);
        pickRed.setMaxValue(255);
        pickGreen = (NumberPicker) findViewById(R.id.pickGreen);
        pickGreen.setMaxValue(255);
        pickBlue = (NumberPicker) findViewById(R.id.pickBlue);
        pickBlue.setMaxValue(255);
        displayColor = (EditText) findViewById(R.id.displayColor);
        select = (Button) findViewById(R.id.select);
        select.setVisibility(View.GONE);//default unless opened by intent
        randomButton = (Button) findViewById(R.id.randomColor);
        save = (Button) findViewById(R.id.saveButton);
        rand = new Random();
        displayColor.setBackgroundColor(Color.rgb(0, 0, 0));


//handling an intent
        final Intent intent = getIntent();
        final Bundle myBundle = intent.getExtras();
        if(myBundle!=null){
            red = myBundle.getInt("setRed",0);
            green = myBundle.getInt("setGreen",0);
            blue = myBundle.getInt("setBlue",0);
            pickRed.setValue(red);
            pickGreen.setValue(green);
            pickBlue.setValue(blue);
            displayColor.setBackgroundColor(Color.rgb(red, green, blue));
            select.setVisibility(View.VISIBLE);
        }//if
//To finish an Intent and return to previous Activity
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBundle.putInt("Red", red);
                myBundle.putInt("Green", green);
                myBundle.putInt("Blue", blue);
                intent.putExtras(myBundle);
                setResult(RESULT_OK, intent);
                finish();
                }
        });//select onClickListener()
        buttonFunctions();
    }//onCreate
//////////////////////////////////////////////////////////////////////////////////////////////////

    public void buttonFunctions(){
    //listens for a UI if user moves the number picker value manually or by entering a value into it
        pickRed.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                red = pickRed.getValue();
                green = pickGreen.getValue();
                blue = pickBlue.getValue();
                displayColor.setBackgroundColor(Color.rgb(red, green, blue));
            }
        });//pickRed onValueChangedListener()
    //listens for a UI if user moves the number picker value manually or by entering a value into it
        pickGreen.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                red = pickRed.getValue();
                green = pickGreen.getValue();
                blue = pickBlue.getValue();
                displayColor.setBackgroundColor(Color.rgb(red, green, blue));
            }
        });//pickGreen onValueChangedListener()
    //listens for a UI if user moves the number picker value manually or by entering a value into it
        pickBlue.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                red = pickRed.getValue();
                green = pickGreen.getValue();
                blue = pickBlue.getValue();
                displayColor.setBackgroundColor(Color.rgb(red, green, blue));
            }
        });//pickBlue onValueChangeListener()

    //randomly generates one value for each of the rgb
        randomButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                red = getRandomValue();
                blue = getRandomValue();
                green = getRandomValue();
                displayColor.setBackgroundColor(Color.rgb(red, green, blue));
                pickRed.setValue(red);
                pickGreen.setValue(green);
                pickBlue.setValue(blue);
            }
        });//randomButton onClickListener()
    //Saves the user's color
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Coming Soon...", Toast.LENGTH_LONG).show();
                //TODO create save code
            }
        });//save onClickListener
    }//buttonFunctions()
///////////////////////////////////////////////////////////////////////////////////////////////////

    public int getRandomValue(){
        //generates and returns a random int value between 0 and 255
        return rand.nextInt(255);
    }//getRandomValue()

    @Override
    public void onBackPressed() {

    }
}//ColorSelect
