package nl.alexvanmanen.capta.dynamic;

import nl.alexvanmanen.capta.model.Criterion;

public class Template2 {




	public static void main(String[] args)  {

		Input[] array = generateInput();
		for(Input input: array){
			String content = new Console().getConsoleOutput(input.input);
			boolean actual = content.contains(input.output.toString());
			//System.out.println("assert: "+ (input.expected == actual));
			
			if(input.expected){
				System.out.println(content.contains(input.output.toString()));
			} else  {
				System.out.println(!content.contains(input.output.toString()));
			}
			
		}
		System.out.println("einde");

	}

	private static Input[] generateInput() {
		
		
		//INPUT > 10 #Het getal is groter dan 10
		Input i1 = new Input("10","Het getal is groter dan 10.",false);
		Input i2 = new Input("11","Het getal is groter dan 10.",true);
		
		//INPUT != 50 #Het getal is ongelijk aan 50
		Input i3 = new Input("51","Het getal is niet gelijk aan 50.",true);
		Input i4 = new Input("50","Het getal is niet gelijk aan 50.",false);
		
		//INPUT > 1 AND INPUT < 9 #Het getal is groter dan 1 en kleiner dan 9
		Input i5 = new Input("1","Het getal is groter dan 1 en kleiner dan 9.",false);
		Input i6 = new Input("2","Het getal is groter dan 1 en kleiner dan 9.",true);
		Input i7 = new Input("8","Het getal is groter dan 1 en kleiner dan 9.",true);
		Input i8 = new Input("9","Het getal is groter dan 1 en kleiner dan 9.",false);
		
		//INPUT % 3 == 0 || INPUT % 4 == 0 #Het getal is deelbaar door 3 of 4
		//Input i9 = new Input("3","Het getal is deelbaar door 3 of 4.",true);
		//Input i10 = new Input("4","Het getal is deelbaar door 3 of 4.",true);
		Input i11 = new Input("5","Het getal is deelbaar door 3 of 4.",false);
		
		//Input[] array = { i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11 };
		Input[] array = { i1, i2, i3, i4, i5, i6, i7, i8, i11 };
		return array;
	}
}

class Input {
	Input (String input, String output, boolean expected){
		this.input = input;
		this.expected = expected;
		this.output = new Output(output);
	}
	String input;
	boolean expected;
	Output output;
	Criterion criterion;
	
	public String toString(){
		return input + " " + output+ " " + expected;
	}
	
}

class Output {
	Output (String expectedOutput){
		this.expectedOutput = expectedOutput;
	}
	String expectedOutput;
	
	public String toString(){
		return this.expectedOutput;
	}
}



