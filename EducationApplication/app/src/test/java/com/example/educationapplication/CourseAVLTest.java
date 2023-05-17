package com.example.educationapplication;
import static org.junit.Assert.*;

import com.example.educationapplication.model.CourseAVL;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import dataObjects.CourseDto;

public class CourseAVLTest {

    private CourseAVL courseAVL;

    @Before
    public void setUp() {
        courseAVL = new CourseAVL();
    }

    @Test
    public void testInsertWithNull() {
        CourseAVL result = courseAVL.insert(null);
        assertNull(result.course);
    }

    @Test
    public void testInsertAndInOrderTraversal() {
        CourseDto course1 = new CourseDto(2100, "COMP2100", null);
        CourseDto course2 = new CourseDto(2620, "COMP2620", null);
        CourseDto course3 = new CourseDto(2300, "COMP2300", null);
        CourseDto course4 = new CourseDto(2420, "COMP2420", null);

        courseAVL.insert(course1);
        courseAVL.insert(course2);
        courseAVL.insert(course3);
        courseAVL.insert(course4);

        ArrayList<CourseDto> inOrder = courseAVL.inOrderTraversal();

        assertEquals(course1, inOrder.get(0));
        assertEquals(course3, inOrder.get(1));
        assertEquals(course4, inOrder.get(2));
        assertEquals(course2, inOrder.get(3));
    }

    @Test
    public void testRightRotate() {
        CourseAVL courseAVL = new CourseAVL();
        CourseDto course1 = new CourseDto(2100, "COMP2100", null);
        CourseDto course2 = new CourseDto(2620, "COMP2620", null);
        CourseDto course3 = new CourseDto(2300, "COMP2300", null);
        CourseDto course4 = new CourseDto(2420, "COMP2420", null);

        courseAVL.insert(course4);
        courseAVL.insert(course3);
        courseAVL.insert(course2);
        courseAVL.insert(course1);

        assertEquals(course4, courseAVL.course);
        assertEquals(course3, courseAVL.leftNode.course);
        assertEquals(course2, courseAVL.rightNode.course);
        assertEquals(course1, courseAVL.leftNode.leftNode.course);

        CourseAVL newRoot = courseAVL.rightRotate();

        assertEquals(course3, newRoot.course);
        assertEquals(course2, newRoot.rightNode.rightNode.course);
        assertEquals(course1, newRoot.leftNode.course);
        assertEquals(course4, newRoot.rightNode.course);
    }

    @Test
    public void testLeftRotate() {
        CourseAVL courseAVL = new CourseAVL();
        CourseDto course1 = new CourseDto(2100, "COMP2100", null);
        CourseDto course2 = new CourseDto(2620, "COMP2620", null);
        CourseDto course3 = new CourseDto(2300, "COMP2300", null);
        CourseDto course4 = new CourseDto(2420, "COMP2420", null);

        courseAVL.insert(course1);
        courseAVL.insert(course2);
        courseAVL.insert(course3);
        courseAVL.insert(course4);

        assertEquals(course1, courseAVL.course);
        assertEquals(course2, courseAVL.rightNode.course);
        assertEquals(course3, courseAVL.rightNode.leftNode.course);
        assertEquals(course4, courseAVL.rightNode.leftNode.rightNode.course);
    }
}


