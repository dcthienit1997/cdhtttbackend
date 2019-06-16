package nlu.edu.cdhttt.demo.repository;

import nlu.edu.cdhttt.demo.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
