package com.CS362.app;

import java.util.Random;
//import java.util.Arrays;

/**
 * Rational Number Check
 * This program checks a number out to 1000 decimals to see if it's rational
 * by checking that no digit shows up sequentially more than 19 times &
 * by checking that no sequence of 20 digits matches the next sequence of
 * 20 digits
 * Yes, this is a bad algorithm for checking if a number is rational
 */
public class App 
{
	//this function is for detecting the varied digit repetition of a fraction such as 2/7
	static short checkRational(byte[] number)
	{
		//perhaps this is redundant with the way readIn() works
		//this window occupies 20 characters & every time the inner loop loops through the array and doesn't
		//find another set of 20 array indices (not in the window) that match the array indices in the window
		//the window moves forward 20 indices
		short windowSize = 20;
		for (short window = 0; window < 1000; window += windowSize)
		{
			//Start one past the window for efficiency, and to make sure
			//the window isn't checked against itself if the indices match
			for (short i = (short)(window + 1); i < 1000/* - windowSize*/; i++)
			{
				if (number[i] == number[window])
				{
					//if the next 20 indices match then this number is rational
					for (short j = i, k = window; number[j] == number[k]; j++, k++)
					{
						if (i < (j - windowSize + 1))
							return window; //the advantage of returning this is that I can use this to denote truth
						//and to denote where the 20 numbers in the array that repeat are
					}
				}
			}
		}
		return -1; //Since 0 is a valid window value, this suffices as "false"
	}
	//Not only does this function read in, but it checks for the most simple case of repetition, single digit repetition
	static byte readIn(byte[] number, short numerator, short denominator)
	{
		byte repeat = 0; //Used to count the number of times a variable has repeated
		boolean isNegative = false; //Used to keep track of whether the result of the fraction is negative
		short remainder = 0;
		if(denominator != 0)
			remainder = (short) (numerator % denominator);
		if (isNegative)
			numerator *= -1;
		for (short i = 0; i < 1000; i++, repeat++)
		{
			remainder = (short) (10 * remainder);
			while (remainder >= denominator)
			{
				//This will find the remainder of remainder/denominator, as well as how many times denominator fits into remainder
				remainder -= denominator;
				number[i]++;
			}
			//Reset repeat when two sequential numbers don't match
			if (i > 0 && number[i] != number[i - 1])
				repeat = 0;
			//If this is the twentieth matching number
			else if (repeat > 19)
				return number[i];
		}
				   //Not a fail case, but in this case the array was filled without
		return -1; //concluding that the number is rational
	}
	//This function actually does the printing
	static void printOutput(byte temp, byte [] number){
		if (temp < 0)
		{
			short checkRationalNumber = checkRational(number);
			if (checkRationalNumber >= 0) //when I just had >, this caused a nasty bug when window was 0
			{
				System.out.println(" is a rational number,");
				System.out.print("because this sequence of numbers occurs more than once: ");
				short i = checkRational(number);
				for (short j = i; i - 20 < j; i++)
					System.out.print(number[i]);
			}
			else
				System.out.print(" is irrational.");
		}
		else
		{
			System.out.println(" is a rational number,");
			System.out.print("because there's a sequence of at least 20 repeating " + temp + "'s");
		}
		System.out.println();
	}
	//The printed output should have the same format every time, so this function makes
	//it easy to check multiple fractions
	static void takeFraction(short numerator, short denominator){
		//short numerator = 0, denominator = 0;
		byte [] number = new byte[1000]; //To hold 1000 digits, & { } to initialize them all to 0
		byte temp = readIn(number, numerator, denominator);
		System.out.print(numerator + "/" + denominator);
		printOutput(temp, number);
	}
	//The printed output should have the same format every time, so this function makes
	//it easy to check multiple randomly generated numbers
	static void takeRandom(){
		//short numerator = 0, denominator = 0;
		Random rand = new Random();
		byte [] number = new byte[1000]; //To hold 1000 digits, & { } to initialize them all to 0
		byte temp = -1;
		for(short i = 0, j = 1; j < 1000 && temp <= 20; i++, j++, temp++){
			number[j] = (byte) rand.nextInt(10);
			if (number[i] != number[j])
				temp = -2;
		}
		//if (temp < 20)
			//temp = -1;
		System.out.print("0.");
		for(short i = 0; i < 100; i++)
			System.out.print(number[i]);
		System.out.print("...");
		printOutput(temp, number);
	}
	public static void main(String args[])
	{
		short numerator = 1, denominator = 2;//This SHOULD NOT fail
		takeFraction(numerator, denominator);
		numerator = 137; 
		denominator = 61;
		takeFraction(numerator, denominator);
		numerator = 6; 
		denominator = 49;
		takeFraction(numerator, denominator);
		numerator = 17;
		denominator = 89;
		takeFraction(numerator, denominator);
		numerator = 1;
		denominator = 9899;
		takeFraction(numerator, denominator);
		numerator = 2739;
		denominator = 846; //Testing the limits of this mediocre algorithm
		takeFraction(numerator, denominator);
		for(byte i = 0; i < 10; i++){
			takeRandom();
		}
		//return 0;
	}
}
