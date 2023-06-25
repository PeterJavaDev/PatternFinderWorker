package org.patternfinder.service;

import org.patternfinder.model.SearchTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FindPatternTaskScheduler {

    private FinderProcessor finderProcessor;
    private FinderService finderService;
    private Integer progressUpdateParts;

    @Autowired
    public FindPatternTaskScheduler(
            FinderProcessor finderProcessor,
            FinderService finderService,
            @Value("${patternfinder.progressUpdateParts}") Integer progressUpdateParts) {
        this.finderProcessor = finderProcessor;
        this.finderService = finderService;
        this.progressUpdateParts = progressUpdateParts;
    }

    @Scheduled(fixedDelay = 1000)
    public void checkForNewTests() {

        SearchTask searchTask = finderService.getSearchTask();
        if (searchTask != null) {
            finderProcessor.findPattern(searchTask, progressUpdateParts);
        }

    }

}
