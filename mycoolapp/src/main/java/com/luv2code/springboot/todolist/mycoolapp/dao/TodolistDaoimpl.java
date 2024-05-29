package com.luv2code.springboot.todolist.mycoolapp.dao;

import com.luv2code.springboot.todolist.mycoolapp.entity.Todolistproject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TodolistDaoimpl implements TodolistDao {

    // define field for entitymanager
    private EntityManager entityManager;


    // set up constructor injection
    @Autowired
    public TodolistDaoimpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }


    @Override
    public List<Todolistproject> findAll() {
        // create a query
        TypedQuery<Todolistproject> theQuery = entityManager.createQuery("from Todolistproject",Todolistproject.class);

        // execute a query and get result list
        List<Todolistproject>  todolists = theQuery.getResultList();
        // return the results

        return todolists;
    }
    @Transactional
    @Override
    public Todolistproject save(Todolistproject theTodolistproject) {
        // save todolist task
        Todolistproject dbTodolistproject = entityManager.merge(theTodolistproject);

        // return the dbTodolistproject
        return dbTodolistproject;
    }


    @Transactional
    @Override
    public Todolistproject deleteAll( ) {
    entityManager.createQuery("DELETE from Todolistproject").executeUpdate();

        return null;
    }


    @Transactional
    @Override
    public void deleteById(int taskId) { // will create one in the future for the endpoint
        // finds todolist task by id
        Todolistproject theTodolistproject = entityManager.find(Todolistproject.class, taskId);

        // deletes todolist task
        entityManager.remove(theTodolistproject);


    }

    @Transactional
    @Override
    public Todolistproject findById(int taskId) {
        // finds todolist task by id
        Todolistproject theTodolistproject = entityManager.find(Todolistproject.class, taskId);

        return theTodolistproject;

    }


    @Transactional
    @Override
    public void markAsComplete(int taskId) {
        Todolistproject theTodolistproject = entityManager.find(Todolistproject.class, taskId);
        if (theTodolistproject == null) {
            throw new RuntimeException("Task id is not found: Error " + taskId);
        }
        theTodolistproject.setCompleted(true);
        entityManager.merge(theTodolistproject);

    }
}
