package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.Items;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ItemsRepository extends CrudRepository<Items, Long>{
//    void deleteItemById(long id);
}
