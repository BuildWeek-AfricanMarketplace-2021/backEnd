package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Items;

import java.util.List;
;

public interface ItemsService {
    Items save(Items items);
    Items findById(long id);
    List<Items> findAll();
    Items update (Items items, long id);
    void delete(long id);
}


//    List<Items> findAllProduct(String product);
//    ProductAverage findAveragePrice(String commodityProduct);
//    CountryProductAverage findAveragePriceByCountry(String country, String commodityProduct);