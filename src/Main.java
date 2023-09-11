import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static String name;
    public static double price;
    public static int quantity;

    public static void main(String[] args) {
        File phone = new File("data.txt");

        int index = 0;
        ArrayList<PhoneData> phoneData = new ArrayList<>();
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<String> iPhone14Pro = new ArrayList<>();
        ArrayList<String> SamsungGalaxyS21 = new ArrayList<>();

        if(phone.exists()){
            System.out.println("File exists");

            if (phone.canRead()){
                System.out.println("File is readable");
            }else {
                System.out.println("File is unreadable");
            }

            try(Scanner reader = new Scanner(phone)) {
                while (reader.hasNext()){
                    String line = reader.nextLine();

                    if (index > 0) {
                        String[] items = line.split(",");



                        for (int i = 0 ; i < items.length; i++){
                            if(i == 0){
                                name = items[i];
                            }
                            if(i == 1){
                                price = Double.parseDouble(items[i]);
                            }

                            if(i == 2){
                                quantity = Integer.parseInt(items[i]);
                            }

                        }

                        PhoneData data = new PhoneData(name, price, quantity);

                        phoneData.add(data);

                        System.out.print(index + ". ");
                        System.out.println(data);

                    }
                    index++;
                }

                System.out.println();

                for (PhoneData row: phoneData){
                    if(!(categories.contains(row.getName()))){
                        categories.add(row.getName());
                    }
                }

                for (String category: categories){
                    try(PrintWriter writer = new PrintWriter(new File(category+".txt"))){
                        for (PhoneData row: phoneData){
                            if (category.equals(row.getName())){
                                writer.println(row);
                            }
                        }
                    }catch (FileNotFoundException exception){
                        System.out.println(exception.getMessage());
                    }
                }

            }catch (FileNotFoundException exception){
                System.out.println(exception.getMessage());
            }
        }else {
            System.out.println("File does not exists");
        }

    }
}