public class MixColProd {
    public MixColProd() {
    }
 
    public static String mult(String var0) {
       String[] var2 = var0.split("\\*");
       if (var2.length != 2) {
          System.out.println("Invalid expression format");
          return "";
       } else {
          String var3 = var2[0];
          String var4 = var2[1];
          int var5 = Integer.parseInt(var3, 16);
          int var6 = Integer.parseInt(var4, 16);
          String var7 = Integer.toBinaryString(var5);
          String var8 = Integer.toBinaryString(var6);
          String var9 = convertToPolynomial(var7);
          String var10 = convertToPolynomial(var8);
          String var11 = multiplyPolynomials(var9, var10);
          String var12 = simplifyPolynomial(var11);
          String var13 = polynomialToBinary(var12);
          int var14 = Integer.parseInt(var13, 2);
          String var15 = Integer.toHexString(var14);
          return var15.toUpperCase();
       }
    }
 
    private static String convertToPolynomial(String var0) {
       StringBuilder var1 = new StringBuilder();
 
       for(int var2 = var0.length() - 1; var2 >= 0; --var2) {
          if (var0.charAt(var2) == '1') {
             if (var1.length() > 0) {
                var1.append("+");
             }
 
             var1.append("x^").append(var0.length() - 1 - var2);
          }
       }
 
       return var1.toString();
    }
 
    private static String multiplyPolynomials(String var0, String var1) {
       if (!var0.isEmpty() && !var1.isEmpty()) {
          String[] var2 = var0.split("\\+");
          String[] var3 = var1.split("\\+");
          StringBuilder var4 = new StringBuilder();
 
          for(int var5 = 0; var5 < var2.length; ++var5) {
             for(int var6 = 0; var6 < var3.length; ++var6) {
                if (!var4.toString().isEmpty()) {
                   var4.append("+");
                }
 
                String var7 = multiplyTerms(var2[var5], var3[var6]);
                if (!var7.isEmpty()) {
                   var4.append(var7);
                }
             }
          }
 
          return var4.toString();
       } else {
          return "";
       }
    }
 
    private static String multiplyTerms(String var0, String var1) {
       String[] var2 = var0.split("\\^");
       String[] var3 = var1.split("\\^");
       boolean var4 = false;
       boolean var5 = false;
 
       int var8;
       int var9;
       try {
          var8 = Integer.parseInt(var2[1].substring(0, 1));
          var9 = Integer.parseInt(var3[1].substring(0, 1));
       } catch (NumberFormatException var7) {
          return "";
       }
 
       int var6 = var8 + var9;
       return var6 == 8 ? "x^4+x^3+x^1+x^0" : "x^" + var6;
    }
 
    private static String simplifyPolynomial(String var0) {
       String[] var1 = var0.split("\\+");
       StringBuilder var2 = new StringBuilder();
       String[] var3 = var1;
       int var4 = var1.length;
 
       for(int var5 = 0; var5 < var4; ++var5) {
          String var6 = var3[var5];
          if (!var2.toString().contains(var6)) {
             if (var2.length() > 0) {
                var2.append("+");
             }
 
             var2.append(var6);
          } else if (var2.indexOf(var6) + var6.length() != var2.length()) {
             var2.delete(var2.indexOf(var6), var2.indexOf(var6) + var6.length() + 1);
          } else {
             var2.delete(var2.indexOf(var6) - 1 > -1 ? var2.indexOf(var6) - 1 : var2.indexOf(var6), var2.indexOf(var6) + var6.length());
          }
       }
 
       return var2.toString();
    }
 
    private static String polynomialToBinary(String var0) {
       String[] var1 = var0.split("\\+");
       int var2 = 0;
       String[] var3 = var1;
       int var4 = var1.length;
 
       int var7;
       for(int var5 = 0; var5 < var4; ++var5) {
          String var6 = var3[var5];
 
          try {
             var7 = Integer.parseInt(var6.substring(2));
          } catch (NumberFormatException var12) {
             continue;
          }
 
          if (var7 > var2) {
             var2 = var7;
          }
       }
 
       StringBuilder var13 = new StringBuilder();
 
       for(var4 = var2; var4 >= 0; --var4) {
          boolean var14 = false;
          String[] var15 = var1;
          var7 = var1.length;
 
          for(int var8 = 0; var8 < var7; ++var8) {
             String var9 = var15[var8];
             if (var9.startsWith("x^") && var9.length() > 2) {
                try {
                   int var10 = Integer.parseInt(var9.substring(2));
                   if (var10 == var4) {
                      var14 = true;
                      break;
                   }
                } catch (NumberFormatException var11) {
                }
             }
          }
 
          if (var14) {
             var13.append('1');
          } else {
             var13.append('0');
          }
       }
 
       return "0" + var13.toString();
    }
 }
 