public class ThreadLimitTest {
    public static void main(String[] args) {
        int count = 0;
        try {
            while (true) {
                Thread t = new Thread(() -> {
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException ignored) {}
                });
                t.start();
                count++;
            }
        } catch (Throwable t) {
            System.out.println("Max threads = " + count);
        }
    }
}
