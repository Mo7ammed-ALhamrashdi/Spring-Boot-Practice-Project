package com.cl.demo.services;

import com.cl.demo.DemoApplication;
import com.cl.demo.entities.Task;
import com.cl.demo.requestobjects.TaskCreateRequest;
import com.cl.demo.requestobjects.TaskUpdateRequest;
import com.cl.demo.utils.HelperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TaskService {
    public static final String TASK_SAVED = "Task saved";
    public static final String TASK_TITLE_REQUIRED = "Task title is required";
    public Map<String, String> addTask(TaskCreateRequest req) {Map<String, String> response = new HashMap<>();
        if (req.getTitle() == null || req.getTitle().isBlank()) {
            response.put("error", TASK_TITLE_REQUIRED);
            return response;
        }
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setIsActive(Boolean.TRUE);
        task.setCreatedDate(new Date());
        task.setTaskNumber(generateTaskNumber());
        task.setTitle(req.getTitle());
        task.setDescription(req.getDescription());
        task.setDueDate(req.getDueDate());
        task.setStartDate(req.getStartDate());
        task.setTaskStatus(req.getTaskStatus());
        task.setIsAssigned(req.getIsAssigned());
        if (DemoApplication.Task_List.add(task)) {
            response.put("response", TASK_SAVED);
        }
        return response;
    }
    public Task getTaskById(String uuid) {
        for (Task task : DemoApplication.Task_List) {
            if (task.getId().toString().equals(uuid) && Boolean.TRUE.equals(task.getIsActive())) {
                return task;
            }
        }
        return new Task();
    }
    public List<Task> getAllTasks() {
        List<Task> resultList = new ArrayList<>();
        for (Task task : DemoApplication.Task_List) {
            if (Boolean.TRUE.equals(task.getIsActive())) {
                resultList.add(task);
            }
        }
        return resultList;
    }
    public Task updateTask(
            TaskUpdateRequest updateObj) {Task task = getTaskById(updateObj.getUuid());
        if (task.getId() == null || !Boolean.TRUE.equals(task.getIsActive())) {
            return task;
        }
        DemoApplication.Task_List.remove(task);
        task.setTitle(HelperUtils.compare(task.getTitle(), updateObj.getTitleToUpdate()));
        task.setDescription(HelperUtils.compare(task.getDescription(), updateObj.getDescriptionToUpdate()));
        task.setTaskStatus(HelperUtils.compare(task.getTaskStatus(), updateObj.getTaskStatusToUpdate()));
        task.setDueDate(HelperUtils.compare(task.getDueDate(), updateObj.getDueDateToUpdate()));
        task.setIsAssigned(HelperUtils.compare(task.getIsAssigned(), updateObj.getIsAssignedToUpdate()));
        task.setUpdatedDate(new Date());
        DemoApplication.Task_List.add(task);
        return task;
    }
    public Boolean deleteById(String uuid) {Task task = getTaskById(uuid);
        if (task.getId() == null || !Boolean.TRUE.equals(task.getIsActive())) {
            return false;
        }
        task.setIsActive(Boolean.FALSE);
        task.setUpdatedDate(new Date());
        return true;
    }
    public String generateTaskNumber() {
        return "TASK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}