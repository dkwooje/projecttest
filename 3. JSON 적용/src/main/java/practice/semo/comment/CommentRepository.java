package practice.semo.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentTable,Long> {

    //List<CommentTable> findByPost_Id(Long postId);
    Page<CommentTable> findByPost_Id(Long postId, Pageable pageable);
    void deleteByPostId(Long id);
}
