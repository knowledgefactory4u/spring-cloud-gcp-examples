package com.knf.dev.demo.repository;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import com.knf.dev.demo.document.User;
import reactor.core.publisher.Flux;

public interface UserRepository extends FirestoreReactiveRepository<User> {

    Flux<User> findByCountry(String country);

}
