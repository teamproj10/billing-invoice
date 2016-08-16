package generateinvoice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerateInvoice{
	 private BaseFont bfBold;
	 private BaseFont bf;
	 private int pageNumber = 0;
	 public GenerateInvoice(String filename,String[] client,String[] invoice,String projectname,ArrayList developer){
		 String pdfFilename = "";
		 //GenerateInvoice generateInvoice = new GenerateInvoice();
		 pdfFilename = filename+".pdf".trim();
		 createPDF(pdfFilename,client,invoice,projectname,developer);
	 }

	 private void createPDF (String pdfFilename,String[] client,String[] invoice,String projectname,ArrayList developer){
		 Document doc = new Document();
		 PdfWriter docWriter = null;
		 initializeFonts();
		 try {
			 String path = "C:/Users/Mike/Desktop/" + pdfFilename;
			 docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
			 doc.addAuthor("Sowmya");
			 doc.addCreationDate();
			 doc.addProducer();
			 doc.addCreator("Sowmya");
			 doc.addTitle("Invoice");
			 doc.setPageSize(PageSize.LETTER);
			 doc.open();
			 PdfContentByte cb = docWriter.getDirectContent();	   
			 boolean beginPage = true;
			 createHeadings(cb,375,780,"Eagle Consultancy");
			 int y = 0;
			 for(int i=0; i < developer.size(); i++ ){
				 String[] tokens=(String[])developer.get(i);
				 if(beginPage){
					 beginPage = false;
					 generateLayout(doc, cb); 
					 generateHeader(doc, cb, client,invoice);
					 y = 615; 
				 }
				 generateDetail(doc, cb, i, y, tokens);
				 y = y - 15;
				 if(y < 50){
					 printPageNumber(cb);
					 doc.newPage();
					 beginPage = true;
				 }
			 }
			 printPageNumber(cb);
		 }catch(DocumentException dex){
			 dex.printStackTrace();
		 }
		 catch(Exception ex){
			 ex.printStackTrace();
		 }finally{
			 if(doc != null){
				 doc.close();
			 }
			 if(docWriter != null){
				 docWriter.close();
			 }
		 }
	 }
	 private void generateLayout(Document doc, PdfContentByte cb){
		 try{
			 cb.setLineWidth(1f);
			 cb.rectangle(40,650,510,120);
			 cb.moveTo(275,650);
			 cb.lineTo(275,770);
			 cb.stroke();

			 createHeadings(cb,415,753,"Invoice Number: ");
			 createHeadings(cb,415,733,"Invoice Date: ");
			 createHeadings(cb,415,713,"Payment Terms: ");
			 createHeadings(cb,395,693,"Billing Frequency: ");
			 createHeadings(cb,395,673,"Total Amount Due: $");

			 cb.rectangle(20,50,550,600);
			 cb.moveTo(20,630);
			 cb.lineTo(570,630);
			 cb.moveTo(50,50);
			 cb.lineTo(50,650);
			 cb.moveTo(150,50);
			 cb.lineTo(150,650);
			 cb.moveTo(430,50);
			 cb.lineTo(430,650);
			 cb.moveTo(500,50);
			 cb.lineTo(500,650);
			 //cb.stroke();

			 createHeadings(cb,50,633,"Date");
			 createHeadings(cb,150,633,"Description");
			 createHeadings(cb,400,633,"Rate");
			 createHeadings(cb,450,633,"Hours");
			 createHeadings(cb,500,633,"Amount");

			   //add the images
		//	   Image companyLogo = Image.getInstance("images/olympics_logo.gif");
		//	   companyLogo.setAbsolutePosition(25,700);
		//	   companyLogo.scalePercent(25);
		//	   doc.add(companyLogo);
		 }catch (Exception dex){
			 dex.printStackTrace();
		 }
	 }
	 
	 private void generateHeader(Document doc, PdfContentByte cb, String[] client,String[] invoice){
		 try{
			 SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
			 createHeadings(cb,100,750,client[1]);
			 createHeadings(cb,100,735,client[2]);
			 createHeadings(cb,100,720,client[3]);
			 createHeadings(cb,100,705,client[4]+", "+client[5]+" - "+client[6]);
			 createHeadings(cb,100,690,"US");
			 createHeadings(cb,100,675,"Client ID: "+client[0]);
			 createHeadings(cb,100,660,"Project: Website Ordering");
			 createHeadings(cb,482,753,invoice[0]);
			 createHeadings(cb,482,733,sdf.format(new Date()));
			 createHeadings(cb,482,713,client[9]);
			 createHeadings(cb,482,693,client[10]);
			 createHeadings(cb,482,673,invoice[4]);
		 }catch (Exception ex){
			 ex.printStackTrace();
		 }
	 }
	 
	 private void generateDetail(Document doc, PdfContentByte cb, int index, int y, String[] tokens)  {
		 DecimalFormat df = new DecimalFormat("0.00");
		 try{
			 createContent(cb,95,y,tokens[7],PdfContentByte.ALIGN_RIGHT);
			 createContent(cb,150,y, tokens[2],PdfContentByte.ALIGN_LEFT);
			 createContent(cb,400,y, tokens[5],PdfContentByte.ALIGN_LEFT);
//			 double price = Double.valueOf(df.format(Math.random() * 10));
//			 double extPrice = price * (index+1) ;
			 if(tokens[3]!=null && !tokens[3].equalsIgnoreCase("0")){
				 createContent(cb,470,y, tokens[3],PdfContentByte.ALIGN_RIGHT);
				 createContent(cb,525,y, ""+(Integer.parseInt(tokens[3])*Integer.parseInt(tokens[5])),PdfContentByte.ALIGN_RIGHT);
			 }else{
				 createContent(cb,470,y, tokens[4],PdfContentByte.ALIGN_RIGHT);
				 createContent(cb,525,y, ""+(Integer.parseInt(tokens[4])*Integer.parseInt(tokens[5])),PdfContentByte.ALIGN_RIGHT);
			 }		 
			 
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
	 }

	 private void createHeadings(PdfContentByte cb, float x, float y, String text){
		 cb.beginText();
		 cb.setFontAndSize(bfBold, 8);
		 cb.setTextMatrix(x,y);
		 cb.showText(text.trim());
		 cb.endText();
	 }
	 
	 private void printPageNumber(PdfContentByte cb){
		 cb.beginText();
		 cb.setFontAndSize(bfBold, 8);
		 cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber+1), 570 , 25, 0);
		 cb.endText();
		 pageNumber++;	  
	 }
	 
	 private void createContent(PdfContentByte cb, float x, float y, String text, int align){
		 cb.beginText();
		 cb.setFontAndSize(bf, 8);
		 cb.showTextAligned(align, text.trim(), x , y, 0);
		 cb.endText(); 
	 }

	 private void initializeFonts(){
		 try {
			 bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			 bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		 }catch(DocumentException e){
			 e.printStackTrace();
		 }catch(IOException e){
			 e.printStackTrace();
		 }
	 }
}
