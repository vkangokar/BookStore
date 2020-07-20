package com.neu.store.pojo; 
import java.util.List;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Document;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;

import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.Paragraph;


public class PDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document documentPdf, PdfWriter pdfwriter, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Font  helvetica_18_blue = new Font(Font.HELVETICA, 18, Font.BOLDITALIC, Color.BLUE);
		
		List<Book> cartlist = (List<Book>) model.get("cartitems");
		
		Paragraph pt = new Paragraph("Thank you for shopping  !!!!", helvetica_18_blue);
		
		Phrase pdtName = new Phrase("Below is the list of items that you have purchased with us!!");
		
		Phrase t = new Phrase("Hope to see you again!!");
		
		PdfPTable tb = new PdfPTable(3);
		tb.setWidthPercentage(100.0f);
		tb.setWidths(new float[] {3.0f, 2.0f, 1.0f});
		tb.setSpacingBefore(10);
		
		PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(5);
		
        cell.setPhrase(new Phrase("Product Title", helvetica_18_blue));
        tb.addCell(cell);
         
        cell.setPhrase(new Phrase("Category", helvetica_18_blue));
        tb.addCell(cell);
 
        cell.setPhrase(new Phrase("Price", helvetica_18_blue));
        tb.addCell(cell);
        
        for (Book bbc : cartlist) {
        	tb.addCell(bbc.getTitle());
        	tb.addCell(bbc.getCategory());
        	tb.addCell(String.valueOf(bbc.getTotalprice()));
        }
        
        documentPdf.add(pt);
        documentPdf.add(pdtName);
        documentPdf.add(tb);
        documentPdf.add(t);
	}

	
}


