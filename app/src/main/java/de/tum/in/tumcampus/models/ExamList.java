package de.tum.in.tumcampus.models;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Wrapper class holding a list of exams.
 *
 * Note: This model is based on the TUMOnline web service response format for a
 * corresponding request.
 */
@SuppressWarnings("UnusedDeclaration")
@Root(name = "rowset")
public class ExamList {

	@ElementList(inline = true)
	private List<Exam> grades;

	public List<Exam> getExams() {
		return grades;
	}

	public void setExams(List<Exam> grades) {
		this.grades = grades;
	}

}
