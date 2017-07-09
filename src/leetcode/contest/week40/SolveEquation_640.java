package leetcode.contest.week40;

import java.util.ArrayList;
import java.util.List;

public class SolveEquation_640 {

	    private static List<String> retrieveElements(String s){
	        List<String> res = new ArrayList<>();
	        int p = 0;
	        char nextSign='+';
	        for(int n=0;n<s.length();n++){
	            if(s.charAt(n)=='+'){
	            	if(!"".equals(s.substring(p,n).trim()))
	            		res.add(nextSign+s.substring(p,n));
	                p=n+1;
	                nextSign='+';
	            }else if(s.charAt(n)=='-'){
	            	if(!"".equals(s.substring(p,n).trim()))
	            		res.add(nextSign+s.substring(p,n));
	                p=n+1;
	                nextSign='-';
	            }else if(n==s.length()-1){
	            	res.add(nextSign+s.substring(p));
	            }
	            
	        }
	        
	        return res;
	    }
	    public static String solveEquation(String equation) {
	        String left = equation.substring(0,equation.indexOf("="));
	        String right = equation.substring(equation.indexOf("=")+1,equation.length());
	        int numXL=0,numXR=0;
	        List<String> leftElements = retrieveElements(left);
	        List<String> rightElements = retrieveElements(right);
	        int leftSum=0,rightSum=0;
	        for(String s:leftElements){
	            if(s.indexOf("x")!=-1){
	                if(s.charAt(0)=='+' &&  s.length()==2)numXL++;
	                else if(s.charAt(0)=='-' &&  s.length()==2)numXL--;
	                else{
	                    String tmp = s.substring(0,s.indexOf("x"));
	                        int nx = Integer.parseInt(tmp);
	                        numXL+=nx;
	                }
	            }
	            else{
	                leftSum+=Integer.parseInt(s);
	            }
	        }
	        
	        //right
	        for(String s:rightElements){
	            if(s.indexOf("x")!=-1){
	                if(s.charAt(0)=='+' &&  s.length()==2)numXR++;
	                else if(s.charAt(0)=='-' &&  s.length()==2)numXR--;
	                else{
	                    String tmp = s.substring(0,s.indexOf("x"));
	                        int nx = Integer.parseInt(tmp);
	                        numXR+=nx;
	                }
	            }
	            else{
	                rightSum+=Integer.parseInt(s);
	            }
	        }
	        if(rightSum==leftSum &&numXL==numXR)return "Infinite solutions";
	        if(numXL==numXR)return "No solution";
	        return "x="+((rightSum-leftSum)/(numXL-numXR));
	    }
	    public static void main(String[] args){
	    	//System.out.println(solveEquation("x+5-3+x=6+x-2"));
	    	System.out.println(solveEquation("-x=-1"));
	    }
	}