package lessons.oop;


import jlm.core.model.lesson.Lesson;

public class Main extends Lesson {

	@Override
	protected void loadExercises() {
		addExercise(new CreateBuggle(this));
		addExercise(new AddDirection(this));
		addExercise(new Move(this));
	}

}
