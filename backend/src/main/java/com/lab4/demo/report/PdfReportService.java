package com.lab4.demo.report;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.lab4.demo.frontoffice.model.dto.BookDTO;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PdfReportService implements ReportService {

    @Override
    public String export(List<BookDTO> books) {
        exportPDD(books);
        //exportJasper(books);

        return "PDF saved successfully";
    }

    public void exportPDD(List<BookDTO> books) {
        createPDD(books);
    }

    public static void drawTable(PDPage page, PDPageContentStream contentStream,
                                 float y, float margin, String[][] content) throws IOException {
        final int rows = content.length;
        final int cols = content[0].length;
        final float rowHeight = 20.0f;
        final float tableWidth = page.getMediaBox().getWidth() - 2.0f * margin;
        final float tableHeight = rowHeight * (float) rows;
        final float colWidth = tableWidth / (float) cols;

        //draw the rows
        float nexty = y ;
        for (int i = 0; i <= rows; i++) {
            contentStream.moveTo(margin, nexty);
            contentStream.lineTo(margin + tableWidth, nexty);
            contentStream.stroke();
            nexty-= rowHeight;
        }

        //draw the columns
        float nextx = margin;
        for (int i = 0; i <= cols; i++) {
            contentStream.moveTo(nextx, y);
            contentStream.lineTo(nextx, y - tableHeight);
            contentStream.stroke();
            nextx += colWidth;
        }

        //now add the text
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12.0f);

        final float cellMargin = 5.0f;
        float textx = margin + cellMargin;
        float texty = y - 15.0f;

        contentStream.beginText();
        contentStream.newLineAtOffset(textx, texty + rowHeight);
        contentStream.showText("List of books out of stock");
        contentStream.endText();

        for (final String[] aContent : content) {
            for (String text : aContent) {
                contentStream.beginText();
                contentStream.newLineAtOffset(textx, texty);
                contentStream.showText(text);
                contentStream.endText();
                textx += colWidth;
            }
            texty -= rowHeight;
            textx = margin + cellMargin;
        }
    }

    private void createPDD(List<BookDTO> books) {
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage( page );

        PDPageContentStream contentStream = null;
        try {
            contentStream = new PDPageContentStream(doc, page);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] headers = new String[]{"Author", "Title", "Price"};

        List<String[]> content = new ArrayList<>();
        content.add(headers);

        for (BookDTO book: books){
            content.add(new String[]{book.getAuthor(), book.getTitle(), String.valueOf(book.getPrice())});
        }
        String[][] mat = content.toArray(new String[0][]);

        try {
            drawTable(page, contentStream, 700, 100, mat);
            contentStream.close();
            SimpleDateFormat ft = new SimpleDateFormat("MM-dd hh-mm-ss");
            Date date = new Date();
            //doc.save("PDFBox " + ft.format(date) + ".pdf");
            doc.save("report.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*public void exportJasper(List<Book> books){
        try
        {
            File pdfFile = File.createTempFile("my-invoice", ".pdf");
            FileOutputStream pos = new FileOutputStream(pdfFile);

        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }*/


    @Override
    public ReportType getType() {
        return ReportType.PDF;
    }

}
