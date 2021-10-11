package billCreators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import counters.PriceCounter;
import models.Item;
import scanners.ConsoleHandler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class BillCreator {
    private static long id = 0;
    private static Scanner sc = new Scanner(System.in);

    public static void create() {


        Document document = new Document();

        float price = 0;

        byte ch;

        try {
            PdfWriter.getInstance(document, new FileOutputStream("bill.pdf"));

            document.open();

            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);

            Chunk chunk = new Chunk("", font);
//            String name;
//            int amount;
//            float itemPrice;

            System.out.println("To add item print '1', to get bill print '0'");

            ch = sc.nextByte();

            while (ch != 0) {


                if (ch == 1) {
                    String name = ConsoleHandler.readName();
                    Float itemPrice = ConsoleHandler.readPrice();
                    int amount = ConsoleHandler.readAmount();


                    Item item = new Item(name, itemPrice);

                    price += PriceCounter.count(item, amount);


                    document.add(new Paragraph(billText(item, price, amount)));

                } else if (ch == 0) {
                    System.out.println("Your bill");
                } else {
                    System.out.println("Please enter valid value");
                }

                System.out.println("To add item print '1', to get bill print '0'");

                ch = sc.nextByte();

            }

            document.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private static String billText(Item i, Float price, int amount) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("|  ").append("name");


        stringBuffer.append("| name  |---------| price for 1 |----------| amount |--------| final price  |\n");
        stringBuffer.append("|  ").append(i.getName()).append("  |").append(i.getPrice()).append("  |").append(amount).append("  |").append(price).append("  |\n");

        return stringBuffer.toString();
    }


}
