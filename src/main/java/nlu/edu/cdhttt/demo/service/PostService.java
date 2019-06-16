package nlu.edu.cdhttt.demo.service;

import nlu.edu.cdhttt.demo.model.Post;
import nlu.edu.cdhttt.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public Optional<Post> getPost(String id) {
        return postRepository.findById(id);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPosts(Date date) {
        return postRepository.findAllByDate(date);
    }

    public List<Post> getPosts(int clap) {
        return postRepository.findAllByClapGreaterThan(clap);
    }

    public List<Post> getPosts(String subtitle) {
        return postRepository.findAllByTitleContains(subtitle);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Post createPost(Post post) {
        return postRepository.insert(post);
    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
        return;
    }

    public Post editStatusPost(Post post) {
        post.setRelease(!post.isRelease());
        return postRepository.save(post);
    }

    public Post increView(String id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = optionalPost.get();
        post.setView(post.getView() + 1);
        return postRepository.save(post);
    }

    public Post increClap(String id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = optionalPost.get();
        post.setView(post.getClap() + 1);
        return postRepository.save(post);
    }

    public int allViews() {
        List<Post> posts = postRepository.findAll();
        int count = 0;
        for (Post post : posts) {
            count += post.getView();
        }
        return count;
    }

    public int allClaps() {
        List<Post> posts = postRepository.findAll();
        int count = 0;
        for (Post post : posts) {
            count += post.getClap();
        }
        return count;
    }

    // Flutter
    public List<Post> getPostsFlutter() {
        return postRepository.findAllByReleaseTrue();
    }

}
