package ru.gb.lesson12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<User> users = new ArrayList<>();

    private EditText firstName;
    private EditText lastName;
    private Button createUser;
    private TextView userList;

    private SharedPreferences prefs;
    public static final String USERS_KEY = "USERS_KEY";

    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        firstName = findViewById(R.id.user_first_name);
        lastName = findViewById(R.id.user_last_name);
        createUser = findViewById(R.id.create_user);

        userList = findViewById(R.id.user_list);


        createUser.setOnClickListener(this);


        showUsers();

    }

    @Override
    public void onClick(View view) {
        if(users == null)
            users = new ArrayList<>();

        User user = new User(
                firstName.getText().toString(),
                lastName.getText().toString()
        );

        users.add(user);

        String usersString = gson.toJson(users);

        prefs
                .edit()
                .putString(USERS_KEY, usersString)
                .apply();

        showUsers();

    }

    private void showUsers() {
        String usersString = prefs.getString(USERS_KEY, "");

        try {
            users =  gson.fromJson(usersString, new TypeToken<List<User>>(){}.getType());
            if(users == null)
            {
                users = new ArrayList<>();
            }

            StringBuilder builder = new StringBuilder();

            for(User u : users)
            {
                Log.d("happy", u.getLastName());
                builder.append(u.getFirstName());
                builder.append(" ");
                builder.append(u.getLastName());
                builder.append("\n");
            }

            userList.setText(builder.toString());


        }
        catch (Exception e)
        {
            Log.d("happy", e.getMessage());
        }



    }
}