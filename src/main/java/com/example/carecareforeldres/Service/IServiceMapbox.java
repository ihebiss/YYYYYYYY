package com.example.carecareforeldres.Service;

import java.io.IOException;
import java.util.Map;

public interface IServiceMapbox {
    public Map<String, String> getInfo(String x1, String y1, String x2, String y2) throws IOException;
    public String getAddressFromCoordinates(Double longitude, Double latitude) throws IOException;

}