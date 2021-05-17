package net.therap.util;

/**
 * @author sadia.afroz
 * @since 4/8/21
 */
public interface InputType {

    int COURSES_DETAILS_BY_TRAINEEID = 1;
    int TRAINEES_DETAILS_BY_COURSEID = 2;
    int ADD_NEW_COURSE = 3;
    int ADD_NEW_TRAINEE = 4;
    int ENROLL_NEW_TRAINEES= 5;
    int ENROLL_NEW_COURSES = 6;
    int REMOVE_TRAINEE_FROM_COURSE = 7;
    int UPDATE_TRAINEE_EMAIL = 8;
    int UPDATE_TRAINEE_NAME = 9;
    int UPDATE_COURSE_TITLE = 10;
    int DELETE_TRAINEE = 11;
    int DELETE_COURSE = 12;
    int GET_ALL_COURSES=13;
    int GET_ALL_TRAINEES=14;
    int EXIT = 15;
}
