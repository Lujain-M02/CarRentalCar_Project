package com.example.carrental;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyCar_rv_Adapter extends RecyclerView.Adapter<MyCar_rv_Adapter.rvMyCarViewHolderClass> {

    ArrayList<Car> CarListToDelete;

    public MyCar_rv_Adapter(ArrayList<Car> carList) {
        CarListToDelete = carList;
    }

    @NonNull
    @Override
    public rvMyCarViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCar_rv_Adapter.rvMyCarViewHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_car_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull rvMyCarViewHolderClass holder, int position) {
        Car CarObject=CarListToDelete.get(position);
        holder.myCar_sr_carName.setText(CarObject.getName());
        holder.myCar_sr_iv_cars.setImageBitmap(CarObject.getImage());
    }

    @Override
    public int getItemCount() {
        return CarListToDelete.size();
    }

    public static class rvMyCarViewHolderClass extends RecyclerView.ViewHolder{

        TextView myCar_sr_carName;
        ImageView myCar_sr_iv_cars;
        Button myCar_sr_delete;

        public rvMyCarViewHolderClass(@NonNull View itemView) {
            super(itemView);
            myCar_sr_carName=itemView.findViewById(R.id.myCar_sr_carName);
            myCar_sr_iv_cars=itemView.findViewById(R.id.myCar_sr_iv_cars);
            myCar_sr_delete=itemView.findViewById(R.id.myCar_sr_delete);

        }
    }

}
