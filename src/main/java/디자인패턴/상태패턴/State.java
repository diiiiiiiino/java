package 디자인패턴.상태패턴;

public interface State {
 
	void insertQuarter();
	void ejectQuarter();
	void turnCrank();
	void dispense();
	
	void refill();
}
