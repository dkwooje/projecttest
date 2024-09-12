package practice.semo.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentTable2,Long> {


    Page<CommentTable2> findByPost_Id(Long postId, Pageable pageable);

}
