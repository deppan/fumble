package com.tsinsi.auth.adapter.out.mapper;

import com.tsinsi.auth.adapter.out.mapper.reader.UserReaderMapper;
import com.tsinsi.auth.adapter.out.mapper.writer.UserWriterMapper;
import com.tsinsi.auth.application.port.out.UserRepository;
import com.tsinsi.auth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public User signup(User user) {
        return userWriterMapper.insert(user);
    }

    @Override
    public User findByUsername(String username) {
        return userReaderMapper.findByUsername(username);
    }
}
