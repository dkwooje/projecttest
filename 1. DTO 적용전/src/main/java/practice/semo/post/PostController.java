package practice.semo.post;


import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.semo.user.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;


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
        postService.postAdd( title, content);
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
        postService.postUpdate(id, title, content);
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
    String delete2(@PathVariable Long id){
        postRepository.deleteById(id);
        return "postMain.html";
    }


}
