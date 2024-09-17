package practice.semo.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @GetMapping("/post/comment/{post_id}")
    String commentWrite1(@PathVariable Long post_id) {
        return "commentWrite.html";
    }

    @PostMapping("/post/comment/{post_id}")
    String commentWrite2(@PathVariable Long post_id,
                         @RequestParam String content) {

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent(content);
        commentService.commentWrite(post_id,commentDTO);
        return "redirect:/post/comment/{post_id}/list";
    }


    @PostMapping("/post/comment/auto/{post_id}")
    String AutoCommentWrite(@PathVariable Long post_id,  String content){
        commentService.AutoWrite(post_id, content);
         return    "commentWrite.html";
    }


    @GetMapping("/post/comment/{post_id}/list")
    public String commentList(Model model, @PathVariable Long post_id, @PageableDefault(size = 5) Pageable pageable) {
        Page<CommentTable> comments = commentRepository.findByPost_Id(post_id, pageable);
        int totalPages = comments.getTotalPages();
        int currentPage = comments.getNumber() + 1;  // Thymeleaf에서 사용할 페이지 번호 (0부터 시작하므로 +1)
        int startPage = Math.max(1, currentPage - 2);  // 현재 페이지 기준으로 앞뒤로 페이지를 보여줌
        int endPage = Math.min(startPage + 4, totalPages);  // 최대 5개 페이지 번호만 표시

        model.addAttribute("data", comments);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPages", totalPages);

        return "commentList.html";
    }
}



