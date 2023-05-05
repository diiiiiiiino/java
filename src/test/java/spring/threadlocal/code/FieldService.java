package spring.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldService {
    private String name;

    public void logic(String name) {
        save(name);
        sleep(1000);
        find();
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void save(String name){
        this.name = name;
        log.debug("저장 : " + name);
    }

    private void find(){
        log.debug("조회 : " + name);
    }

    public String getName() {
        return name;
    }
}
