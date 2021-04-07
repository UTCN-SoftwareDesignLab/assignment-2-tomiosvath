package com.lab4.demo.frontoffice;

import com.lab4.demo.UrlMapping;
import com.lab4.demo.frontoffice.model.Book;
import com.lab4.demo.frontoffice.model.dto.BookDTO;
import com.lab4.demo.frontoffice.model.dto.ItemDTO;
import com.lab4.demo.report.ReportServiceFactory;
import com.lab4.demo.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.EXPORT_REPORT;
import static com.lab4.demo.UrlMapping.FRONT_OFFICE;

@RestController
@RequestMapping(FRONT_OFFICE)
@RequiredArgsConstructor
public class FrontOfficeController {

    /*
    private final ItemService itemService;
    private final ReportServiceFactory reportServiceFactory;


    @GetMapping
    public List<ItemDTO> allItems() {
        return itemService.findAll();
    }

    @PostMapping
    public ItemDTO create(@RequestBody ItemDTO item) {
        return itemService.create(item);
    }

    @PatchMapping
    public ItemDTO edit(@RequestBody ItemDTO item) {
        return itemService.edit(item);
    }

    @GetMapping(EXPORT_REPORT)
    public String exportReport(@PathVariable ReportType type) {
        return reportServiceFactory.getReportService(type).export();
    }
     */

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
        reportServiceFactory.getReportService(type).export(bookService.findOutOfStock());
    }
}
