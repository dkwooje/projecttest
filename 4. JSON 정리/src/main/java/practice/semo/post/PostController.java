package practice.semo.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.semo.comment.CommentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 모든 게시글을 JSON으로 반환
    @GetMapping("")
    public List<PostTable2> getPosts() {
        return postRepository.findAll();
    }


    // 게시글 작성 요청 처리
    @PostMapping("/write")
    public void writePost(@RequestBody List<PostDTO> posts) {
        for (PostDTO postDTO : posts) {
            postService.postAdd(postDTO.getTitle(), postDTO.getContent());
        }
    }

    // 게시글 업데이트 페이지를 JSON으로 처리
    @GetMapping("/update/{id}")
    public Optional<PostTable2> updatePage(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    // 게시글 업데이트 요청 처리
    @PostMapping("/update")
    public void updatePost(@RequestBody PostDTO postDTO) {
        postService.postUpdate(postDTO.getId(), postDTO.getTitle(), postDTO.getContent());
    }

    // 게시글 삭제 페이지를 JSON으로 처리
    @GetMapping("/delete/{id}")
    public Optional<PostTable2> deletePage(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    // 게시글 삭제 요청 처리
    @PostMapping("/delete/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
    }
}