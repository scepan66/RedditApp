package com.example.redditwannabe.controllers;

import com.example.redditwannabe.dtos.PostDTO;
import com.example.redditwannabe.dtos.UserDTO;
import com.example.redditwannabe.models.Post;
import com.example.redditwannabe.models.User;
import com.example.redditwannabe.services.PostService;
import com.example.redditwannabe.services.UserService;
import com.example.redditwannabe.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class MainController {
  private UserService userService;
  private PostService postService;
  private VoteService voteService;

  @Autowired
  public MainController(UserService userService, PostService postService, VoteService voteService) {
    this.userService = userService;
    this.postService = postService;
    this.voteService = voteService;
  }

  @GetMapping("/")
  public String homepage(Model model, @RequestParam(required = false) String user) {
    model.addAttribute("posts", postService.getAllPosts());
    model.addAttribute("username", user);
    return "index";
  }

  @GetMapping("/register")
  public String registerPage() {
    return "registerPage";
  }

  @PostMapping("/register")
  public String register(@ModelAttribute UserDTO userDTO, Model model) {
    if (userService.existsByEmail(userDTO.getEmail())) {
      model.addAttribute("invalidMail", true);
      return "invalidInput";
    } else if (userService.existsByUsername(userDTO.getUsername())) {
      model.addAttribute("invalidUsername", true);
      return "invalidInput";
    } else {
      userService.createNewUser(userDTO);
      return "redirect:/?user=" + userDTO.getUsername();
    }
  }

  @PostMapping("/login")
  public String userLogin(@ModelAttribute User user) {
    User loggedInUser = userService.getUserFromLogin(user);
    if (loggedInUser == null) {
      return "registerPage";
    }
    return "redirect:/?user=" + loggedInUser.getUsername();
  }

  @GetMapping("/submit")
  public String submitNewPostPage(@RequestParam(required = false) String user, Model model) {
    if (user.equals("null")) {
      return "needToLogin";
    }
    model.addAttribute("user", user);
    return "submitPage";
  }

  @PostMapping("/addPost")
  public String addPost(@ModelAttribute PostDTO postDTO, @RequestParam String user) {
    Post newPost = postService.createNewPost(postService.PostDTOConverter(postDTO, user));
    postService.savePost(newPost);
    return "redirect:/?user=" + user;
  }

  @GetMapping("/{id}/upVote")
  public String upVote(@PathVariable Long id, @RequestParam String user) {
    if (user.equals("null")) {
      return "needToLogin";
    }
    voteService.votingUp(user, id);
    return "redirect:/?user=" + user;
  }

  @GetMapping("/{id}/downVote")
  public String downVote(@PathVariable Long id, @RequestParam String user) {
    if (user.equals("null")) {
      return "needToLogin";
    }
    voteService.votingDown(user, id);
    return "redirect:/?user=" + user;
  }

  @GetMapping("/{id}/edit")
  public String editPostPage(@PathVariable Long id, @RequestParam String user, Model model) {
    if (user.equals("null")) {
      return "needToLogin";
    }
    model.addAttribute("post", postService.getPostById(id));
    model.addAttribute("username", user);
    return "editPost";
  }

  @PostMapping("/edit")
  public String editPost(@RequestParam String user, @ModelAttribute PostDTO postDTO) {
    postService.editPost(postDTO);
    return "redirect:/?user=" + user;
  }
}
