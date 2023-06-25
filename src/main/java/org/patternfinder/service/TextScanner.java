package org.patternfinder.service;

import org.patternfinder.dto.SearchResult;
import org.patternfinder.model.SearchTask;

public interface TextScanner {

    SearchResult scan(SearchTask searchTask, int progressUpdateParts);

}
