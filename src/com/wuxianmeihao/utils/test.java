package com.wuxianmeihao.utils;

import java.util.HashSet;
import java.util.Set;

public class test {
	public static void main(String[] args){
		Set<String> xxx = new HashSet<String>();
		xxx.add("fdafdafa");
		xxx.add("2");
		xxx.add("3");
		xxx.add("4");
		xxx.add("5");
		System.out.println(xxx.toString().substring(1, xxx.toString().length()-1).trim());
	}
}
