package com.example.carrental;

import android.app.AlertDialog;
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
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;


public class Rental_Adapter extends RecyclerView.Adapter<Rental_Adapter.rvMyCarViewHolderClass> {

    ArrayList<RentalApplication> CarListToReturn; // All car rented by a renter
    CarDataBase carDataBase;
    Context context;
    RentalApplication rentObject ,ro;
    int id;
    Intent intent;

    public Rental_Adapter(ArrayList<RentalApplication> RentList , Context context){
        CarListToReturn=RentList;
        this.context = context;
        carDataBase = new CarDataBase(context);
    }

    @NonNull
    @Override
    public rvMyCarViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Rental_Adapter.rvMyCarViewHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.rental_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Rental_Adapter.rvMyCarViewHolderClass holder, int position) {
        rentObject=CarListToReturn.get(position); // position to be return from the array
        /// to get car information
        Car carObject = carDataBase.getCarObject(rentObject.getCar_id());
            if(carObject != null) {

            holder.caRental_sr_CarName.setText(carObject.getName());
            holder.myCar_sr_iv_cars.setImageBitmap(carObject.getImage());
            }
            else{
                intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                Toast.makeText(context, "List is empty", Toast.LENGTH_SHORT).show();
            }

    }

    @Override
    public int getItemCount() {
        return CarListToReturn.size();
    }

    public class rvMyCarViewHolderClass extends RecyclerView.ViewHolder {

        ImageView myCar_sr_iv_cars;
        TextView caRental_sr_CarName;
        Button myCar_sr_retrun;

        public rvMyCarViewHolderClass(@NonNull View itemView) {
            super(itemView);
            caRental_sr_CarName = itemView.findViewById(R.id.caRental_sr_CarName);
            myCar_sr_iv_cars = itemView.findViewById(R.id.myCar_sr_iv_cars);
            myCar_sr_retrun = itemView.findViewById(R.id.myCar_sr_retrun);

            itemView.setClickable(true);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            myCar_sr_retrun.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {


                    builder.setTitle("Confirm Returning");
                    builder.setMessage("Are you sure you want to return this car?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {



                    id = getAdapterPosition();
                    ro = CarListToReturn.get(id);
                    CarListToReturn.remove(id); // id here represent the index
                    if(carDataBase.ReturnOne(ro.getId()) > 0)
                        Toast.makeText(context, "Return successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context, "Could not be returned", Toast.LENGTH_SHORT).show();

                    notifyItemRemoved(id);
                    notifyDataSetChanged();

                        }
                    });

                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }});




        }}}

