package com.iclean.icleanapi.service.interf;

import com.iclean.icleanapi.dto.Point;

public interface DistanceCalculatorService {
    double calculateDistance(Point origin, Point destination);
}
