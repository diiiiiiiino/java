package spring.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void logic(String name) {
        log.debug("가져온 값 : " + threadLocal.get() + ", 저장 값 : " + name);
        save(name);
        sleep(1000);
        log.debug("저장하고 난 후 값 : " + threadLocal.get());
        threadLocal.remove();
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void save(String name){
        threadLocal.set(name);
    }
}
