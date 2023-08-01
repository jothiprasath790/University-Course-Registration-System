package com.university.dao;

import java.util.List;

import com.university.model.Course;

public interface CourseDao {
    void addCourse(Course course);

    List<Course> getAllCourses();

    void updateCourse(Course course);

    void deleteCourse(int courseId);
    
    Course getCourseById(int courseId);
}