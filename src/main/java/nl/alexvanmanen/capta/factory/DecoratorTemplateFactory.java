package nl.alexvanmanen.capta.factory;

import nl.alexvanmanen.capta.DecoratorTemplate;
import nl.alexvanmanen.capta.dynamic.DecoratorTemplateDynamic;
import nl.alexvanmanen.capta.model.AssignmentOutput;
import nl.alexvanmanen.capta.statics.DecoratorTemplateStatic;

/* TODO: implement abstract factory for all different templates */
public class DecoratorTemplateFactory {

	
	public DecoratorTemplate getStaticTemplate(AssignmentOutput assignmentOutput){
		return new DecoratorTemplateStatic(assignmentOutput);
	}
	
	public DecoratorTemplate getDynamicTemplate(AssignmentOutput assignmentOutput){
		return new DecoratorTemplateDynamic(assignmentOutput);
	}
	
}
