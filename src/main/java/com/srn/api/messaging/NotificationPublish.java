package com.srn.api.messaging;
/*
 Author: vikraa
 created: 1/2/19 - 11:59 PM
*/

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.ArrayMap;
import com.sun.xml.internal.ws.developer.Serialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class NotificationPublish {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationPublish.class);
    private static String TAG = NotificationPublish.class.getSimpleName();

    @Value("${google.fcm.url}")
    private String fcmUrl;

    @Value("${google.fcm.key}")
    private String fcmKey;

    public void sendRedeemNotificationResult(String fcmId, int redeemStatus, String message) {
        try {
            URL url = new URL(fcmUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", fcmKey);

            FcmPayload payload = new FcmPayload();
            List<String> fcmList = new ArrayList<>();
            fcmList.add(fcmId);
            Map<String, String> data = new ArrayMap<>();
            data.put("key","redeem");
            data.put("redeemStatus", String.valueOf(redeemStatus));
            data.put("message", message);
            payload.setRegistration_ids(fcmList);
            payload.setData(data);
            payload.setPriority("high");

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write(payload.toString());
            writer.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonString = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            LOGGER.info("FCM Result --> {}", jsonString.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class FcmPayload implements Serializable {
        private List<String> registration_ids;
        private String priority;
        private Map<String, String> data;

        public List<String> getRegistration_ids() {
            return registration_ids;
        }

        public void setRegistration_ids(List<String> registration_ids) {
            this.registration_ids = registration_ids;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        public Map<String, String> getData() {
            return data;
        }

        public void setData(Map<String, String> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            String jsonString = "";
            ObjectMapper mapper = new ObjectMapper();
            try {
                jsonString = mapper.writeValueAsString(this);
            }catch (JsonGenerationException exJsonGeneration) {
                exJsonGeneration.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return jsonString;
        }
    }

}