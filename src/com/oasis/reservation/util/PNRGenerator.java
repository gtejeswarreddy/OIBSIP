package com.oasis.reservation.util;

import java.util.UUID;

public class PNRGenerator {

    public static String generatePNR() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
