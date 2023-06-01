package com.example.carrental;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    int id;
    CarDataBase carDataBase;
    Context context;
    Car CarObject , co;


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
    public void onBindViewHolder(@NonNull final rvMyCarViewHolderClass holder, int position) {
        CarObject=CarListToDelete.get(position); // position to be deleted from the array
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
        Button myCar_sr_delete;;

        public rvMyCarViewHolderClass(@NonNull View itemView) {
            super(itemView);
            myCar_sr_carName = itemView.findViewById(R.id.myCar_sr_carName);
            myCar_sr_iv_cars = itemView.findViewById(R.id.myCar_sr_iv_cars);
            myCar_sr_delete = itemView.findViewById(R.id.myCar_sr_delete);

            itemView.setClickable(true);


            myCar_sr_delete.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Confirm Delete");
                    builder.setMessage("Are you sure you want to delete this car?");

                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });




                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                    id = getAdapterPosition();
                                    co = CarListToDelete.get(id);
                                    if((carDataBase.deleteOne(co.getId()) != null)){

                                       if ((carDataBase.deleteOne(co.getId()) >= 0)) {
                                       Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
                                       CarListToDelete.remove(id); // id here represent the index
                                       notifyItemRemoved(id);
                                       notifyDataSetChanged();

                                    }else
                                    Toast.makeText(context, "Could not be deleted", Toast.LENGTH_SHORT).show();
                            }     else{
                                        myCar_sr_delete.setEnabled(false);
                                        Toast.makeText(context, "Could not be deleted , car is rented", Toast.LENGTH_SHORT).show();}
                        }});

                    AlertDialog dialog = builder.create();
                    dialog.show();

                    }});
                    }




        }}

