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
			System.err.print(" - Method execution took to long - ");

		} catch(ExecutionException e) {
			System.err.print(" - Unable to execute method - ");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
            service.shutdown();
        }
	}


	@Override
	public void run() {
		execute();
		
		
	}
	
}