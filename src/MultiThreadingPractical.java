public class MultiThreadingPractical {
    MultiThreadUsingExtends thread1 = new MultiThreadUsingExtends(1);
    MultiThreadUsingExtends thread2 = new MultiThreadUsingExtends(2);

   public void runThreadsUisngExtends(){
       thread1.start();
       thread2.start();
   }
}
