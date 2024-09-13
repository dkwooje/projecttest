package practice.semo.post;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
@RequiredArgsConstructor
public class PostService {


    private final PostRepository postRepository;


    public void postAdd(String title, String content) {
        PostTable2 table = new PostTable2();
        table.setTitle(title);
        table.setContent(content);
        postRepository.save(table);
    }

    public void postUpdate(Long id ,String title, String content) {
        PostTable2 table = postRepository.findById(id)
                        .orElseThrow(()->new ResolutionException("게시글 없음"));
        table.setTitle(title);
        table.setContent(content);
        postRepository.save(table);
    }

}
