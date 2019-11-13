public class ThreadTest {
    public static final ThreadLocal<String> threadlocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "Default";
        }
    };

    public static void main(String[] args) {
        String hello = "Hello1";
        threadlocal.set(hello);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //threadlocal.set("Hello2");
                System.out.println("Thread: " + threadlocal.get());
            }
        }).start();

        System.out.println("Main Thread: " + threadlocal.get());
    }
}
