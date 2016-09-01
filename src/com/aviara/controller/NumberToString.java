/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aviara.controller;

/**
 *
 * @author comp2
 */
public class NumberToString {
    String input;
    int num;
    String[] month=
    {
       "",
       "Jan",
       "Feb",
       "Mar",
       "Apr",
       "May",
       "Jun",
       "Jul",
       "Aug",
       "Sep",
       "Oct",
       "Nov",
       "Dec"
    };
            
    String[] units=
    {"",
     " One",
     " Two",
     " Three",
     " Four",
     " Five",
     " Six",
     " Seven",
     " Eight",
     " Nine"
    };
    String[] teen=
   {" Ten",
    " Eleven",
    " Twelve",
    " Thirteen",
    " Fourteen",
    " Fifteen",
    " Sixteen",
    " Seventeen",
    " Eighteen",
    " Nineteen"
   };
    String[] tens=
  { " Twenty",
    " Thirty",
    " Forty",
    " Fifty",
    " Sixty",
    " Seventy",
    " Eighty",
    " Ninety"
  };
   String[] maxs=
 {"",
  "",
  " Hundred",
  " Thousand",
  " Lakh",
  " Crore"
 };
    public String convertNumberToWords(int n) {
         input=numToString(n);
        String converted=""; 
        int pos=1; 
        boolean hun=false;
        while(input.length()> 0)
        {
            if(pos==1) // TENS AND UNIT POSITION
            {   if(input.length()>= 2) // TWO DIGIT NUMBERS
                {   
                 String temp=input.substring(input.length()-2,input.length());
                 input=input.substring(0,input.length()-2);
                 converted+=digits(temp);
                }
                else if(input.length()==1) // 1 DIGIT NUMBER
                {
                 converted+=digits(input); 
                 input="";
                }
                pos++;
            }
            else if(pos==2) // HUNDRED POSITION
            { 
                String temp=input.substring(input.length()-1,input.length());
                input=input.substring(0,input.length()-1);
                if(converted.length()> 0&&digits(temp)!="")
                {
                    converted=(digits(temp)+maxs[pos]+" and")+converted;
                    hun=true;
                }
                else
                {
                    if
                    (digits(temp)=="");
                    else
                    converted=(digits(temp)+maxs[pos])+converted;hun=true;
                }
                pos++;
            }
            else if(pos > 2) // REMAINING NUMBERS PAIRED BY TWO
            {
                if(input.length()>= 2) // EXTRACT 2 DIGITS
                {  
                 String temp=input.substring(input.length()-2,input.length());
                 input=input.substring(0,input.length()-2);
                   if(!hun&&converted.length()> 0)
                        converted=digits(temp)+maxs[pos]+" and"+converted;
                    else
                    {
                        if(digits(temp)=="")  ;
                        else
                        converted=digits(temp)+maxs[pos]+converted;
                    }
                 }
                 else if(input.length()==1) // EXTRACT 1 DIGIT
                 {
                   if(!hun&&converted.length()> 0)
                    converted=digits(input)+maxs[pos]+" and"+converted;
                    else
                    {
                        if(digits(input)=="")  ;
                        else
                        converted=digits(input)+maxs[pos]+converted;
                        input="";
                    }
                 }
                 pos++; 
             }
        }
        return converted;
    }
    
   private String digits(String temp) // TO RETURN SELECTED NUMBERS IN WORDS
    {
        String converted="";
        for(int i=temp.length()-1;i >= 0;i--)
        {   int ch=temp.charAt(i)-48;
            if(i==0&&ch>1 && temp.length()> 1)
            converted=tens[ch-2]+converted; // IF TENS DIGIT STARTS WITH 2 OR MORE IT FALLS UNDER TENS
            else if(i==0&&ch==1&&temp.length()==2) // IF TENS DIGIT STARTS WITH 1 IT FALLS UNDER TEENS
            {
                int sum=0;
                for(int j=0;j < 2;j++)
                sum=(sum*10)+(temp.charAt(j)-48);
                return teen[sum-10];
            }
            else
            {
                if(ch > 0)
                converted=units[ch]+converted;
            } // IF SINGLE DIGIT PROVIDED
        }
        return converted;
    }
   
    private void inputNumber(int n) {
      try
        {
         // System.out.print("Please enter number to Convert into Words : ");
         int num=n;
        }
        catch(Exception e)
        {
         System.out.println("Number should be Less than 1 Arab ");
         System.exit(1);
        } 
    }

   private String numToString(int x) {
         String num="";
        while(x!=0)
        {
            num=((char)((x%10)+48))+num;
            x/=10;
        }
        return num;
    }
    
}
