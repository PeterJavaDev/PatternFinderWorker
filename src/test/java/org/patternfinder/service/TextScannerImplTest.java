package org.patternfinder.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.patternfinder.dto.SearchResult;
import org.patternfinder.model.SearchTask;
import org.patternfinder.model.SearchTaskStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TextScannerImplTest {

    private TextScannerImpl textScanner;

    @Mock
    private FinderService finderService;

    private SearchTask searchTask;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        textScanner = new TextScannerImpl(finderService);

        String inputText =
                "Gallia est omnis divisa in partes tres, quarum unam incolunt Belgae, aliam Aquitani, tertiam qui ipsorum lingua Celtae, nostra Galli appellantur.";
        searchTask = new SearchTask();
        searchTask.setInputText(inputText);

        searchTask.setStatus(SearchTaskStatus.READY);
    }

    @Test
    public void testScanNoTypos() {
        String pattern = "Celtae";
        searchTask.setPattern(pattern);
        int progressUpdateParts = 5;

        SearchResult searchResult = textScanner.scan(searchTask, progressUpdateParts);

        assertNotNull(searchResult);
        assertEquals(0, searchResult.typos());
        assertEquals(112, searchResult.position());
    }

    @Test
    public void testScanTypos() {
        String pattern = "CeltaeXX";
        searchTask.setPattern(pattern);
        int progressUpdateParts = 5;

        SearchResult searchResult = textScanner.scan(searchTask, progressUpdateParts);

        assertNotNull(searchResult);
        assertEquals(2, searchResult.typos());
        assertEquals(112, searchResult.position());
    }

    @Test
    public void testScanNotFound() {
        String pattern = "XX";
        searchTask.setPattern(pattern);
        int progressUpdateParts = 5;

        SearchResult searchResult = textScanner.scan(searchTask, progressUpdateParts);

        assertNotNull(searchResult);
        assertEquals(-1, searchResult.position());
    }
}