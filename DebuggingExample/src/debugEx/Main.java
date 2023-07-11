package debugEx;

public class Main {
	
	public static void main(String[] args) {
		Counter counter = new Counter();
		counter.count();
	    System.out.println("We have counted " + counter.getResult());

		Counter counter2 = new Counter();
	    counter2.count();
	    System.out.println("We have counted " + counter2.getResult());

	    System.out.println("Total: " + Counter.getTotal());
	}
}
