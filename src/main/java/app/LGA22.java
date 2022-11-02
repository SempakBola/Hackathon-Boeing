package app;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2016
 *
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 */
public class LGA22 {
   // LGA 2016 Code

   // LGA 2016 Name
   private String name;


   private int code, count, population16, population18, year, atRiskChange, homelessChange, popChange, atRiskOG, homelessOG, popOG;
   private String status, sex, ageGroup, type;
   private Double areaSqkm, latitude, longitude;


   /**
    * Create an LGA and set the fields
    */
   public LGA22( String name,
    int code, String status, String sex,String ageGroup,int count,
    String type,Double areaSqkm,Double latitude,Double longitude, int population16 , int population18, int year) {

      this.name = name;

      this.code = code;
      this.status = status; 
      this.sex = sex;
      this.ageGroup=ageGroup;
      this.count=count;

      this.type=type;
      this.areaSqkm=areaSqkm;
      this.latitude=latitude;
      this.longitude= longitude; 
      this.population16 = population16;
      

      this.population18 = population18;

      this.year = year;



   }

   public LGA22(String name2, String status2, String sex2, int count2) {
   }

   public LGA22(String name, int code, int atRiskChange, int homelessChange, int popChange, 
   int atRiskOG, int homelessOG, int popOG) { // 3.2
      this.name = name;
      this.code = code;
      this.atRiskChange = atRiskChange; 
      this.homelessChange = homelessChange;
      this.popChange=popChange;

      this.atRiskOG = atRiskOG; 
      this.homelessOG = homelessOG;
      this.popOG=popOG;

   }






   public String getName() {
      return name;
   }
   public int getCode() {
      return code;
   }
   public String getStatus() {
      return status;
   }
   public String getSex() {
      return sex;
   }
   public String getAgeGroup() {
      return ageGroup;
   }
   public int getCount() {
      return count;
   }
   public String getType() {
      return type;
   }
   public Double getAreaSqkm() {
      return areaSqkm;
   }
   public Double getLatitude() {
      return latitude;
   }
   public Double getLongitude() {
      return longitude;
   }
   public int getPopulation16() {
      return population16;
   }
   public int getPopulation18() {
      return population18;
   }
   public int getYear() {
      return year;
   }



   public int getAtRiskChange() {
      return atRiskChange;
   }
   public int getHomelessChange() {
      return homelessChange;
   }
   public int getPopChange() {
      return popChange;
   }



   public int getAtRiskOG() {
      return atRiskOG;
   }
   public int getHomelessOG() {
      return homelessOG;
   }
   public int getPopOG() {
      return popOG;
   }




}
