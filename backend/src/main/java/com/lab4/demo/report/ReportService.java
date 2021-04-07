package com.lab4.demo.report;

import com.lab4.demo.frontoffice.model.dto.BookDTO;

import java.util.List;

public interface ReportService {
    String export(List<BookDTO> books);

    ReportType getType();
}
