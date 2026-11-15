package principal;

import util.PasswordUtil;

public class PruebaHash {

    public static void main(String[] args) {

        String hash = PasswordUtil.generarHash("admin123");
        System.out.println(hash);
    }
}