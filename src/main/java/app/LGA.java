package app;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2016
 *
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 */
public class LGA {
   // LGA 2016 Code
   private int code2;

   // LGA 2016 Name
   private String name16;
   private String name10;
   private String name2;
   private String pop;
   private String age;
   private String income1;
   private String income2;
   private String income3;
   private String income4;

   /**
    * Create an LGA and set the fields
    */
   public LGA(int code2, String name16, String name10, String name2, String pop2, String age, String income1, String income2, String income3, String income4) {
      this.name16 = name16;
      this.name10 = name10;
      this.name2 = name2;
      this.code2 = code2;
      this.pop = pop2;
      this.age = age;
      this.income1 = income1;
      this.income2 = income2;
      this.income3 = income3;
      this.income4 = income4;
   }

   //public LGA(String name, String status, String sex, int count) {
//}

public String getName16() {
      return name16;
   }
   public String getName10() {
      return name10;
   }
   public String getName2() {
      return name2;
   }
   public int getCode2() {
      return code2;
   }
   public String getPop() {
      return pop;
   }
   public String getAge() {
      return age;
   }
   public String getIncome1() {
      return income1;
   }
   public String getIncome2() {
      return income2;
   }
   public String getIncome3() {
      return income3;
   }
   public String getIncome4() {
      return income4;
   }
}
