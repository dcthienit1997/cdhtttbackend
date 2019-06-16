package nlu.edu.cdhttt.demo.repository;

import nlu.edu.cdhttt.demo.model.Role;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, ObjectId> {
    Role findByRole(String role);
}
