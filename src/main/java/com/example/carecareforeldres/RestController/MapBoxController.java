package com.example.carecareforeldres.RestController;

import com.example.carecareforeldres.Service.IServiceMapbox;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/map")

public class MapBoxController {
    private IServiceMapbox iServiceMapBox;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/MapBox/{x1}/{y1}/{x2}/{y2}")
    public Map<String , String> getInfo(@PathVariable String x1 ,@PathVariable String y1 ,@PathVariable String x2 , @PathVariable String y2) throws IOException{
        return iServiceMapBox.getInfo(x1,y1,x2,y2);
    }
    @GetMapping("/getAddress/{longitude}/{latitude}")
    public String getAddressFromCoordinates(@PathVariable Double longitude, @PathVariable Double latitude) throws IOException {
        return iServiceMapBox.getAddressFromCoordinates(longitude, latitude);
    }

}
