package com.desafiolatam.reciclerviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.desafiolatam.reciclerviewexample.model.AnimalPojo;
import java.util.ArrayList;
import java.util.List;

public class AnimalAdapters extends RecyclerView.Adapter<AnimalAdapters.ViewHolder> {
    //  SE DECLARAN LOS METODOS QUE SE USARAN PARA OBTENER LOS DATOS DE LA DATA

    private LayoutInflater mLayoutInflater;
    //RECIBE UN LISTADO DE ANIMALES
    private List<AnimalPojo> animalPojoList = new ArrayList<>();//dataset
    //CONTEXTO DE DONDE TRAE LOS DATOS
    private Context mContext;
    //PARA CON EL CLICK IR A UN SITIO ESPECIFICO O ALGO PASE
    private OnItemClickListener listener;

//constructor list y pojo


    public AnimalAdapters(List<AnimalPojo> animalPojoList, Context mContext, OnItemClickListener listener) {
        this.animalPojoList = animalPojoList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override //"el layaout es : "
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //SE DECLARA UN view = VARIABLE DEFINIDA ARRIBA,LAYOUT, UN VIEWGROUP Y UN FALSE
        View view =mLayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_animal,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AnimalPojo animalPojo = animalPojoList.get(position);
        //pasar los datos que se van a mostrar

        holder.textView.setText(animalPojo.getName());
//libreria.metodo.(contexto de conde viene)
        Glide.with(holder.imageView.getContext())
                .load(animalPojo.getUrl())//pasandole la url
                .centerCrop()//centrar la foto
                .into(holder.imageView);//donde se va a mostrar la imagen(holder.imageview)
    }
    @Override
    public int getItemCount() {
        //se obtiene el numero de la lista
        return animalPojoList.size();
    }
    //ESTA ES UNA CLASE INTERNA, CUANDO SE INSTENCIE AnimalAdapters LO HARA CON ESTA CLASE INTERNA
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//DECLARACION Y ENLACE ENTRE EL LAYAOUT (id)
             imageView = itemView.findViewById(R.id.imageViewListItem);
             textView = itemView.findViewById(R.id.textViewListItem);

            itemView.setOnClickListener(this);//para realizar la implementacion alt+ enter make viewholder, se escoje el metodo y te autocompleta el metodo y se crea un onclick view v
        }

        @Override// pasar el adaptador a la interface
        public void onClick(View v) {
            listener.onClick(this,getNameByPosition(getAdapterPosition())
            , getURLByPosition(getAdapterPosition()));
        }
    }

    private String getNameByPosition(int position){
        if(position!= RecyclerView.NO_POSITION){
            return  animalPojoList.get(position).getName();
        } else{
            return "No Hay";
        }
    }

    private String getURLByPosition(int position){
        if(position!= RecyclerView.NO_POSITION){
            return  animalPojoList.get(position).getUrl();
        } else{
            return "No Hay";
        }
    }

public interface OnItemClickListener{
        public void onClick(AnimalAdapters.ViewHolder viewHolder, String nameAnimal, String Url);
}
}
