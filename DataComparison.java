import java.io.*;
import java.util.*;

public class DataComparison
{
   public static void main(String[] args) throws FileNotFoundException
   {
      String courseID1; //course id for group 1
      String courseID2; //course id for group 2
      
      int numberOfCourses;
      
      double avg1;      //average for a course in group1
      double avg2;      //average for a course in group2
      double avgGroup1; //average group1
      double avgGroup2; //average group2
      
   //step 2 open the input and output files
      
      Scanner group1 = new Scanner(new FileReader("group1.txt"));
      Scanner group2 = new Scanner(new FileReader("group2.txt"));
      
      PrintWriter outfile = new PrintWriter("student.out");
      
      avgGroup1 = 0.0;  //step 3
      avgGroup2 = 0.0;  //step 4
      
      numberOfCourses = 0; //step 5
   //print heading step 6
   
      outfile.println("Course ID       Group No.      Course Average");
      
      while (group1.hasNext() && group2.hasNext()) //step 7
      {
         courseID1 = group1.next();
         courseID2 = group2.next();
         
         if (!courseID1.equals(courseID2))
         {
            System.out.println("Data error: Course IDs do not match.");
            System.out.println("Program Terminates.");
            outfile.println("Data error: Course IDs do not match.");
            outfile.println("Program Terminates.");
            outfile.close();
            return;
         }
         
         else
         {
            avg1 = calculateAverage(group1);
            avg2 = calculateAverage(group2);
            printResult(outfile, courseID1, 1, avg1);
            printResult(outfile, courseID2, 2, avg2);
            
            avgGroup1 = avgGroup1 + avg1;
            avgGroup2 = avgGroup2 + avg2;
            
            outfile.println();
            numberOfCourses++;
         }
      }//end while
      
      if (group1.hasNext() && !group2.hasNext())
         System.out.println("Ran out of data for group 2 before group 1.");
      else if (!group1.hasNext() && group2.hasNext())
         System.out.println("Ran out of data for group 1 before group 2.");
      else
      {
         outfile.printf("Avg for group 1: %.2f %n", avgGroup1 / numberOfCourses);
         outfile.printf("Avg for group 2: %.2f %n", avgGroup2 / numberOfCourses);
      }
      
      group1.close();
      group2.close();
      outfile.close();
      
   }//end main
   
   public static double calculateAverage(Scanner inp)
   {
      double totalScore = 0.0;
      int numberOfStudents = 0;
      int score = 0;
      double courseAvg;
      
      score = inp.nextInt();
      
      while (score != -999)
      { 
         totalScore = totalScore + score;
         numberOfStudents++;
         score = inp.nextInt();
      }//end while
      
      courseAvg = totalScore / numberOfStudents;
      
      return courseAvg;
   }//end calculateAverage
   
   public static void printResult(PrintWriter outp, String courseID, int groupNo, double avg)
   {
      if (groupNo == 1)
         outp.print("   " + courseID + "  ");
      else
         outp.print("        ");
      
      outp.printf("%12d %20.2f%n", groupNo, avg);
   }//end printResult
}