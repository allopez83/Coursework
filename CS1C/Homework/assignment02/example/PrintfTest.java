package assignment02.example;

public class PrintfTest
{
   public static void main(String[] args)
   {
      String item1 = "Carrot";
      String item2 = "King Salmon";
      String item3 = "Cabernet Saugvignon (case)";

      double price1 = 0.23;
      double price2 = 10.45;
      double price3 = 1257.68;

      System.out.printf("%-26s: %7.2f\n", item1, price1);
      System.out.printf("%-26s: %7.2f\n", item2, price2);
      System.out.printf("%-26s: %7.2f\n", item3, price3);
   }
}