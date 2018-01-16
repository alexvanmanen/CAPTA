
public class Start {
	public static void main(String[] args) {

		Pizza pizza1 = new Ham(new Pinneapple(new Classic()));
		System.out.println(pizza1.getPrice());

		Pizza pizza2 = new Mushroom(new Mushroom(new Italian()));
		System.out.println(pizza2.getPrice());
	}
}
