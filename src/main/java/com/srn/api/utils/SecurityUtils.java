package com.srn.api.utils;

import com.srn.api.model.entity.SrnDevice;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;

public class SecurityUtils {

    private static final String algorithm = "AES";
    private static final String SESSION_KEY = "!S4r1r@sA";
    private static final String DATA_KEY = "@@S4r!1R4s@";
    private final AESEncryption aes = new AESEncryption();
    private static SecurityUtils securityUtils;
    private Method securityMethod;
    private String value;
    private Object model;
    private Class classModel;


    public enum Method {
        UNKNOWN,
        SESSION_ENCRYPT, SESSION_DECRYPT,
        DATA_ENCRYPT, DATA_DECRYPT
    }

    public static SecurityUtils getInstance() {
        if(securityUtils == null) {
            securityUtils = new SecurityUtils();
        }
        return securityUtils;
    }

    public SecurityUtils setValue(String value) {
        securityUtils.value = value;
        return securityUtils;
    }

    public SecurityUtils setData(Object data) {
        securityUtils.model = data;
        return securityUtils;
    }

    public SecurityUtils setData(Class modelClass) {
        securityUtils.classModel = modelClass;
        return securityUtils;
    }

    public SecurityUtils setMethod(Method method) {
        securityUtils.securityMethod = method;
        return securityUtils;
    }

    public String build() {
        String result = null;
        try {
            if (model != null) {
                switch (securityUtils.securityMethod) {
                    case SESSION_ENCRYPT:
                        result = sessionHandler(true);
                        break;
                    case SESSION_DECRYPT:
                        result = sessionHandler(false);
                        break;
                    case DATA_ENCRYPT:
                        result = dataHandler(true);
                        break;
                    case DATA_DECRYPT:
                        result = dataHandler(false);
                        break;
                }
            }
            reset();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return result;
    }

    public boolean isSessionValid(String sessionId) {
        boolean isvalid = setMethod(Method.SESSION_DECRYPT)
                .setData(sessionId)
                .build() != null;
        reset();
        return isvalid;
    }

    private String sessionHandler(boolean encrypt) {
        String result = null;
        try {
            if (encrypt && model instanceof SrnDevice) {
                result = encryptSession((SrnDevice) model);
            } else if (!encrypt && model instanceof String) {
                result = decryptSession((String) model);
            } else {
                throw new IllegalStateException("Invalid model input");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private String dataHandler(boolean encrypt) {
        String result = "";
        try {
            if (encrypt) {
                result = aes.encrypt(model.toString(), DATA_KEY);
            } else {
                result = aes.decrypt(model.toString(), DATA_KEY);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private void reset() {
        securityUtils.value = null;
        securityUtils.securityMethod = Method.UNKNOWN;
        model = null;
    }

    private String encryptSession(SrnDevice device) throws Exception {
        return aes.encrypt(device.toString(), SESSION_KEY);
    }

    private String decryptSession(String cipher) throws Exception {
        return aes.decrypt(cipher, SESSION_KEY);
    }

    private class AESEncryption {

        public String encrypt(String valueToEnc, String password) throws Exception {
            Key key = generateKey(password);
            Cipher c = Cipher.getInstance(algorithm);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encValue = c.doFinal(valueToEnc.getBytes());
            String encryptedValue = new Base64().encodeAsString(encValue);
            return encryptedValue;
        }

        public String decrypt(String encryptedValue, String password) throws Exception {
            Key key = generateKey(password);
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = new Base64().decode(encryptedValue);
            byte[] decValue = c.doFinal(decordedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
        }

        private Key generateKey(String passwd) throws Exception {
            byte[] sKey = passwd.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            sKey = sha.digest(sKey);
            sKey = Arrays.copyOf(sKey, 16); //use only first 128 bit
            Key key = new SecretKeySpec(sKey, algorithm);
            return key;
        }
    }
}