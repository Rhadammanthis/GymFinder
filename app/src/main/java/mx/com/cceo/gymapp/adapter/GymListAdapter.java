package mx.com.cceo.gymapp.adapter;

import android.support.v7.widget.CardView;
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
public class GymListAdapter extends  RecyclerView.Adapter<GymListAdapter.ViewHolderGymList> {


    private ArrayList<GymList> dataSet = new ArrayList<>();
    private GymListOnClickListener gymListOnClickListener;

    public GymListAdapter(ArrayList<GymList> dataSet, GymListOnClickListener onClickListener) {
        this.dataSet = dataSet;
        this.gymListOnClickListener = onClickListener;
    }

    @Override
    public ViewHolderGymList onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_gym_list, parent, false);
        ViewHolderGymList vH = new ViewHolderGymList(view);

        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolderGymList holder, final int position) {

        holder.tvName.setText(dataSet.get(position).getName());
        holder.ivImage.setImageResource(dataSet.get(position).getImageResource());

        holder.rlBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gymListOnClickListener.OnClickListener(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolderGymList extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvName;
        public ImageView ivImage;
        public RelativeLayout rlBody;

        public ViewHolderGymList(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.list_item_gym_list_name);
            ivImage = (ImageView) v.findViewById(R.id.list_item_gym_list_image);
            rlBody = (RelativeLayout) v.findViewById(R.id.list_item_gym_list_body);
        }
    }
}
