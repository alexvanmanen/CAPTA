package nl.alexvanmanen.capta.dynamic;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MethodExecutor implements Runnable{
	private Class<?> classs;
	private Method method;
	private String error = null;
	
	public MethodExecutor(Class classs, Method method) {
		this.classs = classs;
		this.method = method;
	}

	
	public void execute() {
		final ExecutorService service = Executors.newSingleThreadExecutor();
	
		try {
			String[] params = null;
			final Future<Object> f = service.submit(() -> {
				method.invoke(null, (Object) params);
				return "Method executed within time";
			});

			System.out.println(f.get(1, TimeUnit.SECONDS));
		} catch (final TimeoutException e) {
			error = "Method execution took to long";
			System.err.print(" - "+error+" - ");			

		} catch(ExecutionException e) {
			error = "Unable to execute method";
			System.err.print(" - "+error+" - ");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
            service.shutdown();
        }
	}
	
	
	public String getError(){
		return error;
	}
	


	@Override
	public void run() {
		execute();
		
		
	}
	
}