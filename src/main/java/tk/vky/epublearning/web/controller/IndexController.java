/**
 * 
 */
package tk.vky.epublearning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author Vivek Gajbhiye - Cropdata
 *
 * 
 */
@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String rootRedirect() {
		return "redirect:/welcome";
	}

	@RequestMapping("/welcome")
	public String welcome(@RequestParam(value = "error", required = false) boolean error, ModelMap model) {
		return "home/login";
	}
	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) boolean error, ModelMap model) {
		return "home/login";
	}

	@RequestMapping("/dashboard")
	public String adminDashBoard() {
		return "admin/dashboard";
	}

	@RequestMapping("/userlist")
	public String userList() {
		return "admin/userList";
	}

	@RequestMapping("/accessDenied")
	public String accessDenied() {
		return "errors/accessDenied";
	}

	@RequestMapping("/af")
	public String af() {
		return "home/login1";
	}

}
