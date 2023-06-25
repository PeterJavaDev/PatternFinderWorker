package org.patternfinder.service;

import org.patternfinder.dto.SearchResult;
import org.patternfinder.model.SearchTask;
import org.springframework.stereotype.Service;

@Service
public class TextScannerImpl implements TextScanner {

    private FinderService finderService;

    public TextScannerImpl(FinderService finderService) {
        this.finderService = finderService;
    }

    public SearchResult scan(SearchTask searchTask, int progressUpdateParts) {
        String inputText = searchTask.getInputText();
        String pattern = searchTask.getPattern();
        int progress = 0;


        int maxMatchLength = 0;
        int typos = pattern.length();
        int position = -1;

        int textLength = inputText.length();
        int partLength = textLength / progressUpdateParts;
        int percentageIncr = 100 / progressUpdateParts;

        for (int inputTextIndex = 0; inputTextIndex < textLength; inputTextIndex++) {
            for (int patternLength = pattern.length(); patternLength > maxMatchLength; patternLength--) {
                if (inputTextIndex + patternLength <= textLength) {
                    String part = inputText.substring(inputTextIndex, inputTextIndex + patternLength);
                    if (pattern.startsWith(part)) {
                        maxMatchLength = patternLength;
                        typos = pattern.length() - patternLength;
                        position = inputTextIndex;
                    }
                }
            }

            if (inputTextIndex > 0 && inputTextIndex % partLength == 0) {

                /*
                For making scanning artificially longer
                 */
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                progress += percentageIncr;
                finderService.updateProgress(searchTask, progress);
            }
        }
        return new SearchResult(searchTask, position, typos);
    }

}
