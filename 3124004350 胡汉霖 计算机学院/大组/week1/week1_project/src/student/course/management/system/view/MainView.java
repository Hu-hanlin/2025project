package student.course.management.system.view;

import student.course.management.system.model.Course;
import student.course.management.system.model.User;
import student.course.management.system.service.AdminService;
import student.course.management.system.service.StudentService;
import student.course.management.system.service.UserService;

import java.util.Scanner;

public class MainView {
    private UserService userService = new UserService();
    private AdminService adminService = new AdminService();
    private StudentService studentService = new StudentService();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            showLoginMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("退出系统！");
                    return;
                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }

    private void showLoginMenu() {
        System.out.println("===========================");
        System.out.println("🎓 学生选课管理系统");
        System.out.println("===========================");
        System.out.println("1. 登录");
        System.out.println("2. 注册");
        System.out.println("3. 退出");
        System.out.print("请选择操作（输入 1-3）：");
    }

    private void login() {
        System.out.println("===== 用户登录 =====");
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();
        User user = userService.login(username, password);
        if (user != null) {
            System.out.println("登录成功！你的角色是：" + (user.getRole() == 1 ? "学生" : "管理员"));
            if (user.getRole() == 1) {
                studentMenu(user.getId());
            } else {
                adminMenu();
            }
        } else {
            System.out.println("用户名或密码错误，请重新输入！");
        }
    }

    private void register() {
        System.out.println("===== 用户注册 =====");
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();
        System.out.print("请确认密码：");
        String confirmPassword = scanner.nextLine();
        if (!password.equals(confirmPassword)) {
            System.out.println("两次输入的密码不一致，请重新输入！");
            return;
        }
        System.out.print("请选择角色（输入 1 代表学生，2 代表管理员）：");
        int role = scanner.nextInt();
        scanner.nextLine();
        User user = new User(username, password, role);
        if (userService.register(user)) {
            System.out.println("注册成功！请返回主界面登录。");
        } else {
            System.out.println("注册失败，请稍后再试！");
        }
    }

    private void studentMenu(int studentId) {
        while (true) {
            System.out.println("===== 学生菜单 =====");
            System.out.println("1. 查看可选课程");
            System.out.println("2. 选择课程");
            System.out.println("3. 退选课程");
            System.out.println("4. 查看已选课程");
            System.out.println("5. 修改手机号");
            System.out.println("6. 退出");
            System.out.print("请选择操作（输入 1-6）：");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    showAvailableCourses();
                    break;
                case 2:
                    selectCourse(studentId);
                    break;
                case 3:
                    dropCourse(studentId);
                    break;
                case 4:
                    showSelectedCourses(studentId);
                    break;
                case 5:
                    updateStudentPhone(studentId);
                    break;
                case 6:
                    System.out.println("退出学生菜单！");
                    return;
                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }

    private void adminMenu() {
        while (true) {
            System.out.println("===== 管理员菜单 =====");
            System.out.println("1. 查询所有学生");
            System.out.println("2. 修改学生手机号");
            System.out.println("3. 查询所有课程");
            System.out.println("4. 新增课程");
            System.out.println("5. 删除课程");
            System.out.println("6. 查询某课程的学生名单");
            System.out.println("7. 查询某学生的选课情况");
            System.out.println("8. 退出");
            System.out.print("请选择操作（输入 1-8）：");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    showAllStudents();
                    break;
                case 2:
                    updateStudentPhoneByAdmin();
                    break;
                case 3:
                    showAllCourses();
                    break;
                case 4:
                    addCourse();
                    break;
                case 5:
                    deleteCourse();
                    break;
                case 6:
                    showStudentsByCourse();
                    break;
                case 7:
                    showCoursesByStudent();
                    break;
                case 8:
                    System.out.println("退出管理员菜单！");
                    return;
                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }

    private void showAvailableCourses() {
        System.out.println("可选课程列表：");
        for (var course : studentService.getAvailableCourses()) {
            System.out.println("课程 ID：" + course.getId() + "，课程名称：" + course.getName() + "，学分：" + course.getCredits());
        }
    }

    private void selectCourse(int studentId) {
        showAvailableCourses();
        System.out.print("请输入要选择的课程 ID：");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        if (studentService.selectCourse(studentId, courseId)) {
            System.out.println("选课成功！");
        } else {
            System.out.println("选课失败，请稍后再试！");
        }
    }

    private void dropCourse(int studentId) {
        showSelectedCourses(studentId);
        System.out.print("请输入要退选的课程 ID：");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        if (studentService.dropCourse(studentId, courseId)) {
            System.out.println("退课成功！");
        } else {
            System.out.println("退课失败，请稍后再试！");
        }
    }

    private void showSelectedCourses(int studentId) {
        System.out.println("已选课程列表：");
        for (var sc : studentService.getSelectedCourses(studentId)) {
            var course = studentService.getAvailableCourses().stream().filter(c -> c.getId() == sc.getCourseId()).findFirst().orElse(null);
            if (course != null) {
                System.out.println("课程 ID：" + course.getId() + "，课程名称：" + course.getName() + "，学分：" + course.getCredits());
            }
        }
    }

    private void updateStudentPhone(int studentId) {
        System.out.print("请输入新的手机号：");
        String newPhone = scanner.nextLine();
        if (adminService.updateStudentPhone(studentId, newPhone)) {
            System.out.println("手机号修改成功！");
        } else {
            System.out.println("手机号修改失败，请稍后再试！");
        }
    }

    private void showAllStudents() {
        System.out.println("所有学生信息：");
        for (var student : adminService.getAllStudents()) {
            System.out.println("学生 ID：" + student.getId() + "，姓名：" + student.getName() + "，手机号：" + student.getPhone());
        }
    }

    private void updateStudentPhoneByAdmin() {
        showAllStudents();
        System.out.print("请输入要修改手机号的学生 ID：");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("请输入新的手机号：");
        String newPhone = scanner.nextLine();
        if (adminService.updateStudentPhone(studentId, newPhone)) {
            System.out.println("手机号修改成功！");
        } else {
            System.out.println("手机号修改失败，请稍后再试！");
        }
    }

    private void showAllCourses() {
        System.out.println("所有课程信息：");
        for (var course : adminService.getAllCourses()) {
            System.out.println("课程 ID：" + course.getId() + "，课程名称：" + course.getName() + "，学分：" + course.getCredits());
        }
    }

    private void addCourse() {
        System.out.print("请输入课程名称：");
        String courseName = scanner.nextLine();
        System.out.print("请输入课程学分：");
        int credits = scanner.nextInt();
        scanner.nextLine();
        var course = new Course(courseName, credits);
        if (adminService.addCourse(course)) {
            System.out.println("课程添加成功！");
        } else {
            System.out.println("课程添加失败，请稍后再试！");
        }
    }

    private void deleteCourse() {
        showAllCourses();
        System.out.print("请输入要删除的课程 ID：");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        if (adminService.deleteCourse(courseId)) {
            System.out.println("课程删除成功！");
        } else {
            System.out.println("课程删除失败，请稍后再试！");
        }
    }

    private void showStudentsByCourse() {
        showAllCourses();
        System.out.print("请输入要查询的课程 ID：");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("选择该课程的学生列表：");
        for (var sc : adminService.getStudentsByCourse(courseId)) {
            var student = adminService.getAllStudents().stream().filter(s -> s.getId() == sc.getStudentId()).findFirst().orElse(null);
            if (student != null) {
                System.out.println("学生 ID：" + student.getId() + "，姓名：" + student.getName() + "，手机号：" + student.getPhone());
            }
        }
    }

    private void showCoursesByStudent() {
        showAllStudents();
        System.out.print("请输入要查询的学生 ID：");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("该学生的选课信息：");
        for (var sc : adminService.getCoursesByStudent(studentId)) {
            var course = adminService.getAllCourses().stream().filter(c -> c.getId() == sc.getCourseId()).findFirst().orElse(null);
            if (course != null) {
                System.out.println("课程 ID：" + course.getId() + "，课程名称：" + course.getName() + "，学分：" + course.getCredits());
            }
        }
    }
}    