package com.boardgamesbrotherhood.bgb.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.boardgamesbrotherhood.bgb.Fragments.UserFragments.GamesUserFragment;
import com.boardgamesbrotherhood.bgb.Fragments.UserFragments.ProfileUserFragment;
import com.boardgamesbrotherhood.bgb.Fragments.UserFragments.RatingsFragment;
import com.boardgamesbrotherhood.bgb.Fragments.UserFragments.RoomsUserFragment;
import com.boardgamesbrotherhood.bgb.Models.Game;
import com.boardgamesbrotherhood.bgb.Models.UserSession;
import com.boardgamesbrotherhood.bgb.R;

public class UserDataActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Fragment roomsUserFragment, gamesUserFragment, profileUserFragment, ratingsFragment;
    private String section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        gamesUserFragment = new GamesUserFragment();
        profileUserFragment = new ProfileUserFragment();
        ratingsFragment = new RatingsFragment();
        roomsUserFragment = new RoomsUserFragment();

        toolbar = findViewById(R.id.toolbar);
        section = getIntent().getExtras().getString("fragment");


        switch (section){
            case "profile":
                loadFragment(profileUserFragment);
                toolbar.setTitle(R.string.my_profile);
                break;
            case "games":
                loadFragment(gamesUserFragment);
                toolbar.setTitle(R.string.my_games);
                break;
            case "ratings":
                loadFragment(ratingsFragment);
                toolbar.setTitle(R.string.ratings);
                break;
            case "rooms":
                loadFragment(roomsUserFragment);
                toolbar.setTitle(R.string.my_rooms);
                break;
            default:
                loadFragment(profileUserFragment);
                toolbar.setTitle(R.string.my_profile);
                break;
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
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
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(UserSession.SessionEstablished){
            getMenuInflater().inflate(R.menu.toolbar_menu_logged_user, menu);
        } else {
            getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        }
        menu.removeItem(R.id.search_button);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    protected void onResume() {
        if(section.equals("games")){
            ((GamesUserFragment) gamesUserFragment).notifyDatasetChanged();
        }
        //TODO se podría añadir un mensaje con la opción de deshacer. Se devuelve el conjunto de juegos
        //previo a la actualización y si hubo diferencia con el posterior a la actualización se ofrece
        //la posibilidad de restaurarlo
        super.onResume();
    }
}