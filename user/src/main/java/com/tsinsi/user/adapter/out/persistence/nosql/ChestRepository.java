package com.tsinsi.user.adapter.out.persistence.nosql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChestRepository extends CrudRepository<Chest, String> {

}
