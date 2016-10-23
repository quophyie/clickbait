package com.quantal.clickbait.repositories.implementations;

import com.quantal.clickbait.entities.User;
import com.quantal.clickbait.repositories.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dman on 22/10/2016.
 */
@Repository
public class UserRepositoryImpl extends AbstractBaseRepositoryImpl<User, Long> implements UserRepository<User, Long> {
}
