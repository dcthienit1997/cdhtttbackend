package nlu.edu.cdhttt.demo.controller;

import nlu.edu.cdhttt.demo.model.Post;
import nlu.edu.cdhttt.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    // Write a new post
    @RequestMapping(value = "/write")
    public String writePost(Model model) {
        model.addAttribute("post", new Post());
        return "write";
    }

    // Save a new post
    @PostMapping(value = "/save")
    public String savePost(@ModelAttribute("post") Post post, Model model) {
        postService.createPost(post);
        System.out.println(post.toString());
        model.addAttribute("listPost", postService.getPosts());
        model.addAttribute("allViews", postService.allViews());
        model.addAttribute("allClaps", postService.allClaps());
        return "redirect:/dashboard";
    }

    // Get all posts
    @GetMapping(value = "/dashboard")
    public String getPosts(Model model) {
        model.addAttribute("listPost", postService.getPosts());
        model.addAttribute("allViews", postService.allViews());
        model.addAttribute("allClaps", postService.allClaps());
        return "dashboard";
    }

    // Edit a post
    @RequestMapping(value = "/edit/{id}")
    public String updatePost(@PathVariable String id, Model model) {
        Optional<Post> post = postService.getPost(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
        }
        return "edit";
    }

    // Re-save a post
    @PostMapping(value = "/resave")
    public String resavePost(@ModelAttribute("post") Post post, Model model) {
        postService.savePost(post);
        model.addAttribute("listPost", postService.getPosts());
        model.addAttribute("allViews", postService.allViews());
        model.addAttribute("allClaps", postService.allClaps());
        return "redirect:/dashboard";
    }

    @GetMapping(value = "/resave")
    public String resavePostGET(Model model) {
        model.addAttribute("listPost", postService.getPosts());
        model.addAttribute("allViews", postService.allViews());
        model.addAttribute("allClaps", postService.allClaps());
        return "redirect:/dashboard";
    }

    // Re-save as the draft post
    @PostMapping(value = "/draft")
    public String resaveDraftPost(@ModelAttribute("post") Post post, Model model) {
        postService.editStatusPost(post);
        model.addAttribute("listPost", postService.getPosts());
        model.addAttribute("allViews", postService.allViews());
        model.addAttribute("allClaps", postService.allClaps());
        return "redirect:/dashboard";
    }

    @GetMapping(value = "/draft")
    public String resaveDraftPostGET(Model model) {
        model.addAttribute("listPost", postService.getPosts());
        model.addAttribute("allViews", postService.allViews());
        model.addAttribute("allClaps", postService.allClaps());
        return "redirect:/dashboard";
    }

    // Delete a post
    @RequestMapping(value = "/delete/{id}")
    public String deletePost(@PathVariable String id, Model model) {
        postService.deletePost(id);
        model.addAttribute("listPost", postService.getPosts());
        model.addAttribute("allViews", postService.allViews());
        model.addAttribute("allClaps", postService.allClaps());
        return "redirect:/dashboard";
    }

    // Track view count
    @GetMapping(value = "/post/{id}")
    @ResponseBody
    public Optional<Post> getAPostFlutter(@PathVariable String id) {
        postService.increView(id);
        return postService.getPost(id);
    }

    @GetMapping(value = "/posts")
    @ResponseBody
    public List<Post> getPostsFlutter() {
        return postService.getPostsFlutter();
    }

}
