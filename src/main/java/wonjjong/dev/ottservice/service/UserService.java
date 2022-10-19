package wonjjong.dev.ottservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wonjjong.dev.ottservice.domain.user.User;
import wonjjong.dev.ottservice.domain.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> userList() {
        //아마 한명도 없으면 빈 리스트 반환?
        return userRepository.findAll();
    }
}
