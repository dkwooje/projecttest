package practice.semo.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostTable,Long> {

List<PostTable> findByTitle(String title);

}
