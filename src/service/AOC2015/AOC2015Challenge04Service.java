package service.AOC2015;

import service.AOCService;

import java.math.BigInteger;
import java.security.MessageDigest;

public class AOC2015Challenge04Service implements AOCService {

    private final String inputString;

    public AOC2015Challenge04Service(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public String solvePartOne() {
        int i = 1;
        String hashText;
        do {
            String plainText = inputString + i;
            hashText = this.calculateHash(plainText);
            i++;
        } while (!hashText.startsWith("00000"));
        return String.valueOf(i-1);
    }

    @Override
    public String solvePartTwo() {
        int i = 1;
        String hashText;
        do {
            String plainText = inputString + i;
            hashText = this.calculateHash(plainText);
            i++;
        } while (!hashText.startsWith("000000"));
        return String.valueOf(i-1);
    }

    private String calculateHash(String plainText) {
        StringBuilder hashtext = new StringBuilder();
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plainText.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            hashtext = new StringBuilder(bigInt.toString(16));
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
        } catch (Exception e) {
            return "ERROR IN CALCULATING HASH.";
        }
        return hashtext.toString();
    }
}
