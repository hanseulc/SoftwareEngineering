package Project;

import java.io.*;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;

public class User {

    protected static ArrayList<Membership> membershipList;
    protected static ArrayList<LoyaltyScheme> loyaltyList;
    protected static ArrayList<Membership> membershipAll;
    protected static ArrayList<LoyaltyScheme> loyaltyAll;

    public User(){
        membershipList = new ArrayList<>();
        loyaltyList = new ArrayList<>();
        membershipAll = new ArrayList<>();
        loyaltyAll = new ArrayList<>();
    }

    public static void saveData() throws IOException, URISyntaxException {
        String path = String.valueOf((new File(AddCustomMembershipController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())));
        String[] words = path.split("\\\\");
        String str = "";
        int diff;
        if (path.contains(".jar")) diff = 1;
        else diff = 0;
        for (int i = 0; i < words.length-diff; i++) {
            str += words[i]+"\\";
        }
        PrintWriter pwriter = new PrintWriter(new FileOutputStream(str+"savedMemberships.txt", false));
        PrintWriter pwriter2 = new PrintWriter(new FileOutputStream(str+"savedLoyaltySchemes.txt", false));

        for (Membership m: membershipAll) {
            String extra = "";
            if (m instanceof Fixed)
                extra = " " + m.getPaymentOrEndDate().toString();
            pwriter.println(m.getName() + " " + m.getPrice() + " " + m.getType().replace(" ", "_") + " " + m.getStartDate() + " " + m.getStatus() + extra);
        }

        pwriter.close();

        for (LoyaltyScheme ls: loyaltyAll){
            pwriter2.println(ls.getName() + " " + ls.getPoints() + " " + ls.getStatus());
        }

        pwriter2.close();
    }

    public static void readData() throws IOException, URISyntaxException {
        String path = String.valueOf((new File(CatalogueController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())));
        String[] words = path.split("\\\\");
        String str = "";
        int diff;
        if (path.contains(".jar")) diff = 1;
        else diff = 0;
        for (int i = 0; i < words.length-diff; i++) {
            str += words[i]+"\\";
        }
        BufferedReader breader = new BufferedReader(new FileReader(str+"savedMemberships.txt"));
        BufferedReader breader2 = new BufferedReader(new FileReader(str+"savedLoyaltySchemes.txt"));

        String line;
        while ((line = breader.readLine()) != null){
            String[] parts = line.split(" ");
            if (parts[2].contains("Fixed")){
                Membership m = new Fixed(parts[0], LocalDate.parse(parts[3]), Double.parseDouble(parts[1]), LocalDate.parse(parts[5]));
                membershipAll.add(m);
                if (findStatus(parts[4]))
                    membershipList.add(m);
                else
                    m.setExpired();
            }
            else{
                System.out.println("test2");
                if (parts[2].contains("Annual")){
                    System.out.println("test");
                    Membership m = new Annual(parts[0], LocalDate.parse(parts[3]), Double.parseDouble(parts[1]));
                    membershipAll.add(m);
                    if (findStatus(parts[4]))
                        membershipList.add(m);
                    else
                        m.setExpired();
                }
                else if (parts[2].contains("Monthly")){
                    Membership m = new Monthly(parts[0], LocalDate.parse(parts[3]), Double.parseDouble(parts[1]));
                    membershipAll.add(m);
                    if (findStatus(parts[4]))
                        membershipList.add(m);
                    else
                        m.setExpired();
                }
                else if (parts[2].contains("Weekly")){
                    Membership m = new Weekly(parts[0], LocalDate.parse(parts[3]), Double.parseDouble(parts[1]));
                    membershipAll.add(m);
                    if (findStatus(parts[4]))
                        membershipList.add(m);
                    else
                        m.setExpired();
                }
            }
        }

        while ((line = breader2.readLine()) != null){
            String[] parts = line.split(" ");
             LoyaltyScheme ls = new LoyaltyScheme(parts[0], Integer.parseInt(parts[1]));
             loyaltyAll.add(ls);
             if (findStatus(parts[2]))
                 loyaltyList.add(ls);
             else
                 ls.setExpired();

        }

    }

    public static boolean findStatus(String str){
        return str.equals("Active") ? true : false;
    }
}
