//template method class.
abstract class PastaClass {
	public final void makeRecipe() {
		boilWater();
		addSauce();
		addGarnish();
	}

	private void boilWater() {
		System.out.println("Boil Water!");
	}

	protected abstract void addSauce();

	protected abstract void addGarnish();
}

class SphagettiMeatballs extends PastaClass{

	@Override
	protected void addSauce() {
		System.out.println("Add Tomato Sauce!");
	}

	@Override
	protected void addGarnish() {
		System.out.println("Add Parmesan Cheese!");
	}
}

class PenneAlfredo extends PastaClass{

	@Override
	protected void addSauce() {
		System.out.println("Add Alfredo Sauce!");
	}

	@Override
	protected void addGarnish() {
		System.out.println("Add parsley!");
	}
	
}

public class TemplateMethodPattern {
	public TemplateMethodPattern() {
		PastaClass pasta1 = new SphagettiMeatballs();
		PastaClass pasta2 = new PenneAlfredo();
		pasta1.makeRecipe();
		pasta2.makeRecipe();
	}

	public static void main(String[] args) {
		new TemplateMethodPattern();
	}
}
