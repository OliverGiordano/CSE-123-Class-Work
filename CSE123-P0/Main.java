import java.util.*;

class Main{
    public static void main(String[] args){
        //Substitution tmpSub = new Substitution("KBRgYCmhEbHpaedjMPzNVilGctFqZAvrIOTXfnLsyuUQxowDSJkW");
        /*String txt = "Gaviniscool";
        System.out.println(txt);
        //txt = tmpSub.encrypt(txt);
        System.out.println(txt);
        //txt = tmpSub.decrypt(txt);
        System.out.println(txt);

        System.out.println("\n\n\n\n");

        System.out.println(txt);
        CaesarShift sub3 = new CaesarShift(12);
        CaesarShift sub1 = new CaesarShift(4);
        txt = sub1.encrypt(txt);
        System.out.println(txt);
        txt = sub1.decrypt(txt);
        System.out.println(txt);

        System.out.println("\n\n\n\n");
        CaeserKey sub2 = new CaeserKey("123");
        CaeserKey sub4 = new CaeserKey("lemon");
        txt = sub2.encrypt(txt);
        System.out.println(txt);
        txt = sub2.decrypt(txt);
        System.out.println(txt);

        System.out.println("\n\n\n\n");
        ArrayList<Cipher> cal = new ArrayList<Cipher>();
        cal.add(sub1);
        cal.add(sub2);
        cal.add(sub3);
        cal.add(sub4);
        MultiCipher sub5 = new MultiCipher(cal);
        String decryptText = "Yysu(zer(vyly xylw(\"m(!xy (q ywl}ul!)(Oyt(&e\"({le$($xq!(!xy (}u qwu($q (ruvenu(tusn&m!ylwJ(E1";
        txt = sub5.encrypt(txt);
        System.out.println(txt);
        txt = sub5.decrypt(txt);
        System.out.println(txt);
        System.out.println(sub5.decrypt(decryptText));*/

        String txt = "HEllO";

        //CaesarKey sub1 = new CaesarKey("tiN");
        CaesarShift sub1 = new CaesarShift(6);

        txt = sub1.encrypt(txt);

        System.out.println(txt);
    }
}
