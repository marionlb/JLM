package lessons.oop;


import jlm.core.model.lesson.Lesson;

public class Main extends Lesson {

	@Override
	protected void loadExercises() {
		addExercise(new MiniBuggle(this));
	}

}
