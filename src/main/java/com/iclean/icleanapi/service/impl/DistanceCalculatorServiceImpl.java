package com.iclean.icleanapi.service.impl;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElementStatus;
import com.iclean.icleanapi.dto.Point;
import com.iclean.icleanapi.service.interf.DistanceCalculatorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DistanceCalculatorServiceImpl implements DistanceCalculatorService {

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;
    @Override
    public double calculateDistance(Point origin, Point destination) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(googleMapsApiKey)
                .build();
        try {
            DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context)
                    .origins(new com.google.maps.model.LatLng(origin.getLatitude(), origin.getLongitude()))
                    .destinations(new com.google.maps.model.LatLng(destination.getLatitude(), destination.getLongitude()))
                    .await();

            if (distanceMatrix.rows.length > 0 && distanceMatrix.rows[0].elements.length > 0) {
                if (distanceMatrix.rows[0].elements[0].status == DistanceMatrixElementStatus.OK) {
                    return distanceMatrix.rows[0].elements[0].distance.inMeters / 1000.0; // Distance in kilometers
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
