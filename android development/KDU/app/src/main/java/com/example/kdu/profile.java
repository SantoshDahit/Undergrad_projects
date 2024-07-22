package com.example.kdu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class profile extends AppCompatActivity {
    EditText name, email, phone, address;
    Button save;
    TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.edittext_name);
        email = findViewById(R.id.edittext_email);
        phone = findViewById(R.id.editTextPhone);
        address = findViewById(R.id.edittextaddress);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = name.getText().toString();
                String userEmail = email.getText().toString();
                int userPhone = Integer.valueOf(phone.getText().toString());
                String userAddress = address.getText().toString();
                Intent j = new Intent(profile.this,details.class);
                startActivity(j);

                j.putExtra("name",userName);
                j.putExtra("email",userEmail);
                j.putExtra("phone", userPhone);
                j.putExtra("address",userAddress);
                startActivity(j);
            }
        });
    }
}