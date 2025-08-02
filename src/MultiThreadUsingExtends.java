public class MultiThreadUsingExtends extends Thread{
    public int threadNumber;
    MultiThreadUsingExtends(int threadNumber){
        this.threadNumber = threadNumber;
    }

    @Override
    public void run(){
        for(int i=1; i<=5; i++){
            System.out.println(i + " From thread number " + threadNumber);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
