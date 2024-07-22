package com.limhm.maven.project.app.model.author.repository;

import com.limhm.maven.project.app.model.author.entity.Author;
import com.limhm.maven.project.app.model.authorCourse.dto.AuthorCourseDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

  @Query("SELECT new com.limhm.maven.project.app.model.authorCourse.dto.AuthorCourseDto(c.id, a"
      + ".name, c.name, c.description) from AUTHOR a, Course c, AUTHOR_COURSE ac where a.id = ac"
      + ".authorId and c.id=ac.courseId and ac.authorId=?1")
  Iterable<AuthorCourseDto> getAuthorCourseInfo(long authorId);
}
