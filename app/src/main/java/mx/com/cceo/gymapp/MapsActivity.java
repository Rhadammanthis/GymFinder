package mx.com.cceo.gymapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.*;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mx.com.cceo.gymapp.Model.Gym;
import mx.com.cceo.gymapp.Model.GymList;
import mx.com.cceo.gymapp.fragment.GymListFragment;
import mx.com.cceo.gymapp.util.MultiDrawable;


public class MapsActivity extends FragmentActivity implements ClusterManager.OnClusterClickListener<Gym>, ClusterManager.OnClusterInfoWindowClickListener<Gym>,
        ClusterManager.OnClusterItemClickListener<Gym>, ClusterManager.OnClusterItemInfoWindowClickListener<Gym>{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available
    private ClusterManager<Gym> mClusterManager;
    private Random mRandom = new Random(1984);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        this.setTitle("Mapa");
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpCluster();
            }
        }
    }

    private void setUpCluster() {
        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(21.884115, -102.291805));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(13.5f);
//
//        MarkerOptions AGS = new MarkerOptions().position(new LatLng(21.859725, -102.267965)).title("Marcador");
//        mMap.addMarker(AGS);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);

        // Declare a variable for the cluster manager.


        // Position the map.
        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<Gym>(this, mMap);
        mClusterManager.setRenderer(new GymRenderer());
        mMap.setOnCameraChangeListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setOnInfoWindowClickListener(mClusterManager);
        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);

        addItems();
        mClusterManager.cluster();

    }

    private void addItems() {
        // http://www.flickr.com/photos/sdasmarchives/5036248203/
        mClusterManager.addItem(new Gym(new LatLng(21.859652, -102.267782), "Gold Gym", R.drawable.gold_logo));

        // http://www.flickr.com/photos/usnationalarchives/4726917149/
        mClusterManager.addItem(new Gym(new LatLng(21.866134, -102.280918), "SF Fitness", R.drawable.sf_logo));

        // http://www.flickr.com/photos/nypl/3111525394/
        mClusterManager.addItem(new Gym(new LatLng(21.862549, -102.282452), "Studiomix", R.drawable.studio_logo));

        // http://www.flickr.com/photos/smithsonian/2887433330/
        mClusterManager.addItem(new Gym(new LatLng(21.875772, -102.296528), "Virgin Active", R.drawable.virgin_logo));

        // http://www.flickr.com/photos/library_of_congress/2179915182/
        mClusterManager.addItem(new Gym(new LatLng(21.877245, -102.280778), "Ludlow Fitness", R.drawable.ludlow_logo));

        // http://www.flickr.com/photos/nationalmediamuseum/7893552556/
        mClusterManager.addItem(new Gym(new LatLng(21.875772, -102.296528), "Chalk Gyms", R.drawable.chalk_logo));

        // http://www.flickr.com/photos/sdasmarchives/5036231225/
        mClusterManager.addItem(new Gym(new LatLng(21.873940, -102.308458), "McFIT", R.drawable.mc_logo));

        // http://www.flickr.com/photos/anmm_thecommons/7694202096/
        mClusterManager.addItem(new Gym(new LatLng(21.879954, -102.312321), "ENERGi Fitness", R.drawable.energi_logo));

        // http://www.flickr.com/photos/usnationalarchives/4726892651/
        mClusterManager.addItem(new Gym(new LatLng(21.892842, -102.277276), "Fitness 19", R.drawable.nine_logo));
    }

    @Override
    public boolean onClusterClick(Cluster<Gym> cluster) {
//        String firstName = cluster.getItems().iterator().next().name;
//        Toast.makeText(this, cluster.getSize() + " (including " + firstName + ")", Toast.LENGTH_SHORT).show();
        ArrayList<GymList> dataSet = new ArrayList<>();

        for (Gym p : cluster.getItems()) {
            GymList gymList = new GymList(p.profilePhoto, p.name);

            dataSet.add(gymList);
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);

        GymListFragment gymListFragment = new GymListFragment();
        gymListFragment.setContext(getApplicationContext());
        gymListFragment.setDataSet(dataSet);
        //gymListFragment.show(getFragmentManager(), "gymList");

        ft.add(gymListFragment,"frag");
        ft.commit();
        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<Gym> cluster) {
    }

    @Override
    public boolean onClusterItemClick(Gym gym) {
        Intent intent = new Intent(MapsActivity.this, GymDetailActivity.class);
        intent.putExtra("name", gym.name);
        startActivity(intent);
        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(Gym gym) {
    }

    private class GymRenderer extends DefaultClusterRenderer<Gym> {
        private final IconGenerator mIconGenerator = new IconGenerator(getApplicationContext());
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());
        private final ImageView mImageView;
        private final ImageView mClusterImageView;
        private final int mDimension;

        public GymRenderer() {
            super(getApplicationContext(), mMap, mClusterManager);

            View multiProfile = getLayoutInflater().inflate(R.layout.multi_profile, null);
            mClusterIconGenerator.setContentView(multiProfile);
            mClusterImageView = (ImageView) multiProfile.findViewById(R.id.image);

            mImageView = new ImageView(getApplicationContext());
            mDimension = (int) getResources().getDimension(R.dimen.custom_profile_image);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
            int padding = (int) getResources().getDimension(R.dimen.custom_profile_padding);
            mImageView.setPadding(padding, padding, padding, padding);
            mIconGenerator.setContentView(mImageView);
        }

        @Override
        protected void onBeforeClusterItemRendered(Gym gym, MarkerOptions markerOptions) {
            // Draw a single person.
            // Set the info window to show their name.
            mImageView.setImageResource(gym.profilePhoto);
            Bitmap icon = mIconGenerator.makeIcon();
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(gym.name);

        }

        @Override
        protected void onBeforeClusterRendered(Cluster<Gym> cluster, MarkerOptions markerOptions) {
            // Draw multiple people.
            // Note: this method runs on the UI thread. Don't spend too much time in here (like in this example).
            List<Drawable> profilePhotos = new ArrayList<Drawable>(Math.min(4, cluster.getSize()));
            int width = mDimension;
            int height = mDimension;

            for (Gym p : cluster.getItems()) {
                // Draw 4 at most.
                if (profilePhotos.size() == 4) break;
                Drawable drawable = getResources().getDrawable(p.profilePhoto);
                drawable.setBounds(0, 0, width, height);
                profilePhotos.add(drawable);
            }

            MultiDrawable multiDrawable = new MultiDrawable(profilePhotos);
            multiDrawable.setBounds(0, 0, width, height);

            mClusterImageView.setImageDrawable(multiDrawable);
            Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }

        @Override
        protected boolean shouldRenderAsCluster(Cluster cluster) {
            // Always render clusters.
            return cluster.getSize() > 1;
        }
    }
}
