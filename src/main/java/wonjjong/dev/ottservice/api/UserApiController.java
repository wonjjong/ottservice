package wonjjong.dev.ottservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import wonjjong.dev.ottservice.domain.user.User;
import wonjjong.dev.ottservice.domain.user.UserRepository;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserRepository userRepository;

    @GetMapping("/member/{id}")
    public User getMemberById(@PathVariable Long id) {
        Optional<User> memberInfo = userRepository.findById(id);
        return memberInfo.get();
    }

/*
*
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (long id) {
        System.out.print("아이디는" + id);
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
* */
}
