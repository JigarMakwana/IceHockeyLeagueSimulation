package group11.Hockey;

import java.util.Arrays;
import java.util.Scanner;

public class MainTesting {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Integer[] array = new Integer[5];
		System.out.println("Enter values");
		int start = 0;
		while(start < array.length) {
			Integer userInput =  scanner.nextInt();
			if(! (userInput >=2 && userInput <=12)) {
				System.out.println("Enter valid value between 2 and 12");
				continue;
			}
			array[start] = userInput;
			start++;
		}
		
		String reverse = "";
		System.out.println(Arrays.toString(array));
		 for (int i = array.length-1; i >= 0; i--) {   
			 reverse = reverse + array[i].toString() + ", ";
	        }  
		 System.out.println(reverse);
		 String evenValues = "";
		 for(Integer val: array) {
			 if(val %2 == 0) {
				 evenValues = evenValues + val.toString() + ", ";
			 }
		 }
		 System.out.println("Even number values in array: "+ evenValues);
		 
		 String evenNumberIndexes = "";
		 for( int i = 0; i < array.length; i += 2) {
			 evenNumberIndexes = evenNumberIndexes + array[i] + ", ";
			}
		 
		 
		 // Max value:
		 int highestVal = array[0];
		    for (int i = 1; i < array.length; i++) {
		        if (array[i] > highestVal) {
		        	highestVal = array[i];
		        }
		    }
		   System.out.println("Max value: "+ highestVal); 
		   
		   // Min value
		   
		   int min = array[0];
		   for (int i = 1; i < array.length; i++) {
		        if (array[i] < min) {
		        	min = array[i];
		        }
		    }
		   System.out.println("Min value: "+ min);
		   
		   // Sum
		   int sum  = 0;
		   for (int i : array) {
			    sum += i;
			}
		   System.out.println("Sum : " + sum);
		   //averate
		   double avg ;
		   avg = sum/ array.length;
		   System.out.println("Average: "+ avg);
		   // Count of occurrences
		   System.out.println("Enter the number to count the number of occurrences");
		   Integer userCount =  scanner.nextInt();
		   int count = 0;
		   for (int i : array) {
			   if(i == userCount) {
				   count++;
			   }
		   }
		   System.out.println("Count of number "+ userCount.toString()+ "is:"+ count);
		   
		   
	}

}
