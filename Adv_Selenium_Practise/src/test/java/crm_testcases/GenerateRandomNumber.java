package crm_testcases;

import java.util.Random;

public class GenerateRandomNumber {

	public static void main(String[] args) {
		// Generating random number everytime u run the script
		
		Random random=new Random();
		int ranInt=random.nextInt(200);
		System.out.println(ranInt);

	}

}
