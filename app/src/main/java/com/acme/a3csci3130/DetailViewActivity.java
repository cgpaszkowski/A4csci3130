package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailViewActivity extends Activity implements AdapterView.OnItemSelectedListener{

    private Button deleteBtn, updateBtn;
    private EditText businessNumField, nameField, addressField;
    private Spinner businessTypeField, provinceField;
    Business receivedPersonInfo;
    private MyApplicationData appState;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        deleteBtn = (Button) findViewById(R.id.deleteButton);
        updateBtn = (Button) findViewById(R.id.updateInfoButton);

        receivedPersonInfo = (Business)getIntent().getSerializableExtra("Contact");
        businessNumField = (EditText) findViewById(R.id.businessNum);
        nameField = (EditText) findViewById(R.id.name);
        businessTypeField = (Spinner) findViewById(R.id.businessType);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (Spinner) findViewById(R.id.province);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.businessType_arrays, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        businessTypeField.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.province_arrays, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceField.setAdapter(adapter2);

        if(receivedPersonInfo != null){
            businessNumField.setText(receivedPersonInfo.businessNum);
            nameField.setText(receivedPersonInfo.name);
            businessTypeField.setSelection(receivedPersonInfo.businessType);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setSelection(receivedPersonInfo.province);
        }
    }

    public void updateContact(View v){
        String businessID = receivedPersonInfo.bid;
        String businessNum = businessNumField.getText().toString();
        String name = nameField.getText().toString();
        Integer businessType = businessTypeField.getSelectedItemPosition();
        String address = addressField.getText().toString();
        Integer province = provinceField.getSelectedItemPosition();

        Business person = new Business(businessID, businessNum, name, businessType, address, province);

        dbRef = FirebaseDatabase.getInstance().getReference("Business").child(businessID);
        dbRef.setValue(person);

        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void eraseContact(View v)
    {
        String businessID = receivedPersonInfo.bid;
        dbRef = FirebaseDatabase.getInstance().getReference("Business").child(businessID);
        dbRef.removeValue();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
