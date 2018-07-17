/*
Project Objective: Use SHA256 as the hash function to hash the content of a file.
Name: Vincent Hael
ID: 2583
Date: 4/27/18
Course: Applied Cryptography
Teacher: Professor Navid Khoshavi
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Project3CodeHaelVincent
{
    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter directory path: "); //Example: /Users/Vincenthael/HelloWorld.txt
        readFile(input.nextLine()); //Reads File
    }
    private static void readFile(String filename)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                System.out.println(line); //Original Unencrypted Line
                hashFunction(line); //SHA-256
            }
            reader.close();
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
        }
    }
    private static void hashFunction(String toBeHashed)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); //Encryption Library
            byte [] encodedhash = digest.digest(toBeHashed.getBytes(StandardCharsets.UTF_8));
            System.out.printf(bytesToHexString(encodedhash)); //Turns Bytes into a Hex String
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }
    private static String bytesToHexString(byte[] hash)
    {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) //runs through the encodedhash byte array
        {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1)
            {
                hexString.append('0'); //Appending 0 before the hex value
            }
                hexString.append(hex);
        }
        return hexString.toString();
    }
}
