package practice.semo.post;


import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.semo.comment.CommentRepository;
import practice.semo.user.UserService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    @GetMapping("/post")
    String post2(Model model){
        List<PostTable> result = postRepository.findAll();
        model.addAttribute("data", result);
        return "postMain.html";
    }

    @GetMapping("/post/write")
    String write1(){
        return "postWrite.html";
    }
    @PostMapping("/post/write")
    String write2(@RequestParam String title,
                  @RequestParam String content
    ){
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(title);
        postDTO.setContent(content);
        postService.postAdd(postDTO);
        return "postMain.html";
    }

    @GetMapping("/post/update/{id}")
    String update(Model model, @PathVariable Long id){
        Optional<PostTable> result = postRepository.findById(id);
        if(result.isPresent()){
         model.addAttribute("data", result.get());
         return "postUpdate.html";
        }
        return "postMain.html";
    }

    @PostMapping("/post/update/{id}")
    String update(@PathVariable Long id,
                  @RequestParam String title,
                  @RequestParam String content){

        PostDTO postDTO = new PostDTO();
        postDTO.setTitle(title);
        postDTO.setContent(content);
        postService.postUpdate(id, postDTO);

        return "postMain.html";
    }

    @GetMapping("/post/delete/{id}")
    String delete1(Model model,@PathVariable Long id){
        Optional<PostTable> result = postRepository.findById(id);
        if(result.isPresent()){
            model.addAttribute("data",result.get());
            return "postDelete.html";
        }else
        return "postMain.html";
    }

    @PostMapping("/post/delete/{id}")
    String delete2(@PathVariable("id") Long id ){
        postRepository.deleteById(id);
        return "postMain.html";
    }

    @PostMapping("/post/search")
    public String postSearch(@RequestParam String searchText) {
        try {
            // 한글이나 특수 문자를 URL 인코딩
            String encodedSearchText = URLEncoder.encode(searchText, StandardCharsets.UTF_8.toString());
            return "redirect:/post/search?title=" + encodedSearchText;
        } catch (UnsupportedEncodingException e) {
            // 인코딩 오류가 발생할 경우
            e.printStackTrace();
            return "errorPage.html"; // 오류 페이지로 리다이렉트
        }
    }

    @GetMapping("/post/search")
    public String searchResult(Model model, @RequestParam String title) {
        List<PostTable> result = postRepository.findByTitleContaining(title);
        model.addAttribute("data", result);
        return "postMain.html";
    }




}
