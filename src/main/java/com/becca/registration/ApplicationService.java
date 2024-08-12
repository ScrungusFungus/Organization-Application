package com.becca.registration;

import java.util.Map;
import java.util.UUID;

public interface ApplicationService {

    public Map<UUID, Task> readTasks();

    public Task readTask(String id);

    public void createTask(Task Task);

    public void updateTask(Task Task);

    public void deleteTask(String id);

    public void createOrUpdateTask(Task Task);
    
    public void createUser(String name, String email, String pass, String contact);
    
    public void loginUser(String email, String pass);

}
