package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class CreateContactActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Button submitButton;
    private EditText businessNumField, nameField, addressField;
    private Spinner businessTypeField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.createBusinessButton);
        businessNumField = (EditText) findViewById(R.id.businessNum);
        nameField = (EditText) findViewById(R.id.name);
        businessTypeField = (Spinner) findViewById(R.id.businessType);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (Spinner) findViewById(R.id.province);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.businessType_arrays, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        businessTypeField.setPrompt("Select Business Type");
        businessTypeField.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.province_arrays, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceField.setPrompt("Select Province");
        provinceField.setAdapter(adapter2);


    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID

        String businessID = appState.firebaseReference.push().getKey();

        String businessNum = businessNumField.getText().toString();
        String name = nameField.getText().toString();
        Integer businessType = businessTypeField.getSelectedItemPosition();
        String address = addressField.getText().toString();
        Integer province = provinceField.getSelectedItemPosition();

        Business person = new Business(businessID, businessNum, name, businessType, address, province);

        appState.firebaseReference.child(businessID).setValue(person);

        finish();

    }

}
