package com.ums.management.core.utility;

import org.apache.commons.codec.binary.Base64;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Created by Rex on 2017/4/23.
 */
public class UUIDExtension {
    public static String uuidToBase64(UUID uuid) {
        ByteBuffer buf = ByteBuffer.wrap(new byte[16]);
        buf.putLong(uuid.getMostSignificantBits());
        buf.putLong(uuid.getLeastSignificantBits());
        return Base64.encodeBase64URLSafeString(buf.array());
    }

    public static UUID uuidFromBase64(String str) {
        byte[] bytes = Base64.decodeBase64(str);
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        UUID uuid = new UUID(buf.getLong(), buf.getLong());
        return uuid;
    }
}
