package mx.com.cceo.gymapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.cceo.gymapp.Model.GymList;
import mx.com.cceo.gymapp.R;
import mx.com.cceo.gymapp.linker.GymListOnClickListener;

/**
 * Created by Hugo on 10/28/2015.
 */
public class GridAdapter extends  RecyclerView.Adapter<GridAdapter.ViewHolderGrid> {


    private ArrayList<Integer> dataSet = new ArrayList<>();

    public GridAdapter(ArrayList<Integer> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolderGrid onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_grid, parent, false);
        ViewHolderGrid vH = new ViewHolderGrid(view);

        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolderGrid holder, final int position) {

        holder.ivImage.setImageResource(dataSet.get(position));

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolderGrid extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView ivImage;

        public ViewHolderGrid(View v) {
            super(v);
            ivImage = (ImageView) v.findViewById(R.id.list_item_grid_image_view);
        }
    }
}
