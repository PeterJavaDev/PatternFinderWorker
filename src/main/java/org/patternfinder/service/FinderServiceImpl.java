package org.patternfinder.service;

import org.patternfinder.model.SearchTask;
import org.patternfinder.model.SearchTaskStatus;
import org.patternfinder.repository.SearchTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FinderServiceImpl implements FinderService {

    private SearchTaskRepository searchTaskRepository;
    private String workerName;

    @Autowired
    public FinderServiceImpl(
            SearchTaskRepository searchTaskRepository,
            @Value("${patternfinder.workerName}") String workerName) {
        this.searchTaskRepository = searchTaskRepository;
        this.workerName = workerName;
    }

    @Transactional
    public SearchTask getSearchTask() {
        SearchTask searchTask = searchTaskRepository.findFirstByStatusOrderByCreatedTsAsc(SearchTaskStatus.READY);

        if (searchTask != null) {
            searchTask.setStatus(SearchTaskStatus.IN_PROGRESS);
            searchTask.setWorker(workerName);
            searchTaskRepository.save(searchTask);
        }
        return searchTask;
    }

    public void updateProgress(SearchTask searchTask, int progress) {
        searchTask.setProgress(progress);
        searchTaskRepository.save(searchTask);
    }

    public void completeSearchTask(SearchTask searchTask, int position, int typos) {
        searchTask.setStatus(SearchTaskStatus.DONE);
        searchTask.setPosition(position);
        searchTask.setTypos(typos);
        searchTaskRepository.save(searchTask);
    }

}
