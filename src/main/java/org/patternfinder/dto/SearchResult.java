package org.patternfinder.dto;

import org.patternfinder.model.SearchTask;

public record SearchResult(SearchTask searchTask, int position, int typos) {
}
