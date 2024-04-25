package com.example.carecareforeldres.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceMapBox implements IServiceMapbox{
    private final String MAPBOX_API_BASE_URL = "https://api.mapbox.com";
    private final String MAPBOX_API_TOKEN = "pk.eyJ1IjoibW9oYW1lZGFtaW5lMTIiLCJhIjoiY2x0dXF1Ynd6MTQwbTJrcDdjcGRjYWI2cSJ9.BiVcBEegSfO2Q2W7pfbJJg";
    @Override
    public Map<String , String> getInfo(String x1 , String y1 , String x2 , String y2) throws IOException {
        URL url = new URL("https://api.mapbox.com/directions/v5/mapbox/driving/"+x1+"%2C"+y1+"%3B"+x2+"%2C"+y2+"?access_token=pk.eyJ1IjoibW9oYW1lZGFtaW5lMTIiLCJhIjoiY2x0dXF1Ynd6MTQwbTJrcDdjcGRjYWI2cSJ9.BiVcBEegSfO2Q2W7pfbJJg");
        Map<String, String> result = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Object object = objectMapper.readValue(url, Object.class);
        ObjectMapper mapper = new ObjectMapper();
        String jsonstr = mapper.writeValueAsString(object);
        JsonNode json = mapper.readTree(jsonstr);
        JsonNode routes = json.get("routes");
        JsonNode firstRoute = routes.get(0);
        JsonNode legs = firstRoute.get("legs");
        JsonNode firstLeg = legs.get(0);
        JsonNode distance = firstLeg.get("distance");
        JsonNode duration = firstLeg.get("duration");
// get distance value as double in km

        int seconds = (duration.asInt())%60;
        int minutes = (duration.asInt()/60) %60;
        int hours = (duration.asInt()/(60*60)) %60;

        double d = distance.asDouble()/1000;
        String durationn = hours+" hour"+" "+minutes+" "+"minutes,"+" "+seconds+" "+"Secondes ";
        String distancee = " "+d+" "+"km";
        result.put("Distance" , distancee);
        result.put("Duration" , durationn);
        return result;
    }

    @Override
    public String getAddressFromCoordinates(Double longitude, Double latitude) throws IOException {
        String apiUrl = MAPBOX_API_BASE_URL + "/geocoding/v5/mapbox.places/" + longitude + "," + latitude + ".json?access_token=" + MAPBOX_API_TOKEN;
        URL url = new URL(apiUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(url);

        JsonNode features = response.get("features");
        if (features.isArray() && features.size() > 0) {
            JsonNode firstFeature = features.get(0);
            JsonNode properties = firstFeature.get("properties");
            String street = properties.has("address") ? properties.get("address").asText() : "N/A";
            String postalCode = properties.has("postcode") ? properties.get("postcode").asText() : "N/A";
            String placeName = firstFeature.has("place_name") ? firstFeature.get("place_name").asText() : "N/A";

            String detailedAddress = placeName + ", " + street + ", " + postalCode;
            return detailedAddress;
        } else {
            return "Address not found";
        }
    }

}
