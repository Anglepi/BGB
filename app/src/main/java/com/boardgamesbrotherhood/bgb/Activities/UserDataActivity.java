package com.boardgamesbrotherhood.bgb.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.boardgamesbrotherhood.bgb.Models.UserSession;
import com.boardgamesbrotherhood.bgb.R;

public class UserDataActivity extends AppCompatActivity {
    private Toolbar toolbar;
    //TODO tbd create fragments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        toolbar = findViewById(R.id.toolbar);
        //TODO change toolbar title
        toolbar.setTitle(R.string.menu_inicio);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.search_button:
                //TODO boton de la lupa
                break;
            case R.id.overflow_login:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.overflow_register:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.overflow_my_rofile:
                intent = new Intent(this, UserDataActivity.class);
                intent.putExtra("fragment","profile");
                startActivity(intent);
                break;
            case R.id.overflow_my_games:
                intent = new Intent(this, UserDataActivity.class);
                intent.putExtra("fragment","games");
                startActivity(intent);
                break;
            case R.id.overflow_ratings:
                intent = new Intent(this, UserDataActivity.class);
                intent.putExtra("fragment","ratings");
                startActivity(intent);
                break;
            case R.id.overflow_my_rooms:
                intent = new Intent(this, UserDataActivity.class);
                intent.putExtra("fragment","rooms");
                startActivity(intent);
                break;
            case R.id.overflow_logout:
                UserSession.Logout();
                finish();
                startActivity(getIntent());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(UserSession.SessionEstablished){
            getMenuInflater().inflate(R.menu.toolbar_menu_logged_user, menu);
        } else {
            getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}