package service.AOC2015;

import service.AOCService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AOC2015Challenge04Service implements AOCService {

    private final String inputString;

    public AOC2015Challenge04Service(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String solvePartOne() {
        int secretKeySuffixCounter = 1;
        String hashString;
        do {
            String secretKey = inputString + secretKeySuffixCounter;
            BigInteger md5Hash;
            try {
                md5Hash = getMD5Hash(secretKey);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            hashString = String.format("%032X", md5Hash);
            secretKeySuffixCounter++;
        } while (!hashString.startsWith("00000"));
        return String.valueOf(secretKeySuffixCounter - 1);
    }

    @Override
    public String solvePartTwo() {
        int secretKeySuffixCounter = 1;
        String hashString;
        do {
            String secretKey = inputString + secretKeySuffixCounter;
            BigInteger md5Hash;
            try {
                md5Hash = getMD5Hash(secretKey);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            hashString = String.format("%032X", md5Hash);
            secretKeySuffixCounter++;
        } while (!hashString.startsWith("000000"));
        return String.valueOf(secretKeySuffixCounter - 1);
    }

    public static BigInteger getMD5Hash(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return new BigInteger(1, md.digest(string.getBytes()));
    }

}
