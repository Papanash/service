package ro.unibuc.hello.data;

import org.springframework.data.mongodb.repository.MongoRepository;
public interface SingerRepository extends MongoRepository<SingerEntity, String> {

}
