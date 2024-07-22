package com.example.kdu;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;

import android.os.Bundle;

public class details extends AppCompatActivity {
    TextView name, email, address, phone;
    Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        name = findViewById(R.id.textView_name);
        email = findViewById(R.id.textView_email);
        phone = findViewById(R.id.textView_phone);
        address = findViewById(R.id.textView_address);
        Logout = findViewById(R.id.logout);

        Intent j = getIntent();
        String userName = j.getStringExtra("name");
        String userEmail = j.getStringExtra("email");
        int userPhone = j.getIntExtra("phone",0);
        String userAddress = j.getStringExtra("address");

        name.setText ("Hello This is "+ userName);
        email.setText("Email address is "+ userEmail);
        phone.setText("Your Phone number is "+ String.valueOf(userPhone));
        address.setText("Your address is " + userAddress);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(details.this,MainActivity.class);
                startActivity(k);
            }
        });
    }
}