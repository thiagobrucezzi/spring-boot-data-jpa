package com.bolsadeideas.springboot.app.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.bolsadeideas.springboot.app.models.entity.Causa;


@Component("verCausa.xlsx")
public class CausaXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		Causa causa= (Causa) model.get("Causa");
		Sheet sheet= workbook.createSheet("Causa");
		
		Row row = sheet.createRow(0);
		Cell cell=row.createCell(0);
		cell.setCellValue("Datos de la causa");
		
		sheet.createRow(2).createCell(0).setCellValue("N°: "+causa.getNumExpediente());		
		sheet.createRow(3).createCell(0).setCellValue("Carátula: "+causa.getCaratula());
		sheet.createRow(4).createCell(0).setCellValue("Fecha: "+causa.getFecha());		
		sheet.createRow(5).createCell(0).setCellValue("Victima: "+ causa.getVictima());
		sheet.createRow(6).createCell(0).setCellValue("Victimario: "+ causa.getVictimario());	
		
		sheet.createRow(6).createCell(0).setCellValue("Carátula: "+causa.getCaratula());
		
		
		
		
		
		
	}

}
