package com.lambdaschool.foundation.services;
import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.Items;
import com.lambdaschool.foundation.models.User;
import com.lambdaschool.foundation.repository.ItemsRepository;
import com.lambdaschool.foundation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "ItemsService")
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    private ItemsRepository itemsrepos;

    @Autowired
    private HelperFunctions helperFunctions;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public Items save(Items items){
        Items newItem = new Items();

        if(items.getItemid() != 0)
        {
            itemsrepos.findById(items.getItemid())
                    .orElseThrow(() -> new ResourceNotFoundException("Item " + items.getItemid() + " not found!"));
            newItem.setItemid(items.getItemid());
        }
        User user = userRepository.findById(items.getUser().getUserid()).orElseThrow(() -> new ResourceNotFoundException("User ID not found" + items.getUser().getUserid()));

        newItem.setUser(user);
        newItem.setLocation(items.getLocation());
        newItem.setName(items.getName());
        newItem.setDescription(items.getDescription());
        newItem.setCategory(items.getCategory());
        newItem.setPrice(items.getPrice());
        return itemsrepos.save(newItem);
    }

    @Override
    public Items findById(long id){
        return itemsrepos.findById((id))
                .orElseThrow(() -> new ResourceNotFoundException("Item with id of " + id + " not found!"));
    }

    @Override
    public List<Items> findAll(){
        List<Items> myList = new ArrayList<>();
    itemsrepos.findAll().iterator().forEachRemaining(myList::add);
    return myList;
    }

    @Override
    public Items update(Items items, long id) {
        Items currentItem = findById(id);

        if(helperFunctions.isAuthorizedToMakeChange(currentItem.getUser().getUsername()))
        {
            if(items.getUser() != null){
                User user = userRepository.findById(items.getUser().getUserid()).orElseThrow(() -> new ResourceNotFoundException("User ID update not working" + items.getUser().getUserid()));
                currentItem.setUser(user);
            }
            if(items.getLocation() != null){
                currentItem.setLocation((items.getLocation()));
            }

            if (items.getName() != null){
                currentItem.setName((items.getName()));
            }
            if(items.getDescription() != null){
                    currentItem.setDescription((items.getDescription()));
                }
            if(items.getCategory() != null){
                currentItem.setCategory((items.getCategory()));
            }

            return itemsrepos.save(currentItem);
        } else
        {
            throw new ResourceNotFoundException("This user is not authorized to make change");
        }
    }
    @Override
    public void delete(long id) {
        Items currentItem = findById(id);
        if(helperFunctions.isAuthorizedToMakeChange(currentItem.getUser().getUsername())) {
            itemsrepos.deleteById(id);
        }
    }

//    @Override
//    public List<Items> findAllProduct(String product) {
//        List<Items> rtnlist = itemsrepos.findAllByCommodityProduct(product);
//        return rtnlist;
//    }


}
