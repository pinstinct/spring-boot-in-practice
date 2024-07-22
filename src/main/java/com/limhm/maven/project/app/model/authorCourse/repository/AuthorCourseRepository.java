package com.limhm.maven.project.app.model.authorCourse.repository;

import com.limhm.maven.project.app.model.authorCourse.entity.AuthorCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorCourseRepository extends CrudRepository<AuthorCourse, Long> {

}
