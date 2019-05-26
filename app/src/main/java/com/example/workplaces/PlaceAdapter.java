package com.example.workplaces;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    private ArrayList<Place> places;
    private Resources res;
    private Drawable drawable;


    public PlaceAdapter(Context context, ArrayList<Place> places){
        this.places = places;
        this.res = context.getResources();

    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent,  false);
        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        final Place p = places.get(position);
        holder.name.setText(p.getNombre());
        holder.location.setText("Bloque: "+p.getBloque()+" piso: "+p.getPiso());
         //Valido el color a mostrar el indicador

        drawable = ResourcesCompat.getDrawable(res, R.drawable.ic_indicator_24dp,null);
        holder.indicador.setImageDrawable(drawable);
        if (p.getDisponible()){
            holder.indicador.setColorFilter(Color.GREEN);
        }else{
            holder.indicador.setColorFilter(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView location;
        private ImageView indicador;

        public PlaceViewHolder( View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_txt_name);
            location = (TextView) itemView.findViewById(R.id.item_txt_location);
            indicador = (ImageView)itemView.findViewById(R.id.item_indicador);
        }
    }

}
