import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

class Task {
    private String description;
    private String dueDate;
    private boolean isCompleted;

    public Task(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    public void changeDueDate(String newDueDate) {
        dueDate = newDueDate;
    }

    @Override
    public String toString() {
        String status = isCompleted ? "[Completed]" : "[Pending]";
        return status + " " + description + " (Due: " + dueDate + ")";
    }
}

class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void updateTaskStatus(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.markAsCompleted();
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Invalid task number. Task not updated.");
        }
    }

    public void updateTaskDueDate(int taskNumber, String newDueDate) {
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.changeDueDate(newDueDate);
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Invalid task number. Task not updated.");
        }
    }

    public void removeTask(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            tasks.remove(taskNumber - 1);
            System.out.println("Task removed successfully!");
        } else {
            System.out.println("Invalid task number. Task not removed.");
        }
    }
}

public class Mini_Project {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (true) {
            System.out.println("Task List Manager");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Remove Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter task description: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter due date (YYYY-MM-DD): ");
                        String dueDate = scanner.nextLine();
                        Task newTask = new Task(description, dueDate);
                        taskList.addTask(newTask);
                        System.out.println("Task added successfully!\n");
                        break;

                    case 2:
                        System.out.println("Task List:");
                        ArrayList<Task> tasks = taskList.getTasks();
                        if (tasks.isEmpty()) {
                            System.out.println("No tasks found.\n");
                        } else {
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println((i + 1) + ". " + tasks.get(i));
                            }
                            System.out.println();
                        }
                        break;

                    case 3:
                        System.out.print("Enter the task number to update: ");
                        int updateChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline
                        System.out.println("1. Mark as Completed");
                        System.out.println("2. Change Due Date");
                        System.out.print("Enter your choice: ");
                        int action = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline
                        if (action == 1) {
                            taskList.updateTaskStatus(updateChoice);
                        } else if (action == 2) {
                            System.out.print("Enter the new due date (YYYY-MM-DD): ");
                            String newDueDate = scanner.nextLine();
                            taskList.updateTaskDueDate(updateChoice, newDueDate);
                        }
                        break;

                    case 4:
                        System.out.print("Enter the task number to remove: ");
                        int removeChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline
                        taskList.removeTask(removeChoice);
                        break;

                    case 5:
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.\n");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
//end