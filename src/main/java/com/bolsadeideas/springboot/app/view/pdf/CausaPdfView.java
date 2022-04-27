package com.bolsadeideas.springboot.app.view.pdf;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bolsadeideas.springboot.app.models.entity.Causa;
import com.bolsadeideas.springboot.app.models.entity.Informacion;
import com.bolsadeideas.springboot.app.models.service.ICausaService;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("verCausa")
public class CausaPdfView extends AbstractPdfView {
	@Autowired
	 private ICausaService causaService;
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Causa causa= (Causa) model.get("causa");
		Image imagen = Image.getInstance("D:\\Abril\\Cursos\\CursoSpring\\workspace\\spring-boot-data-jpa\\src\\main\\resources\\static\\images\\logo.png");
		imagen.setAlignment(Element.ALIGN_RIGHT);
		imagen.scalePercent(40);
		document.add(imagen);
		
		PdfPTable tabla = new PdfPTable(1);
		tabla.setSpacingAfter(20);
		PdfPCell cell = null;
		
		Font fuenteTitulo=FontFactory.getFont("Helvetica", 13,Color.black);
		Font fuenteSubT=FontFactory.getFont("Arial", 11,Color.black);
		
		cell = new PdfPCell(new Phrase("DATOS DE LA CAUSA", fuenteTitulo));
		cell.setBackgroundColor(new Color(205, 225, 224));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(8f);
		tabla.addCell(cell);

		cell = new PdfPCell(new Phrase("N°: "+ causa.getNumExpediente(), fuenteSubT));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPadding(8f);
		tabla.addCell(cell);
	
		cell = new PdfPCell(new Phrase("Carátula: "+ causa.getCaratula(), fuenteSubT));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPadding(8f);
		tabla.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Fecha: "+causa.getFecha(), fuenteSubT));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPadding(8f);
		tabla.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Victima: "+ causa.getVictima(),fuenteSubT));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPadding(8f);
		tabla.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Victimario: "+ causa.getVictimario(), fuenteSubT));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPadding(8f);
		tabla.addCell(cell);
		
		
		PdfPTable tabla2 = new PdfPTable(1);
		cell = new PdfPCell(new Phrase("Informacion asociada a la causa", fuenteTitulo));
		cell.setBorder(0);
		cell.setBackgroundColor(new Color(184, 230, 203));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(8f);
		tabla2.addCell(cell);
		tabla2.setSpacingAfter(10);
		PdfPTable tabla3 = new PdfPTable(3);
		tabla3.setWidths(new float [] {1f, 3, 1});
		tabla3.addCell("Fecha");
		tabla3.addCell("Descripción");
		tabla3.addCell("Tipo de información");
		
		for(Informacion info: causaService.listaInformaciones(causa)) {
			tabla3.addCell(""+info.getFecha());
			tabla3.addCell(info.getDescripcion());
			if(info.getEsLlamada()) {
				tabla3.addCell("Llamada telefónica");
			}else if(info.getEsMovimiento()) {
				tabla3.addCell("Movimiento bancario");
			}else {
				tabla3.addCell("Red social");
			}
			
			
		}
		
		
		document.add(tabla);
		document.add(tabla2);
		document.add(tabla3);
		
		
	}
	

}
