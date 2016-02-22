package mx.com.cceo.gymapp.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.com.cceo.gymapp.GymDetailActivity;
import mx.com.cceo.gymapp.Model.GymList;
import mx.com.cceo.gymapp.R;
import mx.com.cceo.gymapp.adapter.GymListAdapter;
import mx.com.cceo.gymapp.linker.GymListOnClickListener;

/**
 * Created by Hugo on 10/27/2015.
 */
public class GymListFragment extends DialogFragment implements GymListOnClickListener {

    Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<GymList> dataSet;

    public void setContext(Context context)
    {
        this.context = context;
    }

    public void setDataSet(ArrayList<GymList> dataSet)
    {
        this.dataSet = dataSet;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Dialog dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.fragment_gym_list, null);

        recyclerView = (RecyclerView) v.findViewById(R.id.fragment_gym_list_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mAdapter = new GymListAdapter(dataSet, this);
        recyclerView.setAdapter(mAdapter);

        final Drawable d = new ColorDrawable(Color.BLACK);
        d.setAlpha(180);

        dialog.getWindow().setBackgroundDrawable(d);

        dialog.getWindow().setContentView(v);

        return dialog;
    }

    @Override
    public void OnClickListener(View v, int position) {
        //Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), GymDetailActivity.class);
        intent.putExtra("name", dataSet.get(position).getName());
        getActivity().startActivity(intent);
        this.dismiss();
    }
}
