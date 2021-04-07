package com.lab4.demo.report;

import com.lab4.demo.frontoffice.model.dto.BookDTO;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CSVReportService implements ReportService {

    @Override
    public String export(List<BookDTO> books) {
        SimpleDateFormat ft = new SimpleDateFormat("MM-dd hh-mm-ss");
        Date date = new Date();
        File file = new File(ft.format(date) + ".csv");

        try{
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            for (BookDTO book: books) {
                if (book.getQuantity() == 0) {
                    String[] data = {book.getAuthor(), book.getTitle(), String.valueOf(book.getPrice())};
                    writer.writeNext(data);
                }
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return "I am a CSV reporter.";
    }

    @Override
    public ReportType getType() {
        return ReportType.CSV;
    }
}
