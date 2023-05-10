package com.example.educationapplication;

import java.util.ArrayList;

import dataObjects.CourseDto;

/*
    Class is here temporarily since I don't know where this should be
 */
public class CourseAVL {
    public CourseDto course;
    public CourseAVL leftNode;
    public CourseAVL rightNode;

    public CourseAVL (CourseDto currCourse, CourseAVL left, CourseAVL right) {
        this.course = currCourse;
        this.leftNode = left;
        this.rightNode = right;
    }

    public CourseAVL() {
        this.course = null;
    }

    public CourseAVL insert(CourseDto currCourse) {
        if (currCourse == null) {
            return this;
        }

        if (this.course == null) {
            this.course = currCourse;
            return this;
        }

        if (currCourse.getCourseID() > this.course.getCourseID()) {
            CourseAVL insertRight;
            if (this.rightNode == null) {
                this.rightNode = new CourseAVL();
            }
            insertRight = new CourseAVL(this.course, this.leftNode, this.rightNode.insert(currCourse));
            if (insertRight.getBalanceFactor() > 1) {
                if ((insertRight.leftNode != null) && (insertRight.leftNode.getBalanceFactor() < 0)) {
                    insertRight.leftNode = insertRight.leftNode.leftRotate();
                }
                insertRight = insertRight.rightRotate();
                return insertRight;
            }
            if (insertRight.getBalanceFactor() < -1) {
                if (!(insertRight.rightNode == null) && (insertRight.rightNode.getBalanceFactor() > 0)) {
                    insertRight.rightNode = insertRight.rightNode.rightRotate();
                }
                insertRight = insertRight.leftRotate();
                return insertRight;
            }
            return insertRight;
        } else {
            if (this.leftNode == null) {
                this.leftNode = new CourseAVL();
            }
            CourseAVL insertLeft = new CourseAVL(this.course, this.leftNode.insert(currCourse), this.rightNode);
            if (insertLeft.getBalanceFactor() > 1) {
                if (!(insertLeft.leftNode == null) && (insertLeft.leftNode.getBalanceFactor() < 0)) {
                    insertLeft.leftNode = insertLeft.leftNode.leftRotate();
                }
                insertLeft = insertLeft.rightRotate();
                return insertLeft;
            }
            if (insertLeft.getBalanceFactor() < -1) {
                if (!(insertLeft.rightNode == null) && (insertLeft.rightNode.getBalanceFactor() > 0)) {
                    insertLeft.rightNode = insertLeft.rightNode.rightRotate();
                }
                insertLeft = insertLeft.leftRotate();
                return insertLeft;
            }
            return insertLeft;
        }
    }

    public CourseAVL rightRotate() {
        if (this.leftNode == null || this.leftNode.course == null) {
            return this;
        }
        CourseAVL newParent = this.leftNode;
        CourseAVL newLeftOfCurrent = newParent.rightNode;
        CourseAVL curr = this;
        curr.leftNode = newLeftOfCurrent;
        newParent.rightNode = curr;

        return newParent;
    }

    public CourseAVL leftRotate() {
        if (this.rightNode == null || this.rightNode.course == null){
            return this;
        }
        CourseAVL newParent = this.rightNode;
        CourseAVL newRightOfCurrent = newParent.leftNode;
        CourseAVL curr = this;
        curr.rightNode = newRightOfCurrent;
        newParent.leftNode = curr;

        return newParent;
    }

    public int getHeight() {
        int leftNodeHeight;
        if (this.leftNode == null) {
            leftNodeHeight = 0;
        } else {
            leftNodeHeight = 1 + this.leftNode.getHeight();
        }
        int rightNodeHeight;
        if (this.rightNode == null) {
            rightNodeHeight = 0;
        } else {
            rightNodeHeight = 1 + this.rightNode.getHeight();
        }

        return Math.max(leftNodeHeight, rightNodeHeight);
    }

    public int getBalanceFactor() {
        int leftHeight;
        int rightHeight;
        if (leftNode == null) {
            leftHeight = 0;
        } else {
            leftHeight = leftNode.getHeight();
        }
        if (rightNode == null) {
            rightHeight = 0;
        } else {
            rightHeight = rightNode.getHeight();
        }

        return leftHeight - rightHeight;
    }

    public ArrayList<CourseDto> inOrderTraversal() {
        ArrayList<CourseDto> out = new ArrayList<>();
        inOrderTraversalHelper(out);
        return out;
    }

    private void inOrderTraversalHelper(ArrayList<CourseDto> out) {
        if (this.leftNode != null) {
            this.leftNode.inOrderTraversalHelper(out);
        }
        out.add(this.course);
        if (this.rightNode != null) {
            this.rightNode.inOrderTraversalHelper(out);
        }
    }

}
