package practice.semo.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;


    // 댓글 작성 처리
    @PostMapping("/{post_id}")
    public Map<String, Object> commentWrite2(@PathVariable Long post_id, @RequestBody List<CommentDTO> comments) {
        for (CommentDTO commentDTO : comments) {
            commentService.commentWrite(post_id, commentDTO.getContent());
        }

        // JSON 응답 구성
        Map<String, Object> response = new HashMap<>();
        response.put("message", comments.size() + "개의 댓글이 성공적으로 작성되었습니다.");
        return response;  // JSON으로 응답
    }

    // 자동 댓글 생성 후 JSON 응답
    @PostMapping("/auto/{post_id}")
    public Map<String, Object> autoCommentWrite(@PathVariable Long post_id) {
        int commentCount = commentService.autoWrite(post_id);

        // JSON 응답 구성
        Map<String, Object> response = new HashMap<>();
        response.put("message", "5000개의 댓글이 성공적으로 작성되었습니다.");
        response.put("totalCommentsWritten", commentCount);  // 작성된 댓글 수

        return response;  // JSON으로 응답
    }


    // 댓글 리스트를 JSON으로 반환
    @GetMapping("/{post_id}/list/{abc}")
    public Map<String, Object> commentList(@PathVariable Long post_id, @PathVariable Integer abc, @PageableDefault(size = 5) Pageable pageable) {
        // abc를 페이지 번호로 설정 (abc는 1부터 시작한다고 가정)
        Pageable pageRequest = PageRequest.of(abc - 1, pageable.getPageSize());
        // 해당 post_id의 댓글을 페이지네이션으로 가져오기
        Page<CommentTable2> comments = commentRepository.findByPost_Id(post_id, pageRequest);
        // 페이지네이션 관련 정보 계산
        //  int totalPages = comments.getTotalPages();
        //   int currentPage = comments.getNumber() + 1;  // 0부터 시작하므로 +1
        //    int startPage = Math.max(1, currentPage - 2);
        //   int endPage = Math.min(startPage + 4, totalPages);
        // JSON 응답 구성
        Map<String, Object> response = new HashMap<>();
        response.put("comments", comments.getContent());  // 댓글 리스트
      //  response.put("currentPage", currentPage);         // 현재 페이지 번호
      //  response.put("startPage", startPage);             // 시작 페이지 번호
      //  response.put("endPage", endPage);                 // 끝 페이지 번호
      //  response.put("totalPages", totalPages);           // 전체 페이지 수

        return response;  // JSON으로 응답
    }
}




