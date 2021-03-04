package com.lambdaschool.foundation.controllers;

import com.lambdaschool.foundation.services.UserService;

import com.lambdaschool.foundation.services.ItemsService;
import com.lambdaschool.foundation.models.User;
import com.lambdaschool.foundation.models.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

    @RestController
    @RequestMapping("items")
    public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/item", produces = "application/json")
    public ResponseEntity<?> listAllItems(){
        List<Items> myItems = itemsService.findAll();
        return new ResponseEntity<>(myItems, HttpStatus.OK);
    }

    @GetMapping(value = "/item/{itemid}", produces = "application/json")
    public ResponseEntity<?> getItemById(@PathVariable long itemid){
        Items i = itemsService.findById(itemid);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @PostMapping(value= "/item", consumes = "application/json")
    public ResponseEntity<?> addNewItem(@Valid @RequestBody Items newItem, Authentication authentication){
        User user = userService.findByName(authentication.getName());
        newItem.setItemid(0);
        newItem.setUser(user);
        newItem = itemsService.save(newItem);

        HttpHeaders responseHeader = new HttpHeaders();
        URI newItemURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{itemid")
                .buildAndExpand(newItem.getItemid())
                .toUri();

//        responseHeader.setLocation(newItemURI);

        return new ResponseEntity<>(newItem, responseHeader, HttpStatus.CREATED);
    }

    @PutMapping(value = "item/{itemid}", consumes = "application/json")
    public ResponseEntity<?> updateFullItem(@Valid @RequestBody Items updateItem,
                                            @PathVariable long itemid){
        updateItem.setItemid(itemid);
        updateItem = itemsService.save(updateItem);

        return new ResponseEntity<>(updateItem, HttpStatus.OK);
    }

    @PatchMapping(value = "/item/{itemid}", consumes = "application/json")
    public ResponseEntity<?> updateItem(@RequestBody Items items, @PathVariable long itemid)
    {
        items = itemsService.update(items, itemid);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @DeleteMapping("/item/{itemid}")
    public ResponseEntity<?> deleteItemById(@PathVariable long itemid)
    {
        itemsService.delete(itemid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

