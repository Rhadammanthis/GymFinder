package mx.com.cceo.gymapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import mx.com.cceo.gymapp.adapter.GridAdapter;
import mx.com.cceo.gymapp.adapter.GymListAdapter;

public class GymDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_detail);

        this.setTitle("Detalle de Gimnasio");

        //init recycler view
        recyclerView = (RecyclerView) findViewById(R.id.gym_detail_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //get name from intent
        name = getIntent().getStringExtra("name");

        //to easier retrieve drawable resources
        switch(name)
        {
            case "SF Fitness":
                loadResorurces("SF Fitness", R.drawable.sf_logo, R.drawable.sf_1, R.drawable.sf_2, R.drawable.sf_3,
                        R.drawable.sf_4, R.drawable.sf_5, R.drawable.sf_6);
                break;
            case "Gold Gym":
                loadResorurces("Gold Gym", R.drawable.gold_logo, R.drawable.gold_1, R.drawable.gold_2, R.drawable.gold_3,
                        R.drawable.gold_4, R.drawable.gold_5, R.drawable.gold_6);
                break;
            case "Studiomix":
                loadResorurces("Studiomix", R.drawable.studio_logo, R.drawable.studio_1, R.drawable.studio_2, R.drawable.studio_3,
                        R.drawable.studio_4, R.drawable.studio_5, R.drawable.studio_6);
                break;
            case "Virgin Active":
                loadResorurces("Virgin Active", R.drawable.virgin_logo, R.drawable.virgin_1, R.drawable.virgin_2, R.drawable.virgin_3,
                        R.drawable.virgin_4, R.drawable.virgin_5, R.drawable.virgin_6);
                break;
            case "Ludlow Fitness":
                loadResorurces("Ludlow Fitness", R.drawable.ludlow_logo, R.drawable.ludlow_1, R.drawable.ludlow_2, R.drawable.ludlow_3,
                        R.drawable.ludlow_4, R.drawable.ludlow_5, R.drawable.ludlow_6);
                break;
            case "Chalk Gyms":
                loadResorurces("Chalk Gyms", R.drawable.chalk_logo, R.drawable.chalk_1, R.drawable.chalk_2, R.drawable.chalk_3,
                        R.drawable.chalk_4, R.drawable.chalk_5, R.drawable.chalk_6);
                break;
            case "McFIT":
                loadResorurces("McFIT", R.drawable.mc_logo, R.drawable.mc_1, R.drawable.mc_2, R.drawable.mc_3,
                        R.drawable.mc_4, R.drawable.mc_5, R.drawable.mc_6);
                break;
            case "ENERGi Fitness":
                loadResorurces("ENERGi Fitness", R.drawable.energi_logo, R.drawable.energi_1, R.drawable.energi_2, R.drawable.energi_3,
                        R.drawable.energi_4, R.drawable.energi_5, R.drawable.energi_6);
                break;
            case "Fitness 19":
                loadResorurces("Fitness 19", R.drawable.nine_logo, R.drawable.nine_1, R.drawable.nine_2, R.drawable.nine_3,
                        R.drawable.nine_4, R.drawable.nine_5, R.drawable.nine_6);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gym_detail, menu);
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

    public void loadResorurces(String name, int header, int img1, int img2, int img3, int img4, int img5, int img6)
    {

        //set name
        TextView tvName = (TextView) findViewById(R.id.gym_detail_name);
        tvName.setText(name);

        //set header
        ImageView ivHeader = (ImageView) findViewById(R.id.gym_detail_header);
        ivHeader.setImageResource(header);

        //load recyclerview data
        ArrayList<Integer> dataSet = new ArrayList<>();

        TextView coach1Name = (TextView) findViewById(R.id.coach_1_name);
        TextView coach2Name = (TextView) findViewById(R.id.coach_2_name);
        TextView coach3Name = (TextView) findViewById(R.id.coach_3_name);

        TextView coach1Bio = (TextView) findViewById(R.id.coach_1_bio);
        TextView coach2Bio = (TextView) findViewById(R.id.coach_2_bio);
        TextView coach3Bio = (TextView) findViewById(R.id.coach_3_bio);

        ImageView coach1Pic = (ImageView) findViewById(R.id.coach_1_photo);
        ImageView coach2Pic = (ImageView) findViewById(R.id.coach_2_photo);
        ImageView coach3Pic = (ImageView) findViewById(R.id.coach_3_photo);

        Coach coach1 = getRandomCoach();
        Coach coach2 = getRandomCoach();
        Coach coach3 = getRandomCoach();

        coach1Name.setText(coach1.getName());
        coach2Name.setText(coach2.getName());
        coach3Name.setText(coach3.getName());

        coach1Bio.setText(coach1.getBio());
        coach2Bio.setText(coach2.getBio());
        coach3Bio.setText(coach3.getBio());

        coach1Pic.setImageResource(coach1.getProfilePic());
        coach2Pic.setImageResource(coach2.getProfilePic());
        coach3Pic.setImageResource(coach3.getProfilePic());


        dataSet.add(img1);
        dataSet.add(img2);
        dataSet.add(img3);
        dataSet.add(img4);
        dataSet.add(img5);
        dataSet.add(img6);

        mAdapter = new GridAdapter(dataSet);
        recyclerView.setAdapter(mAdapter);
    }

    public Coach getRandomCoach()
    {
        Coach coach = new Coach();

        Random rand = new Random();
        int selection = (int) (rand.nextFloat() * 10);

        switch (selection)
        {
            case 0:
                coach.setName("Jwayne Dhonson");
                coach.setBio("Solia ser un luchador, ahora es un actor. Te ayuda a completar ese último set y mejorar tu presencia fisica en el escenario. Al mismo tiempo.");
                coach.setProfilePic(R.drawable.dj);
                break;
            case 1:
                coach.setName("Ponda Reusy");
                coach.setBio("Que esa mirada fuerte no te asuste, ella es la reyna de las rutinas de cardio y resistencia.");
                coach.setProfilePic(R.drawable.rr);
                break;
            case 2:
                coach.setName("Gary");
                coach.setBio("El es Gary. Si no estas seguro de que hacer solo llama a Gary. ¿Mencionamos que se llama Gary?");
                coach.setProfilePic(R.drawable.g);
                break;
            case 3:
                coach.setName("Cristian Orlando");
                coach.setBio("Él es a quien debes acudir si necesitas excelentes rutinas para pierna.");
                coach.setProfilePic(R.drawable.co);
                break;
            case 4:
                coach.setName("Michaela Marony");
                coach.setBio("Muy elastica, sabe de gimnasia y le gustan los batidos de manzana con menta. Extraña combinación.");
                coach.setProfilePic(R.drawable.mm);
                break;
            case 5:
                coach.setName("Joe Perez");
                coach.setBio("Ningún gimnasio estaria completo sin alguien como el. Todo el mundo lo cocnoce y le cae bien a todo el mundo");
                coach.setProfilePic(R.drawable.jd);
                break;
            case 6:
                coach.setName("Hans von Spatermeisterhoff");
                coach.setBio("Es extranjero y soloia ser un fisicoculturista. Resolvera todas tus dudas sobre que proteina comprar.");
                coach.setProfilePic(R.drawable.as);
                break;
            default:
                coach.setName("Jwayne Dhonson");
                coach.setBio("Solia ser un luchador, ahora es un actor. Te ayuda a completar ese último set y mejorar tu presencia fisica en el escenario. Al mismo tiempo.");
                coach.setProfilePic(R.drawable.dj);
                break;

        }

        return coach;
    }

    public class Coach
    {
        private int profilePic;
        private String name;
        private String bio;

        public int getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(int profilePic) {
            this.profilePic = profilePic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }
    }
}
