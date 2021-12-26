package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNote extends AppCompatActivity {
    public static final String EXTRA_ID= "com.example.noteapp.EXTRA_ID";
    public static final String EXTRA_TITLE= "com.example.noteapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION= "com.example.noteapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY= "com.example.noteapp.EXTRA_PRIORITY";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle= (EditText) findViewById(R.id.edit_text_title);
        editTextDescription= (EditText) findViewById(R.id.edit_text_description);
        numberPickerPriority= (NumberPicker) findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        
        Intent intent= getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }
        else{
            setTitle("Add Note");
        }
    }

    private void saveNote(){
        String title= editTextTitle.getText().toString();
        String description= editTextDescription.getText().toString();
        Integer priority= numberPickerPriority.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please Enter Title and Description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data= new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id= getIntent().getIntExtra(EXTRA_ID, -1);
        if(id!= -1){
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}