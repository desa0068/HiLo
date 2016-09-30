package com.algonquincollege.desa0068.guessanumber;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText txtnumberguess;
    String guessednumber;
    Button btnguess;
    int userguess,theNumber,countguesses;
    DialogFragment dialog;
    private static String ABOUT_DIALOG_TAG;
    Random r;

    int max=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog=new AboutDialogFragment();
        ABOUT_DIALOG_TAG="About Dialog";
        txtnumberguess=(EditText)findViewById(R.id.noguess);

        btnguess=(Button)findViewById(R.id.btnguess);
        theNumber=generateRandomNumber();
        btnguess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessednumber=txtnumberguess.getText().toString();
                if(guessednumber.isEmpty())
                {
                    txtnumberguess.setError(getResources().getString(R.string.emptynumber));
                }
                else {
                    try {
                        countguesses++;
                        if(countguesses<10) {
                            userguess = Integer.parseInt(guessednumber);
                            if (userguess <= 0 || userguess > 1000) {
                                txtnumberguess.setError(getResources().getString(R.string.numberrange));
                            } else {

                                if (userguess == theNumber) {

                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.win), Toast.LENGTH_LONG).show();
                                } else if (userguess < theNumber) {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.lownumber), Toast.LENGTH_SHORT).show();
                                } else if (userguess > theNumber) {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.highnumber), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),getResources().getString(R.string.resetmessage),Toast.LENGTH_LONG).show();
                            countguesses=0;
                            theNumber = generateRandomNumber();
                        }

                    } catch (NumberFormatException ne) {
                        txtnumberguess.setError(getResources().getString(R.string.notanumber));
                    }

                }
            }
        });



        btnguess.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this,""+theNumber,Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    public int generateRandomNumber()
    {
        r=new Random();
        return r.nextInt(max) + 1;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.about)
        {
            dialog.show(getFragmentManager(),ABOUT_DIALOG_TAG);
        }
        return super.onOptionsItemSelected(item);
    }
}
