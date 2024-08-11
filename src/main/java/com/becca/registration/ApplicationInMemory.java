package com.becca.registration;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class ApplicationInMemory implements ApplicationService {

    private Map<UUID, Task> tasks;

    public ApplicationInMemory() {
        this.tasks = new LinkedHashMap<UUID, Task>();
    }

    @Override
    public Map<UUID, Task> readTasks() {
        return tasks;
    }

    @Override
    public Task readTask(String id) {
        return tasks.get(UUID.fromString(id));
    }

    @Override
    public void createTask(Task task) {
        updateTask(task);
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void deleteTask(String id) {
        tasks.remove(UUID.fromString(id));
    }

    @Override
    public void createOrUpdateTask(Task task) {
        Task localTask = readTask(task.getId().toString());
        if (localTask == null) {
            createTask(task);
        } else {
            updateTask(task);
        }
    }

}
