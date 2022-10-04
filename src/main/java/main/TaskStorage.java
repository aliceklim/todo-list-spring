package main;
import main.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TaskStorage {
    private static volatile int currentId = 1;
    private static ConcurrentHashMap<Integer, Task> tasks = new ConcurrentHashMap<Integer, Task>();

    public static synchronized List<Task> getAllTasks(){
        ArrayList<Task>taskList = new ArrayList<>();
        taskList.addAll(tasks.values());
        return  taskList;
    }
    public static synchronized int addTask(Task task){
        int id = currentId++;
        task.setId(id);
        tasks.put(id, task);
        return id;
    }
    public static synchronized Task getTask(int taskId){
        if(tasks.containsKey(taskId)){
            return tasks.get(taskId);
        }
        return null;
    }
    public static synchronized void deleteTask(int taskId){
        tasks.remove(taskId);
    }
    public static synchronized void editTask(int taskId, String name){
        Task task = new Task();
        task.setId(taskId);
        task.setTask(name);
        tasks.put(taskId, task);
    }
}

