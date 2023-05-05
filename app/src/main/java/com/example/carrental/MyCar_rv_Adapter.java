package com.example.carrental;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyCar_rv_Adapter extends RecyclerView.Adapter<MyCar_rv_Adapter.rvMyCarViewHolderClass> {

    ArrayList<Car> CarListToDelete;
    int id;
    CarDataBase carDataBase;
    Context context;



    public MyCar_rv_Adapter(ArrayList<Car> carList , Context context) {
        CarListToDelete = carList;
        this.context = context;
    }

    @NonNull
    @Override
    public rvMyCarViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCar_rv_Adapter.rvMyCarViewHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_car_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull rvMyCarViewHolderClass holder, int position) {
        Car CarObject=CarListToDelete.get(position); // position to be deleted from the array
        holder.myCar_sr_carName.setText(CarObject.getName());
        holder.myCar_sr_iv_cars.setImageBitmap(CarObject.getImage());

    }


    @Override
    public int getItemCount() {
        return CarListToDelete.size();
    }

    public class rvMyCarViewHolderClass extends RecyclerView.ViewHolder {

        TextView myCar_sr_carName;
        ImageView myCar_sr_iv_cars;
        Button myCar_sr_delete , BackToHome;

        public rvMyCarViewHolderClass(@NonNull View itemView) {
            super(itemView);
            myCar_sr_carName = itemView.findViewById(R.id.myCar_sr_carName);
            myCar_sr_iv_cars = itemView.findViewById(R.id.myCar_sr_iv_cars);
            myCar_sr_delete = itemView.findViewById(R.id.myCar_sr_delete);
            BackToHome = itemView.findViewById(R.id.BackToHome);

            itemView.setClickable(true);

            myCar_sr_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    id = getAdapterPosition();
                    CarListToDelete.remove(id);
                    carDataBase = new CarDataBase(context);
                    carDataBase.deleteOne(id);
                    notifyItemRemoved(id);
                    notifyDataSetChanged();

                }
            });

            BackToHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context ,MainActivity.class);
                    context.startActivity(intent);

                }
            });

        }}}


/*
package com.example.carrental;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyCar_rv_Adapter extends RecyclerView.Adapter<MyCar_rv_Adapter.rvMyCarViewHolderClass> {

    ArrayList<Car> CarListToDelete;
    CarDataBase carDataBase;
    Context context;
    SQLiteDatabase db;



    public MyCar_rv_Adapter(ArrayList<Car> carList , Context context) {
        CarListToDelete = carList;
        this.context = context;
        carDataBase = new CarDataBase(context);
    }

    @NonNull
    @Override
    public rvMyCarViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCar_rv_Adapter.rvMyCarViewHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_car_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull rvMyCarViewHolderClass holder, int position) {
        Car CarObject=CarListToDelete.get(position); // position to be deleted from the array
        holder.myCar_sr_carName.setText(CarObject.getName());
        holder.myCar_sr_iv_cars.setImageBitmap(CarObject.getImage());

        holder.myCar_sr_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialog(CarObject);
            }
        });

    }

    private void deleteDialog(final Car car) {
        if (context != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Are you sure want to delete this car?");
            builder.setCancelable(false);
            builder.setIcon(R.drawable.ic_action_delete);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    carDataBase.deleteOne(car);
                    //((My_Cars)context).onResume();
                    Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

        }
        else
            Toast.makeText(context, "Context in null", Toast.LENGTH_SHORT).show();


    }

    @Override
    public int getItemCount() {
        return CarListToDelete.size();
    }

    public static class rvMyCarViewHolderClass  extends RecyclerView.ViewHolder{

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

 */