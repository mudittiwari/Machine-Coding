import comment.Comment;
import controllers.role.RoleController;
import controllers.task.TaskController;
import controllers.user.UserController;
import enums.PermissionType;
import proxy.RoleControllerProxy;
import proxy.TaskControllerProxy;
import proxy.UserControllerProxy;
import role.Role;
import role.RoleImpl;
import task.Task;
import task.builder.TaskBuilderAbstract;
import user.User;
import user.UserImpl;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        RoleController roleController = new RoleControllerProxy();
        TaskController taskController = new TaskControllerProxy();
        UserController userController = new UserControllerProxy();

        System.out.println("=========== SYSTEM BOOTSTRAP STARTED ===========");

        Role adminRole = new RoleImpl("Admin", List.of(PermissionType.ADMIN, PermissionType.READ, PermissionType.CREATE, PermissionType.DELETE, PermissionType.UPDATE));
        Role userRole = new RoleImpl("User", List.of(PermissionType.READ, PermissionType.CREATE, PermissionType.DELETE, PermissionType.UPDATE));

        User admin = new UserImpl("Mudit Tiwari", "mudit.alwar31@gmail.com", adminRole);
        User user1 = new UserImpl("Nimit Sharma", "nimitsharma@gmail.com", userRole);
        User user2 = new UserImpl("Rahul Sen", "rahulsen@gmail.com", userRole);
        User user3 = new UserImpl("Sarthak Raj", "sarthakraj@gmail.com", userRole);

        System.out.println("\n--- Adding Roles ---");
        roleController.addRole(adminRole, admin);
        roleController.addRole(userRole, admin);

        System.out.println("\n--- Registering Users ---");
        userController.addUser(admin, admin);
        userController.addUser(user1, admin);
        userController.addUser(user2, admin);

        System.out.println("\n=========== TASK CREATION ===========");
        Task task1 = new TaskBuilderAbstract()
                .setTitle("Design UI")
                .setDesc("Design the dashboard UI")
                .setCreatedBy(admin)
                .setAssignedTo(user1)
                .setReviewedBy(admin)
                .setDeadlineDate(Date.from(LocalDateTime.now().plusDays(3).atZone(ZoneId.systemDefault()).toInstant()))
                .buildTask();
        taskController.addTask(task1, user1);

        Task task2 = new TaskBuilderAbstract()
                .setTitle("API Development")
                .setDesc("Develop backend APIs")
                .setCreatedBy(admin)
                .setAssignedTo(user2)
                .setReviewedBy(admin)
                .setDeadlineDate(Date.from(LocalDateTime.now().plusDays(5).atZone(ZoneId.systemDefault()).toInstant()))
                .buildTask();

        taskController.addTask(task2, user2);

        System.out.println("\n=========== TASK UPDATES ===========");
        taskController.updateTask(task1, "title", "Updated UI Design", user1);
        taskController.updateTask(task2, "desc", "Build RESTful APIs", user2);

        System.out.println("\n=========== COMMENTS AND REPLIES ===========");
        taskController.addComment(task1, user1, "Initial comment for task 1");
        taskController.addComment(task1, user3, "Please prioritize this");

        List<Comment> task1Comments = taskController.getComments(task1, user1);

        String commentId1 = task1Comments.get(0).getId();
        taskController.addReply(task1, user3, commentId1, "Acknowledged");

        task1Comments = taskController.getComments(task1, user1);
        String replyId1 = task1Comments.get(0).getReplies().get(0).getId();

        taskController.addReply(task1, user1, replyId1, "Thanks for confirmation");


        System.out.println("\n=========== COMMENTS LOG ===========");
        List<Comment> finalComments = taskController.getComments(task1, user2);
        printComments(finalComments, 0);

        System.out.println("\n=========== TASK COMPLETION TEST ===========");
        taskController.updateTask(task1, "finalReviewed", true, admin);

        System.out.println("\n=========== SYSTEM TEST COMPLETED ===========");
    }

    public static void printComments(List<Comment> comments, int depth) {
        String indent = "  ".repeat(depth);
        for (Comment comment : comments) {
            System.out.println(indent + "- " + comment.getUser().getName() + ": " + comment.getMessage());
            if (!comment.getReplies().isEmpty()) {
                printComments(comment.getReplies(), depth + 1);
            }
        }
    }
}
