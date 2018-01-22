package jokes.test.com.onlineassignment.apiservice.executors;

import android.util.Log;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import jokes.test.com.onlineassignment.apiservice.interactors.ApiInteractor;


/**
 * Created by Gaurav on 1/17/2018.
 * Executor service for submit task and execute at background
 */
public class ApiExecutor {
    private static final int CORE_POOL_SIZE = 1;
    private static final int MAX_POOL_SIZE = 3;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();
    /**
     * thread safe singleton
     */
    private static final ApiExecutor executor = new ApiExecutor();
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * Default executor for initialize default data and configuration of threadpoolexecutor
     */
    private ApiExecutor() {
        int corePoolSize = CORE_POOL_SIZE;
        int maxPoolSize = MAX_POOL_SIZE;
        int keepAliveTime = KEEP_ALIVE_TIME;
        TimeUnit timeUnit = TIME_UNIT;
        BlockingQueue<Runnable> workQueue = WORK_QUEUE;
        threadPoolExecutor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQueue);
    }

    /*get executor*/
    public static ApiExecutor getExecutor() {
        return executor;
    }

    /**
     * Start execution of task
     * @param interactor
     */
    public void run(final ApiInteractor interactor) {
        if (interactor == null) {
            throw new IllegalArgumentException("Interactor to execute can't be null");
        }

        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                interactor.execute();
            }
        });
    }

    /**
     * Close Execution of task
     */
    public void shutDown() {
        if (threadPoolExecutor != null)
            threadPoolExecutor.shutdown();
    }

    /**
     * Fianalize the task execution
     * @throws Throwable throes error of shutdown
     */
    @Override
    protected void finalize() throws Throwable {
//        PrintUtil.systemPrint("finalizing ThreadExecutor ( " + "" + "): Shutting down executor services");
        Log.e("","finalizing ThreadExecutor ( " + "" + "): Shutting down executor services");
        try {

            // It's usually not a good idea to rely on finalize. But this is the easiest way to
            // avoid the "Thread executor" from leaking. The thread(s) of the executor have a
            // reference to their ExecutorService which prevents the ExecutorService from being
            // gc'ed. It is possible that the XMPPConnection is closed and the instance is gc'ed while the
            // threadExecutor ExecutorService call not be gc'ed until it got shut down.
            shutDown();

        } catch (Throwable t) {
            Log.e("ThreadExecutor", "finalize() threw trhowable");
        } finally {
            super.finalize();
        }
    }
}
