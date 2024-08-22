package com.tsinsi.user.adapter.out.mapper;

import com.tsinsi.user.adapter.out.mapper.reader.UserReaderMapper;
import com.tsinsi.user.adapter.out.mapper.writer.UserWriterMapper;
import com.tsinsi.user.application.port.out.UserRepository;
import com.tsinsi.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserWriterMapper userWriterMapper;
    private final UserReaderMapper userReaderMapper;

    @Autowired
    public UserRepositoryImpl(UserWriterMapper userWriterMapper, UserReaderMapper userReaderMapper) {
        this.userWriterMapper = userWriterMapper;
        this.userReaderMapper = userReaderMapper;
    }

    @Override
    public List<User> findBefore(long id) {
        return userReaderMapper.findBefore(id);
    }

    @Override
    public List<User> findAfter(long id) {
        return userReaderMapper.findAfter(id);
    }

    @Override
    @Caching(
            cacheable = {@Cacheable(value = "user", key = "#username", unless = "#result == null")},
            put = {@CachePut(value = "user", key = "#result.id", unless = "#result == null")}
    )
    public User findByUsername(String username) {
        return userReaderMapper.findByUsername(username);
    }
}
