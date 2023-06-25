package org.patternfinder.service;

import org.patternfinder.model.SearchTask;

public interface FinderProcessor {

    void findPattern(SearchTask searchTask, int progressUpdateParts);

}
