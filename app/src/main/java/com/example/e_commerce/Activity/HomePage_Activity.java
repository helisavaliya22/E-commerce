package com.example.e_commerce.Activity;

import static com.example.e_commerce.Activity.MainActivity.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_commerce.Activity.Fragment.Add_Product_Fragment;
import com.example.e_commerce.Activity.Fragment.Show_AllProduct_Fragment;
import com.example.e_commerce.Activity.Fragment.View_Product_Fragment;
import com.example.e_commerce.R;
import com.google.android.material.navigation.NavigationView;

public class HomePage_Activity extends AppCompatActivity {

    DrawerLayout drawer_Layout;
    Toolbar toolbar;
    NavigationView navigation_View;
    TextView header_name,header_email;
    ImageView header_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        drawer_Layout=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        navigation_View=findViewById(R.id.navigation_view);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer_Layout,toolbar,R.string.Open_Drawer,R.string.Close_Drawer);
        drawer_Layout.addDrawerListener(toggle);
        toggle.syncState();
        View view = navigation_View.getHeaderView(0);
        header_name = view.findViewById(R.id.header_name);
        header_email = view.findViewById(R.id.header_email);
        header_image = view.findViewById(R.id.header_image);

        header_name.setText(""+preferences.getString("name",null));
        header_email.setText(""+preferences.getString("email",null));

        addFragment(new View_Product_Fragment());

        navigation_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.addproduct)
                {
                    replaceFragment(new Add_Product_Fragment());
                    drawer_Layout.closeDrawer(Gravity.LEFT);
                }
                if (id == R.id.viewproduct)
                {
                    replaceFragment(new View_Product_Fragment());
                    drawer_Layout.closeDrawer(Gravity.LEFT);
                }
                if (id == R.id.showAllProduct)
                {
                    replaceFragment(new Show_AllProduct_Fragment());
                    drawer_Layout.closeDrawer(Gravity.LEFT);
                }
                if (id == R.id.logout)
                {
                    editor.putBoolean("LoginStatus",false);
                    editor.commit();
                    Intent intent = new Intent(HomePage_Activity.this, Login_Activity.class);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction= fm.beginTransaction();
        transaction.replace(R.id.content_view, fragment);
        transaction.commit();
    }

    private void addFragment(Fragment fragment)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction= fm.beginTransaction();
        transaction.add(R.id.content_view, fragment);
        transaction.commit();
    }
}