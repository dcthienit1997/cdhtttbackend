package nlu.edu.cdhttt.demo.repository;

import nlu.edu.cdhttt.demo.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findAllByReleaseTrue();

    List<Post> findAll();

    List<Post> findAllByDate(Date date);

    List<Post> findAllByClapGreaterThan(int clap);

    List<Post> findAllByTitleContains(String subtitle);

}
