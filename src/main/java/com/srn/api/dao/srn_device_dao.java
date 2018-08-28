/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srn.api.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class srn_device_dao {
    private Connection conn = null;
    
    public String getAllDeviceValidation(){
        String jsonResponse = "";
    
        int id;
        String model = "";
        String imei = "";
        String manufacture = "";
        String osversion = "";
        String fcm_id = "";
        Timestamp created;
        Timestamp last_updated;
        
        Statement stmt = null;
        ResultSet rs;
        
        JSONObject JSONObjectRoot = new JSONObject();
        JSONArray SRN_DEVICE_DATA = new JSONArray();
        
        try {
            dbConnection DC = new dbConnection(); // class conn updated soon 
            conn = DC.getConnection();
             
            stmt = conn.createStatement();
            String query = "SELECT id, model, imei, manufacture, osversion, fcm_id, created, last_updated FROM srn_device";
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                id = rs.getInt("ID");
                model = (rs.getString("MODEL") != null) ? rs.getString("MODEL") : "";
                imei = (rs.getString("IMEI") != null) ? rs.getString("IMEI") : "";
                manufacture = (rs.getString("MANUFACTURE") != null) ? rs.getString("MANUFACTURE") : "";
                osversion = (rs.getString("OSVERSION") != null) ? rs.getString("OSVERSION") : "";
                fcm_id = (rs.getString("FCM_ID") != null) ? rs.getString("FCM_ID") : "";
                created = rs.getTimestamp("CREATED");
                last_updated = rs.getTimestamp("LAST_UPDATED");
                
                JSONObject SRN_DEVICE_VAL = new JSONObject();
                
                SRN_DEVICE_VAL.put("ID", id);
                SRN_DEVICE_VAL.put("MODEL", new String(model));
                SRN_DEVICE_VAL.put("IMEI", new String(imei));
                SRN_DEVICE_VAL.put("MANUFACTURE", new String(manufacture));
                SRN_DEVICE_VAL.put("OSVERSION", new String(osversion));
                SRN_DEVICE_VAL.put("FCM_ID", new String(fcm_id));
                SRN_DEVICE_VAL.put("CREATED", new String(created.toString()));
                SRN_DEVICE_VAL.put("LAST_UPDATED", new String(last_updated.toString()));
                
                SRN_DEVICE_DATA.put(SRN_DEVICE_VAL);
            }
            
            JSONObjectRoot.put("SRN_DEVICE_DATA", SRN_DEVICE_DATA);
            jsonResponse += JSONObjectRoot.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        return jsonResponse;
    }
}
