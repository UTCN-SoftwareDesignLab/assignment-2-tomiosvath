package com.lab4.demo.report;

import com.lab4.demo.frontoffice.model.dto.BookDTO;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PdfReportService implements ReportService {

    @Override
    public String export(List<BookDTO> books) {
        exportPDD(books);
        //exportJasper(books);

        return "PDF saved successfully";
    }

    public void exportPDD(List<BookDTO> books){
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage( page );
        PDPageContentStream contentStream;
        try {
            contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont( PDType1Font.TIMES_ROMAN, 16 );
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 725);
            contentStream.showText("List of books out of stock");
            contentStream.newLine();
            contentStream.newLine();
            int index = 1;
            for (BookDTO book: books) {
                if (book.getQuantity() == 0) {
                    contentStream.showText(index + ". " + book.getAuthor() + ", " + book.getTitle() + ", price: " + book.getPrice() + "$");
                    contentStream.newLine();
                    index++;
                }
            }
            contentStream.endText();

            contentStream.close();

            SimpleDateFormat ft = new SimpleDateFormat("MM-dd hh-mm-ss");
            Date date = new Date();
            document.save("PDFBox " + ft.format(date) + ".pdf");
            document.close();
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
