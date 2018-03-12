package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase database. This is converted to a JSON format
 */

public class Business implements Serializable {

    public String bid;
    public String businessNum;
    public String name;
    public Integer businessType;
    public String address;
    public Integer province;

    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Business(String bid, String businessNum, String name, Integer businessType, String address, Integer province){
        this.bid = bid;
        this.businessNum = businessNum;
        this.name = name;
        this.businessType = businessType;
        this.address = address;
        this.province = province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("bid", bid);
        result.put("businessNum", businessNum);
        result.put("name", name);
        result.put("businessType", businessType);
        result.put("address", address);
        result.put("province", province);
        return result;
    }
}
