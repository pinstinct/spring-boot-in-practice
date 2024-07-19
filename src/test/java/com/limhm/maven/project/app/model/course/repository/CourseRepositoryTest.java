package com.limhm.maven.project.app.model.course.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.limhm.maven.project.ApplicationTests;
import com.limhm.maven.project.app.model.course.entity.Course;
import com.limhm.maven.project.app.model.course.entity.DescriptionOnly;
import com.limhm.maven.project.app.model.course.entity.QCourse;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Condition;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class CourseRepositoryTest extends ApplicationTests {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private EntityManager entityManager;

  @BeforeEach
  public void setUp() {
    courseRepository.deleteAll();
  }

  @Test
  public void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse() {
    Course course = new Course("Rapid Spring Boot Application Development", "spring", 4,
        "Spring Boot gives all the power of the Spring Framework without all of the complexities");

    Course savedCourse = courseRepository.save(course);

    // isEqualTo 함수로 객체의 참조 비교
    // 그러나 JPA 에서 반환된 엔티티 객체(savedCourse)와 생성한 객체(course)는 서로 다른 객체이므로 참조가 다름
    // 따라서 이 비교는 실패한다. 대신, 엔티티 객체의 동등성 비교를 위해 equals, hasCode 메서드를 id 기준으로 오버라이드 함
    assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);
  }

  @Test
  public void givenUpdateCourseWhenLoadTheCourseThenExpectUpdatedCourse() {
    Course course = new Course("Rapid Spring Boot Application Development", "spring", 4,
        "Spring Boot gives all the power of the Spring Framework without all of the complexities");

    courseRepository.save(course);
    course.setRating(5);
    Course savedCourse = courseRepository.save(course);

    assertThat(courseRepository.findById(savedCourse.getId()).get().getRating()).isEqualTo(5);
  }

  @Test
  public void givenDeleteCourseWhenLoadTheCourseThenExpectNoCourse() {
    Course course = new Course("Rapid Spring Boot Application Development", "spring", 4,
        "Spring Boot gives all the power of the Spring Framework without all of the complexities");

    Course savedCourse = courseRepository.save(course);

    assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);
    courseRepository.delete(course);
    assertThat(courseRepository.findById(savedCourse.getId()).isPresent()).isFalse();
  }

  @Test
  public void givenCreateCoursesWhenLoadTheCourseThenExpectSameCourses() {
    courseRepository.saveAll(getCourses());
    assertThat(courseRepository.findAllByCategory("Spring")).hasSize(3);
    assertThat(courseRepository.existsByName("JavaScript for All")).isTrue();
    assertThat(courseRepository.existsByName("Mastering JavaScript")).isFalse();
    assertThat(courseRepository.countByCategory("Python")).isEqualTo(2);
    assertThat(courseRepository.findByNameStartsWith("Getting Started")).hasSize(3);
  }

  private List<Course> getCourses() {
    Course rapidSpringBootCourse = new Course("Rapid Spring Boot Application Development", "Spring",
        4, "Spring Boot gives all the power of the Spring Framework without all fo the complexity");

    Course springSecurityDslCourse = new Course("Getting Started with Spring Security DSL",
        "Spring", 5, "Learn Spring Security DsL in easy steps");

    Course springCloudKubernetesCourse = new Course("Getting Started with Spring Cloud Kubernetes",
        "Spring", 3, "Master Spring Boot application deployment with Kubernetes");

    Course rapidPythonCourse = new Course("Getting Started with Python", "Python", 5,
        "Learn Python concepts in easy steps");

    Course gameDevelopmentWithPython = new Course("Game Development with Python", "Python", 3,
        "Learn Python by developing 10 wonderful games");

    Course javascriptForAll = new Course("JavaScript for All", "JavaScript", 4,
        "Learn basic JavaScript syntax that can apply to anywhere");

    Course javascriptCompleteGuide = new Course("JavaScript Complete Guide", "JavaScript", 5,
        "Master JavaScript with Core Concepts and Web Development");

    return Arrays.asList(rapidSpringBootCourse, springSecurityDslCourse,
        springCloudKubernetesCourse, rapidPythonCourse, gameDevelopmentWithPython, javascriptForAll,
        javascriptCompleteGuide);
  }

  @Test
  public void givenDataAvailableWhenLoadFirstPageThenGetFiveRecords() {
    courseRepository.saveAll(getCourses());
    PageRequest pageable = PageRequest.of(0, 5);
    assertThat(courseRepository.findAll(pageable)).hasSize(5);
    assertThat(pageable.getPageNumber()).isEqualTo(0);

    PageRequest nextPageable = pageable.next();
    assertThat(courseRepository.findAll(nextPageable)).hasSize(2);
    assertThat(nextPageable.getPageNumber()).isEqualTo(1);
  }

  private List<Course> getCoursesForPaging() {
    Course rapidSpringBootCourse = new Course("Rapid Spring Boot Application Development", "Spring",
        4, "Spring Boot gives all the power of the Spring Framework without all fo the complexity");
    Course springSecurityDslCourse = new Course("Getting Started with Spring Security DSL",
        "Spring", 5, "Learn Spring Security DsL in easy steps");
    Course springCloudKubernetesCourse = new Course("Getting Started with Spring Cloud Kubernetes",
        "Spring", 3, "Master Spring Boot application deployment with Kubernetes");
    Course cloudNativeSpringBootApplicationDevelopmentCourse = new Course(
        "Cloud Native Spring Boot Application Development", "Spring", 4,
        "Cloud Native Spring Boot");
    Course gettingStartedWithSpringSecurityOauthCourse = new Course(
        "Getting Started with Spring Security Oauth", "Spring", 5,
        "Learn Spring Security Oauth in easy steps");
    Course springBootWithKotlinCourse = new Course("Spring Boot with Kotlin", "Spring", 3,
        "Master Spring Boot with React");
    Course masteringJsCourse = new Course("Mastering JS", "JavaScript", 4, "Mastering JS");
    Course springBootWithReactCourse = new Course("Spring Boot with React", "Spring", 5,
        "Spring boot with React");
    Course springBootMicroservicesCourse = new Course("Spring Boot Microservices", "Spring", 3,
        "Spring Boot Microservices");
    return List.of(rapidSpringBootCourse, springSecurityDslCourse, springCloudKubernetesCourse,
        cloudNativeSpringBootApplicationDevelopmentCourse,
        gettingStartedWithSpringSecurityOauthCourse, springBootWithKotlinCourse,
        springBootWithReactCourse, springBootMicroservicesCourse);
  }

  @Test
  public void givenDataAvailableWhenSortsFirstPageThenGetSortedData() {
    courseRepository.saveAll(getCoursesForPaging());

    Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("Name")));

    Condition<Course> sortedFirstCourseCondition = new Condition<>() {
      @Override
      public boolean matches(Course course) {
        return course.getName().equals("Cloud Native Spring Boot Application Development");
      }
    };

    assertThat(courseRepository.findAll(pageable)).first().has(sortedFirstCourseCondition);
  }

  @Test
  public void givenDataAvailableWhenApplyCustomSortThenGetSortedResult() {
    courseRepository.saveAll(getCoursesForPaging());
    Pageable customSortPageable = PageRequest.of(0, 5,
        Sort.by("Rating").descending().and(Sort.by("Name")));

    Condition<Course> customSortFirstCourseCondition = new Condition<>() {
      @Override
      public boolean matches(Course course) {
        return course.getName().equals("Getting Started with Spring Security DSL");
      }
    };
    assertThat(courseRepository.findAll(customSortPageable)).first()
        .has(customSortFirstCourseCondition);
  }

  @Test
  public void givenCoursesCreatedWhenLoadCoursesBySpringCategoryThenExpectThreeCourses() {
    courseRepository.saveAll(getCourses());
    assertThat(courseRepository.findAllByCategoryAndRating("Spring", 4)).hasSize(1);
  }

  @Test
  public void givenCoursesCreatedWhenLoadCoursesWithQueryThenExpectCourseDetails() {
    courseRepository.saveAll(getCourses());
    assertThat(courseRepository.findAllByRating(3)).hasSize(2);
    assertThat(courseRepository.findAllByCategoryAndRatingGreaterThan("Spring", 3)).hasSize(2);

    courseRepository.updateCourseRatingByName(4, "Getting Started with Spring Cloud Kubernetes");
    assertThat(courseRepository.findAllByCategoryAndRatingGreaterThan("Spring", 3)).hasSize(3);
  }

  @Test
  public void givenCoursesCreatedWhenLoadCoursesWithQueryThenExpectCorrectCourseDetails() {
    courseRepository.saveAll(getCourses());

    /*
     * EntityManger 인스턴스는 여러 엔티티 인스턴스로 구성되는 퍼시스턴스(persistence) 컨텍스트와 관련되는 인스턴스다.
     * 엔티티 인스턴스의 라이프사이클은 퍼시스턴스 컨텍스에 의해 관리된다.
     * CriteriaBuilder 인스턴스를 사용하면 Criteria API 기반 쿼리, 조회, 정렬 등을 사용할 수 있다.
     * */
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    /*
     * CriteriaBuilder 는 CriteriaQuery 를 정의하는데 사용되고, CriteriaQuery 는 Course 클래스를 파라미터 타입으로 전달받는다.
     * 비지니스 로직에 맞게 CriteriaQuery, CriteriaUpdate, CriteriaDelete 인스턴스를 생성한다.
     * */
    CriteriaQuery<Course> courseCriteriaQuery = criteriaBuilder.createQuery(Course.class);

    // Root 객체는 Course 엔티티를 참조하게 된다. 쿼리에 사용되는 표현식을 만드는 데 사용됨
    Root<Course> courseRoot = courseCriteriaQuery.from(Course.class);
    Predicate courseCategoryPredicate = criteriaBuilder.equal(courseRoot.get("category"), "Spring");
    courseCriteriaQuery.where(courseCategoryPredicate);
    TypedQuery<Course> query = entityManager.createQuery(courseCriteriaQuery);

    assertThat(query.getResultList().size()).isEqualTo(3);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Test
  public void queryDslTest() {
    courseRepository.saveAll(getCourses());

    QCourse course = QCourse.course;
    JPAQuery query1 = new JPAQuery(entityManager);  // JPAQuery(QueryDSL 에서 JPA 를 사용할 수 있게 해주는
    // JPQLQuery 인스턴스의 기본 구현체) 인스턴스 생성
    query1.from(course).where(course.category.eq("Spring"));
    assertThat(query1.fetch().size()).isEqualTo(3);

    JPAQuery query2 = new JPAQuery(entityManager);
    query2.from(course).where(course.category.eq("Spring").and(course.rating.gt(3)));
    assertThat(query2.fetch().size()).isEqualTo(2);

    // QuerydslPredicateExecutor 인터페이스를 상속받은 findAll 메서드의 인자로 OrderSpecifier 인스턴스를 전달
    OrderSpecifier<Integer> descOrderSpecifier = course.rating.desc();
    assertThat(Lists.newArrayList(courseRepository.findAll(descOrderSpecifier)).get(0)
        .getName()).isEqualTo("Getting Started with Spring Security DSL");
  }

  @Test
  public void givenACourseAvailableWhenGenCourseByNameThenGetCourseDescription() {
    courseRepository.saveAll(getCourses());
    Iterable<DescriptionOnly> result = courseRepository.getCourseByName("Rapid Spring Boot "
        + "Application Development");
    assertThat(result).extracting("description").contains("Spring Boot gives all the power of the"
        + " Spring Framework without all fo the complexity");
  }
}
