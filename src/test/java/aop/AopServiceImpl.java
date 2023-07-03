package aop;

import org.springframework.stereotype.Component;

@ClassAop
@Component
public class AopServiceImpl implements AopService {

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param){
        return "ok";
    }
}
