
public abstract class Ingredient extends Pizza {
	protected Pizza pizza;

	public Ingredient(Pizza pizzaToDecorate) {
		this.pizza = pizzaToDecorate;
	}

	@Override
	public double getPrice() {
		return (this.pizza.getPrice() + this.myPrice);
	}
}
