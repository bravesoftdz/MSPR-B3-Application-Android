package com.example.projetmspr;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //Définition de la vue principale
       /* ImageView imageView = (ImageView) findViewById(R.id.coupon1);   //Appel de l'imageView2"
        imageView.setOnClickListener(new View.OnClickListener()      //Creation du listener sur ce bouton
        {
            public void onClick(View actuelView)    //au clic sur le bouton
            {
                Intent intent = new Intent(MainActivity.this, Coupon.class);  //Lancer l'activité Coupon
                startActivity(intent);    //Afficher la vue
            }
        });
    }*/
    }
}