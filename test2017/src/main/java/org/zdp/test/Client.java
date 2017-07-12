package org.zdp.test;

import java.util.ArrayList;
import java.util.List;

import org.zdp.domain.Fruit;
import org.zdp.jxls.ExcelUtil;

/**
 * ������.
 */
public class Client { 
	public static void main(String[] args) {
		List<Fruit> list = new ArrayList<Fruit>();
		list.add(new Fruit("ƻ��",20.0f));
		list.add(new Fruit("����",30.0f));
		String templateFileName = "template.xlsx";
		String resultFileName = "result.xlsx";
		new ExcelUtil().createExcel(templateFileName,list,resultFileName);
	}
}