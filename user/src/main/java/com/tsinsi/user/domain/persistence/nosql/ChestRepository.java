package com.tsinsi.user.domain.persistence.nosql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChestRepository extends CrudRepository<Chest, String> {

}
