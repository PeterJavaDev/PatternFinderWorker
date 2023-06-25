package org.patternfinder.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.patternfinder.dto.SearchResult;
import org.patternfinder.model.SearchTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class FinderProcessorImpl implements FinderProcessor {

    private static final Logger log = LogManager.getLogger(FinderProcessorImpl.class);

    private FinderService finderService;
    private TextScanner textScanner;

    @Autowired
    public FinderProcessorImpl(FinderService finderService, TextScanner textScanner) {
        this.finderService = finderService;
        this.textScanner = textScanner;
    }

    @Async("findTaskExecutor")
    public void findPattern(SearchTask searchTask, int progressUpdateParts) {
        log.debug("FinderProcessorImpl.findPattern started " + Thread.currentThread().getName());

        SearchResult searchResult = textScanner.scan(searchTask, progressUpdateParts);
        finderService.completeSearchTask(searchResult.searchTask(), searchResult.position(), searchResult.typos());

        log.debug("FinderProcessorImpl.findPattern ended " + Thread.currentThread().getName());
    }

}
