package com.easydynamics.calculator;

import java.util.Arrays;

public class CalculatorService {
    public static String add(String a, String b) {
        if (a.length() > b.length()) {
            String tmp = a;
            a = b;
            b = tmp;
        }

        String result = "";
        int n1 = a.length(), n2 = b.length();
        int diff = n2 - n1;
        int carry = 0;

        for (int i = n1-1; i >= 0; i--) {
            int sum = ((a.charAt(i) - '0') + (b.charAt(i+diff) - '0') + carry);
            result = Integer.toString(sum % 10) + result;
            carry = sum/10;
        }

        for (int i = n1; i < n2; i++) {
            int sum = ((b.charAt(i)-'0') + carry);
            result = Integer.toString(sum % 10) + result;
            carry = sum/10;
        }

        if (carry != 0) {
            result = Integer.toString(carry) + result;
        }
        
        return result;
    }

    public static String subtract(String a, String b) {
        if (a.equals(b)) return "0";

        String result = "";
        int n1 = a.length(), n2 = b.length();
        int diff = n1 - n2;
        int carry = 0;

        for (int i = n2-1; i >= 0; i--) {
            int sub = ((a.charAt(i+diff) - '0') - (b.charAt(i) - '0') - carry);
            if (sub < 0) {
                sub = sub + 10;
                carry = 1;
            } else {
                carry = 0;
            }
            result = Integer.toString(sub) + result;
        }

        for (int i = n1-n2-1; i >= 0; i--) {
            if (a.charAt(i) == '0' && carry > 0) {
                result = "9" + result;
                continue;
            }
            int sub = ((a.charAt(i) - '0') - carry);
            if (i > 0 || sub > 0) {
                result = Integer.toString(sub) + result;
            }
            carry = 0;
        }

        return result.replaceFirst("^0*", "");
    }

    public static String multiply(String a, String b) {
        if (a.length() == 0 || b.length() == 0) return "";
        if (a.equals("0") || b.equals("0")) return "0";

        String result = "";
        int n1 = a.length(), n2 = b.length();
        int[] c = new int[n1 + n2 + 1];
        Arrays.fill(c, 0);
        int i_n1 = 0, i_n2 = 0;

        for (int i = n2 - 1; i >= 0; i--) {
            int dig2 = b.charAt(i) - '0';
            int carry = 0;
            i_n1 = 0;
            for (int j = n1 - 1; j >= 0; j--) {
                int dig1 = a.charAt(j) - '0';
                int sum = dig1 * dig2 + c[i_n1 + i_n2] + carry;
                carry = sum / 10;
                c[i_n1 + i_n2] = sum % 10;
                i_n1++;
            }
            if (carry > 0) {
                c[i_n1 + i_n2] += carry;
            }

            i_n2++;
        }
        int i = n1 + n2;
        while (i >= 0 && c[i] == 0) {
            i--;
        }
        while (i >= 0) {
            result += c[i--];
        }
        return result;
    }

    public static String divide(String a, String b) {
        String n3;
        long quotient = 0;
        String[] results;
        while (true) {
            n3 = subtract(a, b);
            if (n3.equals("-1")) {
                results = new String[]{"" + quotient, a};
                break;
            }
            quotient++;
            if (n3.equals("0")) {
                results = new String[]{"" + quotient, "0"};
                break;
            }
            a = n3;
        }
        return results[0];
    }

}
