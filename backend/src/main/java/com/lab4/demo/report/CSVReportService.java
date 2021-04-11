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
    private final String fileName = "report.csv";

    @Override
    public String export(List<BookDTO> books) {
        createCSV(books);
        return "I am a CSV reporter.";
    }

    private void createCSV(List<BookDTO> books){
        SimpleDateFormat ft = new SimpleDateFormat("MM-dd hh-mm-ss");
        Date date = new Date();
        //File file = new File(ft.format(date) + ".csv");
        File file = new File(fileName);

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
    }

    @Override
    public ReportType getType() {
        return ReportType.CSV;
    }

    @Override
    public void delete() {
        File file = new File(fileName);
        if(file.delete()) {
            System.out.println("File deleted successfully");
        }
        else {
            System.out.println("Failed to delete the file");
        }
    }
}
