package com.cheatbreaker.bridge.util;

public interface Vec3Bridge {
    float bridge$getXCoord();
    float bridge$getYCoord();
    float bridge$getZCoord();
    double bridge$lengthVector();
    Vec3Bridge bridge$crossProduct(Vec3Bridge vec3);
    Vec3Bridge bridge$normalize();
    double bridge$dotProduct(Vec3Bridge vec3Bridge);
}
