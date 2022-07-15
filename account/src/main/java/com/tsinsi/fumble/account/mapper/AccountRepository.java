package com.tsinsi.fumble.account.mapper;

import com.tsinsi.fumble.account.entity.Account;
import com.tsinsi.fumble.account.mapper.reader.AccountReaderMapper;
import com.tsinsi.fumble.account.mapper.writer.AccountWriterMapper;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "account")
public class AccountRepository {

    private final AccountWriterMapper accountWriterMapper;
    private final AccountReaderMapper accountReaderMapper;
    private final Hashids hashids;

    @Autowired
    public AccountRepository(AccountWriterMapper accountWriterMapper, AccountReaderMapper accountReaderMapper, Hashids hashids) {
        this.accountWriterMapper = accountWriterMapper;
        this.accountReaderMapper = accountReaderMapper;
        this.hashids = hashids;
    }

    public List<Account> findAccounts(String hash) {
        long id = 0;
        try {
            id = hashids.decode(hash)[0];
        } catch (Exception ignored) {
        }
        return accountReaderMapper.findAccounts(id);
    }

    @Cacheable(key = "#p0")
    public Account findById(long id) {
        return accountReaderMapper.findById(id);
    }

    @Cacheable(key = "#p0")
    public Account findBy(long id) {
        return accountReaderMapper.findBy(id);
    }

    public Account findByUsername(String username) {
        return accountReaderMapper.findByUsername(username);
    }
}
