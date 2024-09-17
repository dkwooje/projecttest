package practice.semo.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostTable,Long> {


    List<PostTable> findByTitleContaining(String title);

  //  @Query("SELECT p FROM PostTable p WHERE p.title LIKE %:title%")
    // List<PostTable> findByTitleContaining(@Param("title") String title);

}
