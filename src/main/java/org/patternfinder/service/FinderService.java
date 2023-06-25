package org.patternfinder.service;

import org.patternfinder.model.SearchTask;

public interface FinderService {

    SearchTask getSearchTask();
    void updateProgress(SearchTask searchTask, int progress);
    void completeSearchTask(SearchTask searchTask,int position, int typos);

}
