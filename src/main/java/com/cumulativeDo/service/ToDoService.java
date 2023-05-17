package com.cumulativeDo.service;
import com.cumulativeDo.model.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class ToDoService {

    private final Map<String, Set<ToDoItem>> items;

    public ToDoService() {
        this.items = new HashMap<>();
    }

    public void addUser(String userName) {
        items.put(userName, new HashSet<>());
    }

    public ToDoItem addTodoItem(String userName, ToDoItem addItem) {
        Set<ToDoItem> existingItems = items.get(userName);
        existingItems.add(addItem);
        items.put(userName, existingItems);
        return addItem;
    }

    public Set<ToDoItem> getTodoItems(String userName) {
        return items.get(userName);
    }

    public ToDoItem deleteTodoItem(String userName, ToDoItem deleteItem) {
        Set<ToDoItem> existingItems = items.get(userName);
        existingItems.remove(deleteItem);
        return deleteItem;
    }

    public ToDoItem completeTodoItem(String userName, ToDoItem completeItem) {
        Set<ToDoItem> existingItems = items.get(userName);
        if (existingItems.contains(completeItem))
            completeItem.markComplete();
        return completeItem;
    }

    public boolean hasUsername(String userName) {
        return items.containsKey(userName);
    }

}