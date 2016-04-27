package fordm13.tacoma.uw.edu.webservicelab.authenticate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.OutputStreamWriter;

import fordm13.tacoma.uw.edu.webservicelab.CourseActivity;
import fordm13.tacoma.uw.edu.webservicelab.R;

public class SignInActivity extends AppCompatActivity implements LogInFragment.LoginInteractionListener{
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        mSharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS)
                , Context.MODE_PRIVATE);
        if(!mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN), false)){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new LogInFragment())
                    .commit();
        } else {
            Intent i  = new Intent(this, CourseActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void login(String userId, String pwd){

        ConnectivityManager connMger = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMger.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            //check if the login and password is valid
            //new LoginTask().execute(url);
            try{
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    openFileOutput(getString(R.string.LOGIN_FILE),
                            Context.MODE_PRIVATE));
                outputStreamWriter.write("email = " + userId + ";");
                outputStreamWriter.write("password = " + pwd);
                outputStreamWriter.close();
                Toast.makeText(this, "Stored in File Successfully!", Toast.LENGTH_LONG)
                .show();
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "No network connection avaiable. Cannot " +
                    "authenticate user",
                    Toast.LENGTH_SHORT).show();
            return;

        }

        mSharedPreferences
                .edit()
                .putBoolean(getString(R.string.LOGGEDIN), true)
                .commit();

        Intent i = new Intent(this, CourseActivity.class);
        startActivity(i);
        finish();
    }

}
