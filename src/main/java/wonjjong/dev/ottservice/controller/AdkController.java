package wonjjong.dev.ottservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wonjjong.dev.ottservice.domain.user.User;
import wonjjong.dev.ottservice.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adk")
public class AdkController {

	private final UserService userService;

	@GetMapping({"/index", "", "/"})
	@Operation(summary = "Admin index page")
	public String index() {
		return "admin/index";
	}

	@GetMapping("/tables")
	public String tables() {
		return "admin/tables";
	}

	@GetMapping("/layout-static")
	public String layoutStatic() {
		return "admin/layout-static";
	}

	@GetMapping("/layout-sidenav-light")
	public String layoutSideNavLight() {
		return "admin/layout-static";
	}

	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}

	@GetMapping("/register")
	public String register() {
		return "admin/register";
	}

	@GetMapping("/password")
	public String password() {
		return "admin/password";
	}

	@GetMapping("/error401")
	public String error401() {
		return "admin/error/401";
	}
	@GetMapping("error404")
	public String error404() {
		return "admin/error/404";
	}
	@GetMapping("/error500")
	public String error500() {
		return "admin/error/500";
	}

	@GetMapping("/charts")
	public String charts() {
		return "admin/charts";
	}

	@GetMapping("/userList")
	public String userList(Model model, Pageable pageable) {
		model.addAttribute("users", userService.userList(pageable));
		return "admin/userList";
	}

	@GetMapping("/userList-api")
	@ResponseBody
	public List<User> userListApi(Pageable pageable) {
		List<User> users = userService.userList(pageable);
		return userService.userList(pageable);
	}
}
