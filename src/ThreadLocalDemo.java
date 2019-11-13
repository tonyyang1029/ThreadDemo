/**
 * https://blog.csdn.net/chen_kkw/article/details/79176371
 */

import java.util.ArrayList;

class Context {
    private String userName;

    public Context(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userName;
    }
}

class UserRepository {
    ArrayList<String> strList = new ArrayList<>();

    public UserRepository() {
        strList.add("Kathy");
        strList.add("Kelly");
        strList.add("Cobbin");
        strList.add("Tony");
    }

    public int getRepoSize(int i) {
        return strList.size();
    }

    public String getEntry(int i) {
        if (i >= strList.size() || i < 0) {
            return null;
        }
        else {
            return strList.get(i);
        }
    }

    public void addEntry(String entry) {
        strList.add(entry);
    }
}

class ThreadLocalWithUserContext implements Runnable {
    private static ThreadLocal<Context> userContext = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            return new Context("Default");
        }
    };
    private Integer userId;
    private UserRepository userRepository = new UserRepository();

    public ThreadLocalWithUserContext(Integer userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        String userName = userRepository.getEntry(userId);
        userContext.set(new Context(userName));
        System.out.println("User ID: " + userId + ", User Name: " + userContext.get());
    }
}

public class ThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocalWithUserContext user1 = new ThreadLocalWithUserContext(1);
        ThreadLocalWithUserContext user2 = new ThreadLocalWithUserContext(2);
        ThreadLocalWithUserContext user6 = new ThreadLocalWithUserContext(6);

        new Thread(user1).start();
        new Thread(user2).start();
        new Thread(user6).start();
    }
}
