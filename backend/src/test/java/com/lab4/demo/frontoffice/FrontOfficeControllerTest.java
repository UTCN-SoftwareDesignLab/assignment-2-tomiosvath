package com.lab4.demo.frontoffice;

import com.lab4.demo.BaseControllerTest;

import static com.lab4.demo.TestCreationFactory.randomLong;
import static com.lab4.demo.TestCreationFactory.randomString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class FrontOfficeControllerTest extends BaseControllerTest {
/*
    @InjectMocks
    private FrontOfficeController controller;

    @Mock
    private ItemService itemService;

    @Mock
    private ReportServiceFactory reportServiceFactory;

    @Mock
    private CSVReportService csvReportService;

    @Mock
    private PdfReportService pdfReportService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        controller = new FrontOfficeController(itemService, reportServiceFactory);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allItems() throws Exception {
        List<ItemDTO> items = TestCreationFactory.listOf(Item.class);
        when(itemService.findAll()).thenReturn(items);

        ResultActions response = mockMvc.perform(get(FRONT_OFFICE));

        response.andExpect(status().isOk())
                .andExpect(jsonContentToBe(items));

    }

    @Test
    void exportReport() throws Exception {
        when(reportServiceFactory.getReportService(PDF)).thenReturn(pdfReportService);
        when(reportServiceFactory.getReportService(CSV)).thenReturn(csvReportService);
        String pdfResponse = "PDF!";
        when(pdfReportService.export()).thenReturn(pdfResponse);
        String csvResponse = "CSV!";
        when(csvReportService.export()).thenReturn(csvResponse);

        ResultActions pdfExport = mockMvc.perform(get(FRONT_OFFICE + EXPORT_REPORT, PDF.name()));
        ResultActions csvExport = mockMvc.perform(get(FRONT_OFFICE + EXPORT_REPORT, CSV.name()));

        pdfExport.andExpect(status().isOk())
                .andExpect(content().string(pdfResponse));
        csvExport.andExpect(status().isOk())
                .andExpect(content().string(csvResponse));

    }

    @Test
    void create() throws Exception {
        ItemDTO reqItem = ItemDTO.builder().name(randomString())
                .description(randomString())
                .build();

        when(itemService.create(reqItem)).thenReturn(reqItem);

        ResultActions result = performPostWithRequestBody(FRONT_OFFICE, reqItem);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqItem));
    }

    @Test
    void edit() throws Exception {
        ItemDTO reqItem = ItemDTO.builder()
                .id(randomLong())
                .name(randomString())
                .description(randomString())
                .build();

        when(itemService.edit(reqItem)).thenReturn(reqItem);

        ResultActions result = performPutWithRequestBody(FRONT_OFFICE, reqItem);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqItem));
    }
    */
}