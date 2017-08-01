package demoactionbar.android.myapplicationdev.com.p11_knowyournationalday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    String[] list = new String[]{"Singapore National Day is on 9 Aug", "Singapore is 52 years old", "Theme is '#OneNationTogether'"};
    ArrayAdapter<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);

        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(aa);

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout passPhrase =
                (LinearLayout) inflater.inflate(R.layout.passphrase, null);
        final EditText etPassphrase = (EditText) passPhrase
                .findViewById(R.id.editTextPassPhrase);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Enter")
                .setCancelable(false) // prevent alert dialog to dismiss outside the box
                .setView(passPhrase)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (etPassphrase.getText().toString().equalsIgnoreCase("738964")) {

                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                            SharedPreferences.Editor prefEdit = prefs.edit();
                            prefEdit.putString("pw", etPassphrase.getText().toString());
                            prefEdit.commit();

                            Toast.makeText(MainActivity.this, "Enjoy~", Toast.LENGTH_LONG).show();
                        } else {

                            System.exit(0);
                        }

                    }
                })

                .setNegativeButton("No Access Code", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        System.exit(0);
                    }
                });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String pw = prefs.getString("pw", "");
        if(pw.equalsIgnoreCase("738964")){
            alertDialog.dismiss();
        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Tally against the respective action item clicked
        //  and implement the appropriate action
        if (item.getItemId() == R.id.itemQuit) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quit?")
                    .setCancelable(false)
                    // Set text for the positive button and the corresponding
                    //  OnClickListener when it is clicked
                    .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            System.exit(0);
                        }
                    })
                    // Set text for the negative button and the corresponding
                    //  OnClickListener when it is clicked
                    .setNegativeButton("Not really", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            // Create the AlertDialog object and return it
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else if (item.getItemId() == R.id.itemSendToFriend) {
            String [] lists = new String[] { "Email", "SMS" };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend")
                    // Set the list of items easily by just supplying an
                    //  array of the items
                    .setItems(lists, new DialogInterface.OnClickListener() {
                        // The parameter "which" is the item index
                        // clicked, starting from 0
                        public void onClick(DialogInterface dialog, int which) {

                            if (which == 0) {
                                //Toast.makeText(MainActivity.this, "Email", Toast.LENGTH_LONG).show();
                                // The action you want this intent to do;
                                // ACTION_SEND is used to indicate sending text
                                Intent email = new Intent(Intent.ACTION_SEND);
                                // Put essentials like email address, subject & body text
                                email.putExtra(Intent.EXTRA_EMAIL,
                                        new String[]{"jason_lim@rp.edu.sg"});
                                email.putExtra(Intent.EXTRA_SUBJECT,
                                        "Know Your National Day");
                                email.putExtra(Intent.EXTRA_TEXT,
                                        list[0] + "\n" + list[1] + "\n" + list[2] );
                                // This MIME type indicates email
                                email.setType("message/rfc822");
                                // createChooser shows user a list of app that can handle
                                // this MIME type, which is, email
                                startActivity(Intent.createChooser(email,
                                        "Choose an Email client :"));

                            } else {

                                //Toast.makeText(MainActivity.this, "SMS", Toast.LENGTH_LONG).show();

                                // The action you want this intent to do;
                                // ACTION_SEND is used to indicate sending text
                                Intent email = new Intent(Intent.ACTION_SEND);
                                // Put essentials like email address, subject & body text
                                //email.putExtra(Intent.EXTRA_EMAIL,new String[]{"jason_lim@rp.edu.sg"});
                                email.putExtra(Intent.EXTRA_SUBJECT, "Know Your National Day");
                                email.putExtra(Intent.EXTRA_TEXT, list[0] + "\n" + list[1] + "\n" + list[2] );
                                // This MIME type indicates email
                                email.setType("text/plain");
                                // createChooser shows user a list of app that can handle
                                // this MIME type, which is, email
                                startActivity(Intent.createChooser(email, "Send sms: "));
                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else if (item.getItemId() == R.id.itemQuiz) {

            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final LinearLayout passPhrase = (LinearLayout) inflater.inflate(R.layout.quiz, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Test Yourself!")
                    .setView(passPhrase)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            RadioGroup rg1 = (RadioGroup) passPhrase.findViewById(R.id.rg1);
                            RadioGroup rg2 = (RadioGroup) passPhrase.findViewById(R.id.rg2);
                            RadioGroup rg3 = (RadioGroup) passPhrase.findViewById(R.id.rg3);

                            RadioButton rb1 = (RadioButton) passPhrase.findViewById(rg1.getCheckedRadioButtonId());
                            RadioButton rb2 = (RadioButton) passPhrase.findViewById(rg2.getCheckedRadioButtonId());
                            RadioButton rb3 = (RadioButton) passPhrase.findViewById(rg3.getCheckedRadioButtonId());

                            int score = 0;

                            if (rb1.getText().toString().equals("No")){
                                score += 1;
                                Log.d("test", rb1.getText().toString()+" "+score);
                            }
                            if (rb2.getText().toString().equals("Yes")){
                                score += 1;
                                Log.d("test", rb2.getText().toString()+" "+score);
                            }
                            if (rb3.getText().toString().equals("Yes")){
                                score += 1;
                                Log.d("test", rb3.getText().toString()+" "+score);
                            }

                            Toast.makeText(MainActivity.this, "Score: " + score, Toast.LENGTH_LONG).show();

                        }
                    })
                    .setNegativeButton("Don't know lah", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }
}
