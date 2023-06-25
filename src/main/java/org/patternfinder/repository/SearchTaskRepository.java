package org.patternfinder.repository;

import org.patternfinder.model.SearchTask;
import org.patternfinder.model.SearchTaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchTaskRepository extends JpaRepository<SearchTask, Long> {

    SearchTask findFirstByStatusOrderByCreatedTsAsc(SearchTaskStatus status);
    List<SearchTask> findByStatusOrderByCreatedTsAsc(SearchTaskStatus status);

}
