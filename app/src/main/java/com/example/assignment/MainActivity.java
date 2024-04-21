package com.example.assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;

    EditText name,email;

    Button register,show;
    StringBuilder records;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        spinner = findViewById(R.id.spinner);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.position, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedItem = spinner.getSelectedItem().toString();

                if(selectedItem.equals("Employee")){

                if(!name.getText().toString().isEmpty() && !email.getText().toString().isEmpty()){
                    boolean res = databaseHelper.insertEmp(name.getText().toString(),email.getText().toString());
                    if(res == true)
                        Toast.makeText(MainActivity.this,"Employee record inserted",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this,"Employee record not inserted",Toast.LENGTH_LONG).show();
                }
                } else if (selectedItem.equals("Manager")) {
                    if(!name.getText().toString().isEmpty() && !email.getText().toString().isEmpty()){
                        boolean res = databaseHelper.insertMan(name.getText().toString(),email.getText().toString());
                        if(res == true)
                            Toast.makeText(MainActivity.this,"Manager record inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Manager record not inserted",Toast.LENGTH_LONG).show();
                    }
                } else if (selectedItem.equals("Director")) {
                    if(!name.getText().toString().isEmpty() && !email.getText().toString().isEmpty()){
                        boolean res = databaseHelper.insertDir(name.getText().toString(),email.getText().toString());
                        if(res == true)
                            Toast.makeText(MainActivity.this,"Director record inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Director record not inserted",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        show = findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedItem = spinner.getSelectedItem().toString();
                records = new StringBuilder();
                if(selectedItem.equals("Employee")){
                    Cursor result = databaseHelper.showEmprec();
                    while (result.moveToNext()){
                        records.append("ID: "  + result.getString(0));
                        records.append("\nName: "  + result.getString(1));
                        records.append("\nEmail: "  + result.getString(2));
                        records.append("\n");
                    }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Employee Records:");
                    builder.setMessage(records);
//                    builder.setCancelable(true);
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else if (selectedItem.equals("Manager")) {
                    Cursor result = databaseHelper.showManrec();
                    while (result.moveToNext()){
                        records.append("ID: "  + result.getString(0));
                        records.append("\nName: "  + result.getString(1));
                        records.append("\nEmail: "  + result.getString(2));
                        records.append("\n");
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Manager Records:");
                    builder.setMessage(records);
//                    builder.setCancelable(true);
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else if (selectedItem.equals("Director")) {
                    Cursor result = databaseHelper.showDir();
                    while (result.moveToNext()){
                        records.append("ID: "  + result.getString(0));
                        records.append("\nName: "  + result.getString(1));
                        records.append("\nEmail: "  + result.getString(2));
                        records.append("\n");
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Director Records:");
                    builder.setMessage(records);
//                    builder.setCancelable(true);
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }
}