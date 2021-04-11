package com.lab4.demo.frontoffice;

import com.lab4.demo.frontoffice.model.dto.BookDTO;
import com.lab4.demo.report.ReportServiceFactory;
import com.lab4.demo.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.lab4.demo.UrlMapping.FRONT_OFFICE;
import static org.apache.tomcat.util.http.fileupload.IOUtils.copy;

@RestController
@RequestMapping(FRONT_OFFICE)
@RequiredArgsConstructor
public class FrontOfficeController {

    private final BookService bookService;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping
    public List<BookDTO> allItems() {
        return bookService.findAll();
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO book) {
        return bookService.create(book);
    }

    @PatchMapping
    public BookDTO edit(@RequestBody BookDTO book){
        return bookService.edit(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PatchMapping("/sell")
    public void sell(@RequestBody BookDTO book) {
        bookService.sell(book.getId());
    }

    @GetMapping("/export/{type}")
    public void export(@PathVariable ReportType type){
        reportServiceFactory.getReportService(type).export(bookService.findAllByQuantity(0));
    }

    @RequestMapping(value = "/files/{file_name}/{type}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("file_name") String fileName,
            @PathVariable("type") ReportType type,
            HttpServletResponse response) {

        reportServiceFactory.getReportService(type).export(bookService.findAllByQuantity(0));
        try {
            InputStream is = new FileInputStream(fileName);
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }

    }
}
