package com.example.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFConnector;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnection;

public class XSSFShapeDemo {

	public static void main(String[] args) {
		try {
			
			String filePath = "C:\\Users\\minh-khang\\Desktop\\Excel File\\input\\DetailDesign.xlsx";
			final File file = new File(filePath);
			InputStream inp = new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(inp);
			XSSFSheet sheet1 = (XSSFSheet) wb.getSheetAt(0);

			// returns the existing SpreadsheetDrawingML from the sheet, or creates a new
			// one
			XSSFDrawing drawing = sheet1.createDrawingPatriarch();

			// loop through all of the shapes in the drawing area
			for (XSSFShape shape : drawing.getShapes()) {
				
				System.out.println(shape.getAnchor().getDx1()+" - " + shape.getAnchor().getDx2()
						+" - " + shape.getAnchor().getDy1()+" - " + shape.getAnchor().getDy2());
				
				
				if (shape instanceof XSSFSimpleShape) {
					XSSFSimpleShape sShape = (XSSFSimpleShape) shape;
					//sShape.getCTShape().getNvSpPr().getCNvPr().ge
					//sShape.get
					System.out.println("XSSFSimpleShape = " + sShape.getShapeId() + "->" + sShape.getText());
					//System.out.println(((XSSFSimpleShape) shape).getText());
					//System.out.println(((XSSFSimpleShape) shape).getShapeName());
				}
				
				if (shape instanceof XSSFConnector) {
					CTConnection stCxn = ((XSSFConnector) shape).getCTConnector().getNvCxnSpPr().getCNvCxnSpPr().getStCxn();
					//stCxn.getId()
					CTConnection endCxn = ((XSSFConnector) shape).getCTConnector().getNvCxnSpPr().getCNvCxnSpPr().getEndCxn();
					//System.out.println(((XSSFConnector) shape).);
					System.out.println("CTConnection:  stCxn = "+ stCxn.getId() + "  endCxn =  " + endCxn.getId());
				}
				
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
