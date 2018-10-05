package com.srn.api.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class FormatterUtils {
    public static Timestamp getCurrentTimestamp() {
        Timestamp in = new Timestamp(new Date().getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Timestamp out = Timestamp.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        return out;
    }

    public static long getLongCurrentTimestamp() {
        Timestamp in = new Timestamp(new Date().getTime());
        return in.toInstant().toEpochMilli();
    }


}