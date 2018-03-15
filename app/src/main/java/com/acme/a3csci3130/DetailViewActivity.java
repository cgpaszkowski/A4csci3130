package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DetailViewActivity extends Activity implements AdapterView.OnItemSelectedListener{

    private EditText businessNumField, nameField, addressField;
    private Spinner businessTypeField, provinceField;
    Business receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
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
        //TODO: Update contact funcionality
        String businessID = appState.firebaseReference.getKey();

        String businessNum = businessNumField.getText().toString();
        String name = nameField.getText().toString();
        Integer businessType = businessTypeField.getSelectedItemPosition();
        String address = addressField.getText().toString();
        Integer province = provinceField.getSelectedItemPosition();

        Business person = new Business(businessID, businessNum, name, businessType, address, province);

        appState.firebaseReference.updateChildren(businessID);
        //-----------ADD -----------------------------
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void eraseContact(View v)
    {
        String businessID = appState.firebaseReference.getKey();
        //TODO: Erase contact functionality
        //--------- ADD .delete()---------------
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
