package practice.semo.post;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
@RequiredArgsConstructor
public class PostService {


    private final PostRepository postRepository;


    public void postAdd(PostDTO postDTO) {
        PostTable table = new PostTable();
        table.setTitle(postDTO.getTitle());
        table.setContent(postDTO.getContent());
        postRepository.save(table);
    }

    public void postUpdate(Long id ,PostDTO postDTO) {
        PostTable table = postRepository.findById(id)
                        .orElseThrow(()->new ResolutionException("게시글 없음"));
        table.setTitle(postDTO.getTitle());
        table.setContent(postDTO.getContent());
        postRepository.save(table);
    }

}
