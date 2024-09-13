package practice.semo.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentTable,Long> {


    Page<CommentTable> findByPost_Id(Long postId, Pageable pageable);

}
