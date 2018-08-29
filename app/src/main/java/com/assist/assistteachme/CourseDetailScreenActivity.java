package com.assist.assistteachme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.Adapters.RecycleViewAdaptersC;
import com.assist.assistteachme.Models.CategoriesRecive;
import com.assist.assistteachme.Models.ChapterRecive;
import com.assist.assistteachme.Models.Course;
import com.assist.assistteachme.Models.User;
import com.assist.assistteachme.Network.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseDetailScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button backButon;
    Button openButton;
    NavigationView nav;
    TextView coursesTextView;
    TextView whatsNewTextView;
    TextView aboutTextView;
    TextView nameTextView;
    Context context;

    //pentru recycleview
    private RecyclerView recycleView;
    private RecycleViewAdaptersC adaptersC;

    private ArrayList<Course> listCourse;
    private ArrayList<ChapterRecive> chapterReciveArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail_screen);

        //recycle view
        recycleView = findViewById(R.id.recycleView);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        listCourse = new ArrayList<>();

        //populateWithDummyData();
        chapterToApi();

        adaptersC = new RecycleViewAdaptersC( listCourse,this);
        recycleView.setAdapter(adaptersC);

        //recicleview closed

        nav=findViewById(R.id.nav_view);
        backButon = findViewById(R.id.backButton);
        openButton = findViewById(R.id.openButton);
        whatsNewTextView = findViewById(R.id.whatsTextView);
        aboutTextView = findViewById(R.id.aboutTextView);
        nameTextView = findViewById(R.id.nameTextView);
        context = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        backButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.END);
            }
        });

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.END);
            }
        });
        coursesTextView = findViewById(R.id.coursesTextView);

        coursesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CourseDetailScreenActivity.this, DrawerTestActivity.class));
            }
        });

        whatsNewTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Nothing new" ,Toast.LENGTH_SHORT).show();
            }
        });
        aboutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Developed by Sofian And Costel" ,Toast.LENGTH_SHORT).show();
            }
        });
        nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CourseDetailScreenActivity.this, MyAccountMenuDrawer.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.course_detail_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void populateWithDummyData(){
        for(int i=0;i<5;i++){
            Course c= new Course("chapter"+i,"subtitlu","asdasdsadasd asdasd asda dasd asd asda as dasd  sfgafas  ajsdg ajhdg j gdajshd kad asjhdg jahsd jasd sadasd asd asd a dsa sda asd asd asd asd asd asd asdasd .");
            listCourse.add(c);
        }
    }

    public void chapterToApi(){
        RestClient.networkHandler().getChapters().enqueue(new Callback<ArrayList<ChapterRecive>>() {
            @Override
            public void onResponse(Call<ArrayList<ChapterRecive>> call, Response<ArrayList<ChapterRecive>> response) {
                User.INSTANCE.setChapterReciveArrayList(response.body());
                chapterReciveArrayList=User.INSTANCE.getChapterReciveArrayList();
                //Toast.makeText(context," "+User.INSTANCE.getCourseId(),Toast.LENGTH_LONG).show();
                if(response.isSuccessful()){
                    for(int i=0;i<chapterReciveArrayList.size();i++){
                        if(chapterReciveArrayList.get(i).getCourseId()==User.INSTANCE.getCourseId()){
                            Course c = new Course(chapterReciveArrayList.get(i).getName(),chapterReciveArrayList.get(i).getName(),chapterReciveArrayList.get(i).getContent());
                            listCourse.add(c);
                            User.INSTANCE.setChapterId(chapterReciveArrayList.get(i).getId());

                            //Toast.makeText(context," "+chapterReciveArrayList.get(i).getId(),Toast.LENGTH_LONG).show();
                        }

                    }
                    adaptersC.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ChapterRecive>> call, Throwable t) {

            }
        });
    }

}
