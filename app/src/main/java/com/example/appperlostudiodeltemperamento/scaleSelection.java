package com.example.appperlostudiodeltemperamento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;


public class scaleSelection extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_selection);


        // data to populate the RecyclerView with
        ArrayList<String> scaleNames = new ArrayList<>();
        scaleNames.add("\n12EDO\n(Twelve-tone equal temperament)\n");
        scaleNames.add("\n24EDO\n(Quarter tone scale)\n");
        scaleNames.add("\n22EDO\n");
        scaleNames.add("\n11EDO\n");
        scaleNames.add("\n7EDO\n");
        scaleNames.add("\nPythagorean tuning\n");
        scaleNames.add("\nSyntonous diatonic scale\n(Zarlinian Scale or Ptolemy's intense diatonic scale)\n");
        scaleNames.add("\nAlpha scale\n");
        scaleNames.add("\nBohlen-Pierce scale\n");
        scaleNames.add("\nShí-èr-lǜ\n(Traditional 12 tone chinese tuning)\n");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerscales);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, scaleNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

        final Intent intent;
        switch (position){
            case 0:
                intent =  new Intent(this, EDO12keyboard.class);
                break;

            case 1:
                intent =  new Intent(this, EDO24keyboard.class);
                break;

            case 2:
                intent =  new Intent(this, EDO22keyboard.class);
                break;

            case 3:
                intent =  new Intent(this, EDO11keyboard.class);
                break;

            case 4:
                intent =  new Intent(this, EDO7keyboard.class);
                break;

            case 5:
                intent =  new Intent(this, Pitagorakeyboard.class);
                break;

            case 6:
                intent =  new Intent(this, Zarlinokeyboard.class);
                break;

            case 7:
                intent =  new Intent(this, Alphakeyboard.class);
                break;

            case 8:
                intent =  new Intent(this, BohlenPiercekeyboard.class);
                break;

            case 9:
                intent =  new Intent(this, Shierlukeyboard.class);
                break;

            default:
                intent =  new Intent(this, MainActivity.class);
                break;
        }

        this.startActivity(intent);
    }



}