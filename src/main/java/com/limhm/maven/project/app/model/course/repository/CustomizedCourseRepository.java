package com.limhm.maven.project.app.model.course.repository;

import com.limhm.maven.project.app.model.course.entity.Course;
import com.limhm.maven.project.global.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedCourseRepository extends BaseRepository<Course, Long> {

}
