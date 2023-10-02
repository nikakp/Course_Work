//This program counts the days between the day it is run and the due date given,
//if the due date has passed the program indicates so
//Name: Nika Prairie
//Student Number: 260843183

// do NOT touch these import statements 
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class CountdownDays {
  // the method returns a String representing the current date in the following format: dd/mm/yyyy
  // you can use it, but do NOT modify it!
  public static String getCurrentDate() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
    LocalDateTime now = LocalDateTime.now();  
    return dtf.format(now);
  }
  
  public static void main(String[] args) {
    // Set to true to run my tests for my assignment
    // Otherwise display the countdown for the due
    // date passed as an argument
    boolean test = false; 
    if (test) {
      testAssignment3();
    } else {
      // do what should normally be done in main here
      String dueDate = (args[0]);
      displayCountdown(dueDate);
    }
  }
  
  public static String getSubstring (String s, int i, int j) {
    
    String subString = "";
    
    //if i is bigger than j the while loop wont execute
    while(i <= j) {
      
      subString += s.charAt(i);
      i++;
      
    }
    return subString;
  }
  
  //gets the day from the string and returns and integer
  public static int getDay (String a) {
    
    return Integer.parseInt(getSubstring(a, 0, 1));
    
  }
  //gets the month from the string and returns and integer
  public static int getMonth (String b) {
    
    return Integer.parseInt(getSubstring(b, 3, 4));
    
  }
  
  //gets the year from the string and returns and integer
  public static int getYear (String c) {
    
    return Integer.parseInt(getSubstring(c, 6, 9));
    
  }
  
  //checks if the year in question is a leap year and returns true or false
  public static boolean isLeapYear (int year) {
    
    boolean isLeapYear = false;
    
    if(year%100 == 0) {
      
      if( year%400 == 0) {
        
        isLeapYear = true;
      }
    }
    else if(year%4 == 0) {
      
      isLeapYear = true;
      
    }
    return isLeapYear;
  }
  
  //gets the specific amount of days in a month including february which 
  //can either have 28 or 29 depending if its a leap year or not
  // default is 31 because the most months of the year have 31 days
  public static int getDaysInAMonth (int month, int year) {
    
    int numDaysInAMonth = 31;
    
    switch(month) {
      
      case 2: 
        if(isLeapYear(year)) {
        
        numDaysInAMonth = 29;
        
      } else {
        
        numDaysInAMonth = 28;
        
      }
      break;
      
      case 4: 
      case 6:
      case 9:
      case 11:
        numDaysInAMonth = 30;
        break;
      default:
        numDaysInAMonth = 31;
    }
    return numDaysInAMonth;
  }
  
  //checks whether the due date has passed in relation to the current date
  public static boolean dueDateHasPassed (String currentDate, String dueDate) {
    
    if(currentDate.equals(dueDate)) {
      return true;
    }
    // ex: 2002 > 1998
    if (getYear(currentDate) > getYear(dueDate)) {
      return true;
    }
    // ex: 1998 < 2002
    if (getYear(currentDate) < getYear(dueDate)) {
      return false;
    }
    // ex: 1998 == 1998
    // ex: 4 > 1
    if (getMonth(currentDate) > getMonth(dueDate)){
      return true;
    }
    // ex: 1 < 4
    if (getMonth(currentDate) < getMonth(dueDate)) {
      return false;
    }
    // ex: 20 > 10
    if (getDay(currentDate) > getDay(dueDate)) {
      return true;
    }
    // ex: 10 < 20
    if (getDay(currentDate) < getDay(dueDate)) {
      return false;
    }
    
    // ex: day 20 == 20
    return true;
  }
  
  //counts the specific amount of days between the start of a year and the end of a year taking
  //into consideration if the year in question is a leap year and retruns the total amount of days
  public static int countDaysBetweenYears(int startYear, int endYear) {
    int days = 0;
    for(int i = startYear; i <= endYear;i++) {
      if (isLeapYear(i)) {
        days += 366;
      } else {
        days += 365;
      }
    }
    return days;
  }
  
  //counts the amount od days between the current date and the due date
  public static int countDaysLeft(String currentDate, String dueDate) {
    int daysLeft = 0;
    int currentDay, dueDay;
    int currentMonth, dueMonth;
    int currentYear, dueYear;
    
    // if the due date has passed, return 0
    if (dueDateHasPassed(currentDate, dueDate)) {
      return 0;
    }
    
    currentDay = getDay(currentDate);
    currentMonth = getMonth(currentDate);
    currentYear = getYear(currentDate);
    
    dueDay = getDay(dueDate);
    dueMonth = getMonth(dueDate);
    dueYear = getYear(dueDate);
    
    // Let's count the number of days in the years immediately
    // after the current year and before due year
    // in other words, there is a full year between
    // them that we need to count
    // ex: 1998 to 2003, we need to count the years
    // 1999, 2000, 2001, 2002
    
    if (dueYear - currentYear > 1) {
      // this would be 1998 and 2002 if I use the example from above
      daysLeft = countDaysBetweenYears(currentYear+1, dueYear-1);
      
      if (currentMonth != 12) {
        // Start from the next month until December and
        // add up the days
        for (int i = currentMonth + 1; i <= 12; i++) {
          daysLeft += getDaysInAMonth(i, currentYear);
        }
      }
      //counts the days left in a month
      daysLeft += getDaysInAMonth(currentMonth,currentYear) - currentDay;
      
      if (currentMonth != 1) {
        for(int i = 1; i < dueMonth; i++) {
          daysLeft += getDaysInAMonth(i, dueYear);
        }
      }
      
      daysLeft += dueDay;
      
    }
    
    else if(dueYear - currentYear == 1) {
      if (currentMonth != 12) {
        // Start from the next month until December and
        // add up the days
        for (int i = currentMonth + 1; i <= 12; i++) {
          daysLeft += getDaysInAMonth(i, currentYear);
        }
      }
      // Add the number of days remaining in the current month
      daysLeft += getDaysInAMonth(currentMonth,currentYear) - currentDay;
      if (currentMonth != 1) {
        for(int i = 1; i < dueMonth; i++) {
          daysLeft += getDaysInAMonth(i, dueYear);
        }
      }
      // add the number of days remaining in the due month
      daysLeft += dueDay;
    }
    
    //if the years are the same
    else if(dueYear - currentYear == 0) {
      
      //if the months are the same just subtract due day from current day
      if(dueMonth == currentMonth){
        daysLeft += dueDay - currentDay;
      }
      else {
        int i = currentMonth+1;
        while (i < dueMonth) {
          
          //if the year is the same but the months arent the same
          daysLeft += getDaysInAMonth(i, currentYear);
          i++;
        }
        // Add the number of days remaining in the current month
        daysLeft += getDaysInAMonth(currentMonth,currentYear) - currentDay;
        // add the number of days remaining in the due month
        daysLeft += dueDay;
      }
    }
    
    return daysLeft;
  }
  
  //displays message to the screen indicating whether the due date has passed or not and how
  // many days are remaining untill the due date
  public static void displayCountdown(String dueDate) {
    if(!dueDateHasPassed(getCurrentDate(), dueDate)){
      System.out.println("Today is: " + getCurrentDate());
      System.out.println("Due date: " + dueDate);
      System.out.println("You have " + countDaysLeft(getCurrentDate(), dueDate) + " days left. You can do it!");;
    }
    else{
      System.out.println("Today is: " + getCurrentDate());
      System.out.println("Due date: " + dueDate);
      System.out.println("The due date has passed! :( Better luck next time!");
    }
  }
  
  // I implemented this function to help me test my code
  public static void testAssignment3() {
    int i;
    // Test getSubstring
    System.out.println(getSubstring("strawberry",0,4));
    System.out.println(getSubstring("cats and dogs",3,6));
    System.out.println(getSubstring("monday",2,2));
    System.out.println(getSubstring("monday",0,0));
    System.out.println(getSubstring("monday",5,5));
    // Both of these cause exceptions as they should
    //System.out.println(getSubstring("another string",6,3));
    //System.out.println(getSubstring("more cats", -4, 3));
    
    // Test getDay, getMonth & getYear
    System.out.println(getDay("01/02/3456"));
    System.out.println(getMonth("01/02/3456"));
    System.out.println(getYear("01/02/3456"));
    
    // Test code for getDaysInAMonth, which in turn will
    // test isLeapYear
    for(i = 1; i<=12; i++) {
      System.out.println("Year: 1997 " + "Month: "+i+" days: " + getDaysInAMonth(i, 1997));
    }
    for(i = 1; i<=12; i++) {
      System.out.println("Year: 2000 " + "Month: "+i+" days: " + getDaysInAMonth(i, 2000));
    }
    for(i = 1; i<=12; i++) {
      System.out.println("Year: 2100 " + "Month: "+i+" days: " + getDaysInAMonth(i, 2100));
    }
    for(i = 1; i<=12; i++) {
      System.out.println("Year: 1988 " + "Month: "+i+" days: " + getDaysInAMonth(i, 1988));
    }
    
    // Test dueDateHasPassed
    System.out.println(dueDateHasPassed("12/04/1998", "23/01/2002"));
    System.out.println(dueDateHasPassed("23/01/2002","12/04/1998"));
    System.out.println(dueDateHasPassed("23/01/2002","23/01/2002"));
    System.out.println(dueDateHasPassed("23/01/2003","23/01/2002"));
    System.out.println(dueDateHasPassed("23/01/2002","23/01/2003"));
    System.out.println(dueDateHasPassed("23/02/2002","23/01/2002"));
    System.out.println(dueDateHasPassed("23/01/2002","23/02/2002"));
    System.out.println(dueDateHasPassed("20/01/2002","23/01/2002"));
    System.out.println(dueDateHasPassed("23/01/2002","20/01/2002"));
    System.out.println(dueDateHasPassed("23/01/2002","23/01/2002"));
    System.out.println("------------------");
    System.out.println("Testing days between current date and due date");
    System.out.println(""+countDaysLeft("13/10/2018","23/10/2018"));
    System.out.println(""+countDaysLeft("13/10/2018", "08/05/2024"));
    System.out.println(""+countDaysLeft("13/10/2018", "15/09/2018"));
    System.out.println(""+countDaysLeft("13/10/2018", "18/09/2019"));
    System.out.println(""+countDaysLeft("13/10/2018", "26/02/2019"));
    System.out.println(""+countDaysLeft("13/10/2018","23/10/2018"));
    System.out.println(""+countDaysLeft("13/10/2018", "08/05/2024"));
    System.out.println(""+countDaysLeft("13/10/2018", "15/09/2018"));
    System.out.println(""+countDaysLeft("13/10/2018", "18/09/2019"));
    System.out.println(""+countDaysLeft("13/10/2018", "26/02/2019"));
    System.out.println(""+countDaysLeft("13/10/2018", "13/10/2019"));
    System.out.println(""+countDaysLeft("13/10/2018", "14/10/2018"));
    System.out.println(""+countDaysLeft("13/10/2018", "14/10/2019"));
    System.out.println(""+countDaysLeft("13/10/2018", "14/10/2020"));
    
    // Test displayCountdown
    displayCountdown("30/10/2018");
    displayCountdown("31/10/2018");
    displayCountdown("30/09/2018");
    displayCountdown("29/10/2018");
    displayCountdown("30/10/2019");
    displayCountdown("30/10/2020");
    displayCountdown("01/01/2018");
  }
}
