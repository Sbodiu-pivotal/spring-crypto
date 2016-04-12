package io.pivotal.apac;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BadAESCryptoTest {

    ExecutorService threadPool;

    @Before
    public void setThreadPool() {
        threadPool = Executors.newFixedThreadPool(4);
    }

    @After
    public void shutdownThreadPool() {
        threadPool.shutdown();
    }


    // Throws java.util.concurrent.ExecutionException:
    //  java.lang.RuntimeException:
    //      java.lang.IllegalStateException: Cipher not initialized
    @Test
    // Uncomment the line to make tests pass
//            (expected = java.util.concurrent.ExecutionException.class)
    public void throwExecutionException() throws InterruptedException, ExecutionException {
        List<StringTask> tasks = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            tasks.add(new StringTask("myValue" + i));
        }

        CompletionService<String> pool = new ExecutorCompletionService<>(threadPool);

        for (StringTask task : tasks) {
            pool.submit(task);
        }

        for(int i = 0; i < tasks.size(); i++){
            String result = pool.take().get();
        }
    }

    private final class StringTask implements Callable<String> {
        private final String value;
        private final String encrypted;

        public StringTask(String value) {
            this.value = value;
            this.encrypted = BadAESCrypto.encrypt(value);
        }

        public String call(){
            //Long operations
            String decrypted = BadAESCrypto.decrypt(encrypted);
            System.out.println("Value: " + decrypted + " , Original " + value);

            return decrypted;
        }
    }

}