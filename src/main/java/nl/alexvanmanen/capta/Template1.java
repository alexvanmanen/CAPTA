package nl.alexvanmanen.capta;

public interface Template1 extends Template {

	public void setSignature(String className, String methodName);

	public void setVariable(String type, String name);

	public void setWhatIsBeingPrinted(String printed);
}
